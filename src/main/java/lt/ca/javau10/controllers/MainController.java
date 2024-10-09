package lt.ca.javau10.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import lt.ca.javau10.entites.Product;
import lt.ca.javau10.services.ProductService;

@CrossOrigin
@Controller
@RequestMapping("/product")

public class MainController {
	
	
	private final  ProductService prodService;
	
	public MainController (ProductService prodService) {
		this.prodService = prodService;
	}
	
	@GetMapping()
	public List<Product> getAllProducts(){ // http://localhost:8080/product
		return prodService.gelAllProducts();
		
	}
	
	@GetMapping("/{id}")
	public Optional<Product> getProductById(@PathVariable Long id){
		return prodService.getProductById(id);
	}
	
	@PostMapping
	public Product createProduct(@RequestBody Product product) {
		return prodService.createProduct(product);
	}
	
	@PutMapping("/{id}")
	public Product updateProduct (@PathVariable Long id, @RequestBody Product product ) {
		return prodService.updateProduct(id, product);
		
		
	}
	
	@DeleteMapping("/{id}")
	public void deleteProduct(@PathVariable Long id) {
		prodService.deleteProduct(id);
	}
	
						//	 PATCH endpointas pakeisti esamą produktą
	@PatchMapping("/{id}")
	public Product updateProduct2 (@PathVariable Long id, @RequestBody Product product ) {
		return prodService.updateProduct(id, product);
	
	}  
	
}
