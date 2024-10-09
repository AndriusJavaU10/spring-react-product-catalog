package lt.ca.javau10.services;

import java.util.List;

import org.springframework.stereotype.Service;

import lt.ca.javau10.entites.Customer;
import lt.ca.javau10.repositories.CustomerRepository;



@Service
public class AdminService {

	CustomerRepository customerRepository;
	
	public AdminService (CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}

	public List<Customer> gelAllUsers() {
		
		return customerRepository.findAll();
	}
	
}
