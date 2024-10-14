package lt.ca.javau10.online.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lt.ca.javau10.online.entites.Product;
import lt.ca.javau10.online.repositories.ProductRepository;
import lt.ca.javau10.online.utils.ResourceNotFoundException;

@Service
public class ProductService {
	


	private final ProductRepository productRepository;
	
	public ProductService(ProductRepository prodRepository) {
		this.productRepository = prodRepository;
	}
	
	public List<Product> gelAllProducts(){
		return productRepository.findAll();
	}
	
	
	public Optional<Product> getProductById (Long id){
		return productRepository.findById(id);
	}
	
	public Product createProduct(Product product) {
		return productRepository.save(product);	
		
	}
	
	
	public Product updateProduct(Long id, Product updatedProduct) {
	    return productRepository.findById(id)
	        .map(product -> {
	            // Update the product with the new values
	            product.setName(updatedProduct.getName());
	            product.setDescription(updatedProduct.getDescription());
	            product.setPrice(updatedProduct.getPrice());
	            
	            // Checks and sets the enum category
	            if (updatedProduct.getCategory() != null) {
	                product.setCategory(updatedProduct.getCategory());
	            }
	            
	            return productRepository.save(product); // Saved product
	        })
	        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));  // Jei produktas nerastas
	}
	
	public void deleteProduct (Long id) {
		productRepository.deleteById(id); 
	}	
	

	
}
