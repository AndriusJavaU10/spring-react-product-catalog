package lt.ca.javau10.controllers;


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

import lt.ca.javau10.entites.Product;
import lt.ca.javau10.entites.ProductCategory;
import lt.ca.javau10.services.ProductService;

@RestController 
@RequestMapping("/api/products")
@CrossOrigin(origins = "http://localhost:3000")
public class ProductController {
		
	private final  ProductService productService;
	
	public ProductController (ProductService productService) {
		this.productService = productService; 
		}
	
	public Product getSimpleProduct() {
		return new Product(1L, "Product name", "Product description", 0, ProductCategory.FERMENTED_BEVERAGES);
	}
	
//	 1. Gauti visų produktų sąrašą kaip JSON
	    @GetMapping()
	    public List<Product> getAllProducts(){ // http://localhost:8080/api/products
			return productService.gelAllProducts();
	    }

	    // 2. Gauti vieną produktą pagal ID
	    @GetMapping("/{id}")
	    public Optional<Product> getProductById(@PathVariable Long id){
			return productService.getProductById(id);
		}
	    
	   	    
	 // POST - Create a new product
	    @PostMapping(consumes = { "application/json", "application/json;charset=UTF-8" }, produces = "application/json")
	    public ResponseEntity<Product> createProduct(@RequestBody Product product) {
	        Product createdProduct = productService.createProduct(product);
	        return new ResponseEntity<>(createdProduct, HttpStatus.CREATED);
	    }

	    // 4. DELETE endpointas ištrinti produktą pagal ID	 
	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
	        productService.deleteProduct(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

	    
	 // PUT - Update an existing product
	    @PutMapping(value = "/{id}", consumes = { "application/json", "application/json;charset=UTF-8" }, produces = "application/json")
	    public ResponseEntity<Product> updateProduct(@PathVariable Long id, @RequestBody Product updatedProduct) {
	        Product updated = productService.updateProduct(id, updatedProduct);
	        return new ResponseEntity<>(updated, HttpStatus.OK);
	    }

	    @GetMapping("/dummy")
	    public Product getDummy() {
	    	return new Product(1L, "name", " description", 10, ProductCategory.FERMENTED_BEVERAGES);
		}

	    
}
