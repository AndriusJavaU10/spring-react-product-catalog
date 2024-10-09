package lt.ca.javau10.controllers;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

import lt.ca.javau10.entites.Customer;
import lt.ca.javau10.services.AdminService;


@CrossOrigin
@Controller("/user")
public class AdminCotroller  {

	private final AdminService adminService;
	
	public AdminCotroller (AdminService adminService) {
		this.adminService = adminService;
	}
	
	@GetMapping("/all")
	public List<Customer> getAllUsers(){ // http://localhost:8080/product/all
		return adminService.gelAllUsers();
	}
	
}