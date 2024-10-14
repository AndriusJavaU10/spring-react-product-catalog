package lt.ca.javau10.online.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	public CustomerController (CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping("/")
	public ResponseEntity<CustomerDto>  createCustomer(@RequestBody CustomerDto userDto) {
		CustomerDto createdCustoemr = customerService.createCustomer(userDto);
		return new ResponseEntity<>(createdCustoemr, HttpStatus.CREATED);		
	}
	
	@GetMapping("/")
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
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(
										@PathVariable Long id, 
										@RequestBody CustomerDto customerDto){
		Optional<CustomerDto> customerInBox = customerService.updateCustomer(id, customerDto);
		
		return customerInBox
				.map( ResponseEntity::ok )
				.orElseGet( () -> ResponseEntity.notFound().build());
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCustomer(@PathVariable Long id){
		customerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	
	
}
