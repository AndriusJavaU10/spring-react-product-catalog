package lt.ca.javau10.online.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import lt.ca.javau10.online.payload.requests.LoginRequest;
import lt.ca.javau10.online.payload.requests.SignupRequest;
import lt.ca.javau10.online.payload.responses.JwtResponse;
import lt.ca.javau10.online.payload.responses.MessageResponse;
import lt.ca.javau10.online.services.AuthService;




@CrossOrigin
@RestController
@RequestMapping("/api/auth")
public class AuthCotroller  {

	private static final Logger logger = LoggerFactory.getLogger(AuthCotroller.class);
	
	private final AuthService authService;
	
	public AuthCotroller (AuthService authService) {
		this.authService = authService;
	}
	
	
	@PostMapping("/signin")
	public ResponseEntity<?> authenticateCustomer (@RequestBody LoginRequest loginRequest){
        JwtResponse jwtResponse = authService.authenticateUser(loginRequest);
        return ResponseEntity.ok(jwtResponse);		
	}
	
	@PostMapping("/signup")
    public ResponseEntity<?> registerCustomer(@RequestBody SignupRequest signUpRequest) {
		logger.info("Trying to signup \n" + signUpRequest);
		
        try {
            MessageResponse response = authService.registerUser(signUpRequest);
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
        	
            return ResponseEntity.status(e.getStatusCode())
            		.body(new MessageResponse(e.getReason()));
        }
    }
	
}