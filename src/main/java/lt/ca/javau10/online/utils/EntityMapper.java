package lt.ca.javau10.online.utils;

import org.springframework.stereotype.Component;

import lt.ca.javau10.online.entites.Customer;
import lt.ca.javau10.online.models.CustomerDto;





@Component
public class EntityMapper {
	
	public Customer toCustomer(CustomerDto dto) {
		
		Customer entity = new Customer();
		entity.setId( dto.getId());
		entity.setUsername( dto.getUsername());
		entity.setEmail(dto.getEmail());
		entity.setPassword(dto.getPassword());	
		return entity;		
	}
	//Long id, String username, String email, String password, Collection<? extends GrantedAuthority> authorities) {
	
	public CustomerDto toCustomerDto(Customer entity) {
		return new CustomerDto( 
				entity.getId(), 
				entity.getUsername(), 
				entity.getEmail(), 
				entity.getPassword(), 
				entity.getRoles() 
			);
	}
	
}
