package lt.ca.javau10.online.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import lt.ca.javau10.online.entites.Customer;
import lt.ca.javau10.online.models.CustomerDto;



@SpringBootTest
public class EntityMapperTests {

	@Autowired
	EntityMapper entityMapper;
	
	@Test 
	void toUserDto_ShouldCorrectlyMapUserEntityToUserDto(){
		//Arrange
		Customer userEntity = new Customer();
		userEntity.setId( 15L );
		userEntity.setUsername("someUserName");
		userEntity.setEmail("some@email.com");
		
		//Act
		CustomerDto result = entityMapper.toCustomerDto(userEntity);
		
		//Assert
		assertThat( result.getId()).isEqualTo(  userEntity.getId());
		assertThat( result.getUsername()).isEqualTo(  userEntity.getUsername());
		assertThat( result.getEmail()).isEqualTo(  userEntity.getEmail());
		
	}
	
	@Test
	void toUserEntity_ShouldCorrectlyMapUserDtoToUserEntity() {
		CustomerDto customerDto = new CustomerDto( 20L, "testUser", "test@example.com");
		
		Customer result = entityMapper.toCustomer(customerDto);
		
		assertThat(result.getId()).isEqualTo(customerDto.getId());
		assertThat(result.getUsername()).isEqualTo(customerDto.getUsername());
        assertThat(result.getEmail()).isEqualTo(customerDto.getEmail());				
	}
	
}
