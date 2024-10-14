package lt.ca.javau10.online.services;

import java.util.List;
import java.util.Optional;

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
	
}
