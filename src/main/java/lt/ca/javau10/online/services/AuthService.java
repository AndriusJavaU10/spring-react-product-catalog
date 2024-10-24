package lt.ca.javau10.online.services;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import lt.ca.javau10.online.entites.Customer;
import lt.ca.javau10.online.models.CustomerDto;
import lt.ca.javau10.online.models.ERole;
import lt.ca.javau10.online.models.Role;
import lt.ca.javau10.online.payload.requests.LoginRequest;
import lt.ca.javau10.online.payload.requests.SignupRequest;
import lt.ca.javau10.online.payload.responses.JwtResponse;
import lt.ca.javau10.online.payload.responses.MessageResponse;
import lt.ca.javau10.online.repositories.CustomerRepository;
import lt.ca.javau10.online.repositories.RoleRepository;
import lt.ca.javau10.online.security.JwtUtils;



@Service
public class AuthService {

	private static final Logger logger = LoggerFactory.getLogger(AuthService.class);
	
	AuthenticationManager authenticationManager;
	
	CustomerRepository customerRepository;
	
	RoleRepository roleRepository;

	PasswordEncoder encoder;

	JwtUtils jwtUtils;
	
	public AuthService (AuthenticationManager authenticationManager,
			CustomerRepository customerRepository,
			RoleRepository roleRepository,
			PasswordEncoder encoder,
			JwtUtils jwtUtils) {
		this.customerRepository = customerRepository;
		this.authenticationManager = authenticationManager;
		this.roleRepository = roleRepository;
		this.encoder = encoder;
		this.jwtUtils = jwtUtils;
	}

	public JwtResponse authenticateUser(LoginRequest loginRequest) {
	       
		Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword())
        );
		
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtUtils.generateJwtToken(authentication);
        CustomerDto userDetails = (CustomerDto) authentication.getPrincipal();
        
        logger.info("Before: " + userDetails.toString());
        
        List<String> roles = userDetails.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)
            .collect(Collectors.toList());

        return new JwtResponse(jwt, userDetails.getId(), userDetails.getUsername(), userDetails.getEmail(), roles);
    }
	
	public MessageResponse registerUser(SignupRequest signUpRequest) {
		
        checkUserExists(signUpRequest);
        
        
        Customer user = createNewCustomer(signUpRequest);
        Set<Role> roles = getInitialRoles(signUpRequest);

        user.setRoles(roles);
       
        logger.info("Before: " + user.toString());
        user = customerRepository.save(user);
        logger.info("After: " + user.toString());
        
        return new MessageResponse("User registered successfully!");
    }

	private Customer createNewCustomer(SignupRequest signUpRequest) {
		Customer user = new Customer(
				signUpRequest.getUsername(), 
				encoder.encode(signUpRequest.getPassword()),
	            signUpRequest.getEmail());
		logger.info(user.toString());
		return user;
	}
	private Set<Role> getInitialRoles(SignupRequest signUpRequest) {
		Set<String> strRoles = signUpRequest.getRole();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null || strRoles.isEmpty()) {
            Role userRole = roleRepository.findByName(ERole.ROLE_USER)
                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            for (String role : strRoles) {
                Role resolvedRole = roleRepository.findByName(ERole.valueOf("ROLE_" + role.toUpperCase()))
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                roles.add(resolvedRole);
            }
        }
		return roles;
	}

	private void checkUserExists(SignupRequest signUpRequest) {
		if (customerRepository.existsByUsername(signUpRequest.getUsername())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Username is already taken!");
	    }
	
	    if (customerRepository.existsByEmail(signUpRequest.getEmail())) {
	        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Error: Email is already in use!");
	    }
	}
	

	
	
	
	
	
}
