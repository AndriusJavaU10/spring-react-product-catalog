package lt.ca.javau10.online.services;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import lt.ca.javau10.online.entites.Customer;
import lt.ca.javau10.online.models.CustomerDto;
import lt.ca.javau10.online.repositories.CustomerRepository;
import lt.ca.javau10.online.utils.EntityMapper;




@SpringBootTest
public class UserServiceTest {

	@Mock
    private CustomerRepository customerRepository;
	
	@Mock
    private EntityMapper entityMapper;
	
	private CustomerService customerService;
	
	@BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerService(customerRepository, entityMapper);
    }
	
	@Test
    void createUser_ShouldReturnUserDto() {
		//Arrange
        CustomerDto userDto = new CustomerDto(); 
        Customer userEntity = new Customer(); 
                
        when(entityMapper.toCustomer(any(CustomerDto.class))).thenReturn(userEntity);
        when(customerRepository.save(any(Customer.class))).thenReturn(userEntity);
        when(entityMapper.toCustomerDto(any(Customer.class))).thenReturn(userDto);

      //Act
        CustomerDto createdUser = customerService.createCustomer(new CustomerDto());

        //Assert
        assertThat(createdUser).isNotNull();
    }
	
	@Test
    void getAllUsers_ShouldReturnListOfUserDto() {
		
		//Arrange
        List<Customer> userEntities = new ArrayList<>();
        
        when(customerRepository.findAll()).thenReturn(userEntities);

        
        //Act
        List<CustomerDto> users = customerService.getAllCustomer();

        
        //Assert
        assertThat(users).isNotNull();
      
    }
	
	@Test
    void getUserById_WhenUserExists_ShouldReturnUserDto() {
		//Arrange
        Optional<Customer> userEntity = Optional.of(new Customer());       
        
        when(customerRepository.findById(any(Long.class))).thenReturn(userEntity);
        when(entityMapper.toCustomerDto(any(Customer.class))).thenReturn(new CustomerDto());

        
        //Act
        Optional<CustomerDto> userDto = customerService.getCustomerById(1L);

        
        //Assert
        assertThat(userDto).isPresent();      
    }

	@Test
    void updateUser_WhenUserExists_ShouldReturnUpdatedUserDto() {
		
		//Arrange
		CustomerDto customerDto = new CustomerDto(); 
		Customer customer = new Customer();
        
        when(customerRepository.existsById(any(Long.class))).thenReturn(true);
        when(entityMapper.toCustomer(any(CustomerDto.class))).thenReturn(customer);
        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        when(entityMapper.toCustomerDto(any(Customer.class))).thenReturn(customerDto);

        
        //Act
        Optional<CustomerDto> updatedUser = customerService.updateCustomer(1L, new CustomerDto());

        //Assert
        assertThat(updatedUser).isPresent();     
    }
	
	@Test
    void deleteUser_ShouldInvokeDeleteMethod() {
		//Arrange
        Long customerId = 1L;
        
        //Act
        customerService.deleteCustomer(customerId);

        
        //Assert
        Mockito.verify(customerRepository).deleteById(customerId);      
    }
	
	
}
