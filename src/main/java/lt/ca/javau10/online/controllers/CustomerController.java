package lt.ca.javau10.online.controllers;

import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lt.ca.javau10.online.models.CustomerDto;
import lt.ca.javau10.online.services.CustomerService;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerController {
	
	
	private final  CustomerService customerService;
//	private final RoleRepository roleRepository;
	
	public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
       
        
	}
	
	
	
	@PostMapping("/")
	public ResponseEntity<CustomerDto>  customers(@RequestBody CustomerDto userDto) {
		CustomerDto createdCustoemr = customerService.createCustomer(userDto);
		return new ResponseEntity<>(createdCustoemr, HttpStatus.CREATED);		
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<CustomerDto>> getAllCustomers(){
		List<CustomerDto> customers = customerService.getAllCustomer();
		return new ResponseEntity<>(customers, HttpStatus.OK);	
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id){	
		Optional<CustomerDto> customerInBox = customerService.getCustomerById(id);
		return customerInBox
				.map( ResponseEntity::ok )
				.orElseGet( () -> ResponseEntity.notFound().build());
	}
	
	
	// Roles
	
//	@PutMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<CustomerDto> updateCustomer(
//										@PathVariable Long id, 
//										@RequestBody CustomerDto customerDto){
//		Optional<CustomerDto> customerInBox = customerService.updateCustomer(id, customerDto);
//		
//		return customerInBox
//				.map( ResponseEntity::ok )
//				.orElseGet( () -> ResponseEntity.notFound().build());
//	}
//	
//	@Transactional
//	@DeleteMapping("/{id}")
//	@PreAuthorize("hasRole('ADMIN')")
//	public ResponseEntity<?> updateUserRole(@PathVariable Long id, @RequestBody SetRoleRequest roleRequest) {
//		// 1. Get user by id
//	    Optional<Customer> customerOpt = customerService.findById(id);
//
//	    if (!customerOpt.isPresent()) {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//	    }
//
//	    Customer customer = customerOpt.get();
//
//	    // 2. Get role from request
//	    String newRoleName = roleRequest.getRole();
//	    Role role = roleRepository.findByName(ERole.valueOf(newRoleName))
//	                              .orElseThrow(() -> new RuntimeException("Role not found"));
//
//	    // 3. Assign a new role
//	    customer.getRoles().clear(); // Can clear all roles or just add a new one
//	    customer.getRoles().add(role);
//
//	    // 4. Save the user
//	    customerService.save(customer);
//
//	    return ResponseEntity.ok("Role updated successfully");
//	}

	
	
	
}
