package lt.ca.javau10.online.controllers;

import lt.ca.javau10.online.models.CustomerDto;
import lt.ca.javau10.online.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
@AutoConfigureMockMvc(addFilters = false)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService; // Naudojame @MockBean vietoj @Mock

    private CustomerDto testCustomer;

    @BeforeEach
    public void setup() {
        testCustomer = new CustomerDto();
        testCustomer.setId(1L);
        testCustomer.setUsername("JohnDoe");
        testCustomer.setEmail("johndoe@example.com");
    }

    @Test
    public void createCustomer_ShouldReturnCreatedCustomer() throws Exception {
        when(customerService.createCustomer(any(CustomerDto.class))).thenReturn(testCustomer);

        mockMvc.perform(post("/api/customers/")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"JohnDoe\", \"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("JohnDoe"))
                .andExpect(jsonPath("$.email").value("johndoe@example.com"));

        verify(customerService, times(1)).createCustomer(any(CustomerDto.class));
    }

    @Test
    public void getAllCustomers_ShouldReturnCustomerList() throws Exception {
        when(customerService.getAllCustomer()).thenReturn(Arrays.asList(testCustomer));

        mockMvc.perform(get("/api/customers/"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].username").value("JohnDoe"));

        verify(customerService, times(1)).getAllCustomer();
    }

    @Test
    public void getCustomerById_WhenCustomerExists_ShouldReturnCustomer() throws Exception {
        when(customerService.getCustomerById(1L)).thenReturn(Optional.of(testCustomer));

        mockMvc.perform(get("/api/customers/{id}", 1L))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("JohnDoe"));

        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    public void getCustomerById_WhenCustomerDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(customerService.getCustomerById(1L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/api/customers/{id}", 1L))
                .andExpect(status().isNotFound());

        verify(customerService, times(1)).getCustomerById(1L);
    }

    @Test
    public void updateCustomer_WhenCustomerExists_ShouldReturnUpdatedCustomer() throws Exception {
        when(customerService.updateCustomer(eq(1L), any(CustomerDto.class))).thenReturn(Optional.of(testCustomer));

        mockMvc.perform(put("/api/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"JohnDoe\", \"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("JohnDoe"));

        verify(customerService, times(1)).updateCustomer(eq(1L), any(CustomerDto.class));
    }

    @Test
    public void updateCustomer_WhenCustomerDoesNotExist_ShouldReturnNotFound() throws Exception {
        when(customerService.updateCustomer(eq(1L), any(CustomerDto.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/api/customers/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"JohnDoe\", \"email\":\"johndoe@example.com\"}"))
                .andExpect(status().isNotFound());

        verify(customerService, times(1)).updateCustomer(eq(1L), any(CustomerDto.class));
    }

    @Test
    public void deleteCustomer_ShouldReturnNoContent() throws Exception {
        doNothing().when(customerService).deleteCustomer(1L);

        mockMvc.perform(delete("/api/customers/{id}", 1L))
                .andExpect(status().isNoContent());

        verify(customerService, times(1)).deleteCustomer(1L);
    }
}
