package lt.ca.javau10;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import lt.ca.javau10.controllers.ProductController;
import lt.ca.javau10.entites.Product;
import lt.ca.javau10.entites.ProductCategory;
import lt.ca.javau10.services.ProductService;

@WebMvcTest(ProductController.class)
public class ProductControllerTest {


	@Autowired
    private MockMvc mockMvc;  // Naudojame MockMvc simuliuoti HTTP užklausas

    @MockBean
    private ProductService productService;  // Mockuojame ProductService

    @Autowired
    private ObjectMapper objectMapper;  // Jackson biblioteka JSON serializavimui

    private Product sampleProduct;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);  // Inicijuojame Mockito
        sampleProduct = new Product(1L, "Product name", "Product description", 10, ProductCategory.FERMENTED_BEVERAGES);
    }

    // Testas, kai gaunamas visų produktų sąrašas
    @Test
    public void testGetAllProducts() throws Exception {
        when(productService.gelAllProducts()).thenReturn(Arrays.asList(sampleProduct));

        mockMvc.perform(get("/api/products")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(sampleProduct.getId()))
                .andExpect(jsonPath("$[0].name").value(sampleProduct.getName()));
    }

    // Testas, kai gaunamas produktas pagal ID
    @Test
    public void testGetProductById() throws Exception {
        when(productService.getProductById(1L)).thenReturn(Optional.of(sampleProduct));

        mockMvc.perform(get("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleProduct.getId()))
                .andExpect(jsonPath("$.name").value(sampleProduct.getName()));
    }

    // Testas POST užklausai sukurti naują produktą
    @Test
    public void testCreateProduct() throws Exception {
    	when(productService.createProduct(any(Product.class))).thenReturn(sampleProduct);

    	mockMvc.perform(post("/api/products")
    		    .contentType(MediaType.APPLICATION_JSON)  // Nurodykite Content-Type kaip application/json
    		    .content(objectMapper.writeValueAsString(sampleProduct)))  // Konvertuojame objektą į JSON
    		    .andExpect(status().isCreated());  // Tikimasi, kad bus 201 Created
//                .andExpect(jsonPath("$.id").value(sampleProduct.getId()))
//                .andExpect(jsonPath("$.name").value(sampleProduct.getName()));
    }

    // Testas PUT užklausai atnaujinti produktą
    @Test
    public void testUpdateProduct() throws Exception {
        when(productService.updateProduct(eq(1L), any(Product.class))).thenReturn(sampleProduct);

        mockMvc.perform(put("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(sampleProduct)))  // Siunčiame JSON formatą
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(sampleProduct.getId()))
                .andExpect(jsonPath("$.name").value(sampleProduct.getName()));
    }

    // Testas DELETE užklausai ištrinti produktą
    @Test
    public void testDeleteProduct() throws Exception {
        doNothing().when(productService).deleteProduct(1L);

        mockMvc.perform(delete("/api/products/1")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }
    
}
	

