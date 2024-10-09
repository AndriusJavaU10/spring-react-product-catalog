package lt.ca.javau10;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import lt.ca.javau10.Utils.ResourceNotFoundException;
import lt.ca.javau10.entites.Product;
import lt.ca.javau10.entites.ProductCategory;
import lt.ca.javau10.repositories.ProductRepository;
import lt.ca.javau10.services.ProductService;

import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;



public class TestsProductService {

	@Mock
	private ProductRepository productRepository;
	
	@InjectMocks
	private ProductService productService;
	private Product sampleProduct;
	
	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		sampleProduct = new Product(1L, "Test Product", "Test Description", 15.0, ProductCategory.FERMENTED_BEVERAGES);
	}
	
	@Test
	void testAllProducts() { 
		//Arrange
		Product product1 = new Product(1L,"Test product","Test description", 15, ProductCategory.FERMENTED_BEVERAGES );
		Product product2 = new Product(2L,"Test product2","Test description2", 16, ProductCategory.FERMENTED_BEVERAGES );
		
		when (productRepository.findAll())
			.thenReturn (Arrays.asList(product1, product2) );
		
		//Act
		List<Product> products = productService.gelAllProducts();
		
		//Assert
		assertNotNull(products);
		assertEquals(2, products.size());
		verify(productRepository, times(1)).findAll();
		
	}
	@Test
    public void testGetProductById_Success() {
        // Arrange
        when(productRepository.findById(1L))
        	.thenReturn(Optional.of(sampleProduct));

        // Act
        Optional<Product> product = productService.getProductById(1L);

        // Assert
        assertTrue(product.isPresent());
        assertEquals("Test Product", product.get().getName());
        verify(productRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetProductById_NotFound() {
        // Arrange
        when(productRepository.findById(1L))
        	.thenReturn(Optional.empty());

        // Act
        Optional<Product> product = productService.getProductById(1L);

        // Assert
        assertFalse(product.isPresent());
        verify(productRepository, times(1)).findById(1L);
    }
	
	@Test
	void testCreateProduct() {
		//Arrange
		Product product = new Product (1L, "Test product", "Test description", 18, ProductCategory.FERMENTED_BEVERAGES);
		
		when(productRepository.save(product))
			.thenReturn(new Product (1L, "Test product", "Test description", 18, ProductCategory.FERMENTED_BEVERAGES));
		
		//Act
		Product createProduct = productService.createProduct(product);
		
		//Assert
		assertNotNull(createProduct);
		assertEquals("Test product", createProduct.getName());
		assertNotNull(createProduct.getId());
		verify(productRepository, times(1)).save(product);
	}
	
	 @Test
	    public void testUpdateProduct_Success() {
	        // Arrange
	        Product updatedProduct = new Product(1L, "Test product", "Test description", 18.0, ProductCategory.FERMENTED_BEVERAGES);
	        when(productRepository.findById(1L)).thenReturn(Optional.of(sampleProduct));
	        when(productRepository.save(any(Product.class))).thenReturn(updatedProduct);

	        // Act
	        Product result = productService.updateProduct(1L, updatedProduct);

	        // Assert
	        assertNotNull(result);
	        assertEquals("Test product", result.getName());
	        assertEquals(18.0, result.getPrice());
	        verify(productRepository, times(1)).findById(1L);
	        verify(productRepository, times(1)).save(sampleProduct); // Tikriname, ar atnaujinta
	    }

	    @Test
	    public void testUpdateProduct_NotFound() {
	        // Arrange
	    	 Long nonExistentId = 1L;  // ID, kuris neegzistuoja
	    	    Product updatedProduct = new Product(1L, "Updated Name", "Updated Description", 20.0, ProductCategory.FERMENTED_BEVERAGES);

	    	    when(productRepository.findById(nonExistentId)).thenReturn(Optional.empty());  // Imituojame, kad produkto nėra

	        // Act
	    	    ResourceNotFoundException exception = assertThrows(ResourceNotFoundException.class, () -> {
	    	        productService.updateProduct(nonExistentId, updatedProduct);
	    	    });

	        // Assert
	    	    assertEquals("Product not found with id 1", exception.getMessage());  // Patikriname klaidos pranešimą
	    }

	    @Test
	    public void testDeleteProduct() {
	        // Arrange
	        doNothing().when(productRepository).deleteById(1L);

	        // Act
	        productService.deleteProduct(1L);

	        // Assert
	        verify(productRepository, times(1)).deleteById(1L);
	    }
	
}
