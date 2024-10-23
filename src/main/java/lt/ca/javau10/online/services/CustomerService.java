package lt.ca.javau10.online.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import lt.ca.javau10.online.entites.Customer;
import lt.ca.javau10.online.models.CustomerDto;
import lt.ca.javau10.online.repositories.CustomerRepository;
import lt.ca.javau10.online.utils.EntityMapper;



@Service
public class CustomerService implements UserDetailsService  {

	private final CustomerRepository customerRepository;
	@Autowired
//    private RoleRepository roleRepository; 
	
	private final EntityMapper entityMapper;
	private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
	
	
	
	public CustomerService(CustomerRepository customerRepository, EntityMapper entityMapper) {
		this.customerRepository = customerRepository;
		this.entityMapper = entityMapper;
	}
	
	
	
	
	//CRUD - Create, Read, Update, Delete
	
	public CustomerDto createCustomer(CustomerDto userDto) {
		Customer customerBeforeSave = entityMapper.toCustomer(userDto);
		

		Customer customerAfterSave = customerRepository.save(customerBeforeSave);		
		
		return entityMapper.toCustomerDto(customerAfterSave);		
	}
	
	public List<CustomerDto> getAllCustomer(){
		List<Customer> customers = customerRepository.findAll();
		
		return customers.stream()
				.map(entityMapper::toCustomerDto)
				.toList();		
	}
	
	public Optional<CustomerDto> getCustomerById(Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		
		return customer.map(entityMapper::toCustomerDto);
	}
	
	public Optional<CustomerDto> updateCustomer(Long id, CustomerDto userDto ){
		
		if( customerRepository.existsById(id) ) {
			Customer userEntityBeforeSave = entityMapper.toCustomer(userDto);
			userEntityBeforeSave.setId(id);
			
			Customer userEntityAfterSave = customerRepository.save(userEntityBeforeSave);
			return Optional.of( entityMapper.toCustomerDto(userEntityAfterSave));
			
		} else {
			return Optional.empty();
		}
		
	}
	
	public void deleteCustomer(Long id) {
		customerRepository.deleteById(id);
	}

	@Override
	@Qualifier("customerService")
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Customer customer = customerRepository
							.findByUsername(username)
							.orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
		
		logger.info("Loaded :"+ customer.toString());
		return entityMapper.toCustomerDto(customer);
	}


		// Roles

//	public Optional<Customer> findById(Long id) {
//		 return customerRepository.findById(id);
//	}
//	
//	public Customer save(Customer customer) {
//        return customerRepository.save(customer);
//    }
//	
//	 public Customer updateRoles(Long customerId, Set<String> roleNames) {
//	        Customer customer = customerRepository.findById(customerId)
//	            .orElseThrow(() -> new RuntimeException("User not found"));
//
//	     // Find roles by name (ENUM values)
//	        Set<Role> roles = new HashSet<>();
//	        for (String roleName : roleNames) {
//	            Role role = roleRepository.findByName(ERole.valueOf(roleName))
//	                .orElseThrow(() -> new RuntimeException("Role not found"));
//	            roles.add(role);
//	        }
//
//	     // Update user roles
//	        customer.setRoles(roles);
//	        return customerRepository.save(customer);
//	    }
	
}
