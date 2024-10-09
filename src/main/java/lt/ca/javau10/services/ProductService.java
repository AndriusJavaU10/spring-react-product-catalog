package lt.ca.javau10.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import lt.ca.javau10.Utils.ResourceNotFoundException;
import lt.ca.javau10.entites.Product;
import lt.ca.javau10.repositories.ProductRepository;

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
	            // Atnaujiname produktą su naujomis reikšmėmis
	            product.setName(updatedProduct.getName());
	            product.setDescription(updatedProduct.getDescription());
	            product.setPrice(updatedProduct.getPrice());
	            
	            // Tikriname ir nustatome enum kategoriją
	            if (updatedProduct.getCategory() != null) {
	                product.setCategory(updatedProduct.getCategory());
	            }
	            
	            return productRepository.save(product); // Išsaugome produktą
	        })
	        .orElseThrow(() -> new ResourceNotFoundException("Product not found with id " + id));  // Jei produktas nerastas
	}
	
	public void deleteProduct (Long id) {
		productRepository.deleteById(id); 
	}	
	

	
}
