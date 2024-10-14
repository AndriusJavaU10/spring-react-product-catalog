package lt.ca.javau10.online.models;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;




public class CustomerDto implements UserDetails {

	private static final long serialVersionUID = -1L;
	
	private Long id;
	private String username;
	private String email;
	
	private Set<Role> roles = new HashSet<Role>();
	
	@JsonIgnore
	private String password; //One way road 
	
	public CustomerDto() {}

	public CustomerDto(Long id, String username, String email) {
		this.id = id;
		this.username = username;
		this.email = email;
	}
	
	public CustomerDto(Long id, String username, String email, String password, Set<Role> roles) {
		this.id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
	}	
	

	public CustomerDto(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.password = password;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		 return roles.stream()
	                .map(role -> new SimpleGrantedAuthority(role.getName()))
	                .collect(Collectors.toSet());
	}

	@Override
	public boolean isAccountNonExpired() {		
		return true;		//can add logic for account validity
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;	// can add logic to check the account lock
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;		//can add logic for password validity
	}

	@Override
	public boolean isEnabled() {
		return true;		//an set the logic whether the user is enabled
	}

	  @Override
	  public boolean equals(Object o) {
	    if (this == o)  // If both objects are the same reference, they are equal
	      return true;
	    if (o == null || getClass() != o.getClass())
	      return false; // If one object is null or they are not of the same class, they are not the same
	    CustomerDto customer = (CustomerDto) o; //Cast to UserDto type
	    return Objects.equals(id, customer.id); // We compare id, which is the unique identifier of the user
	  }
	  
	  @Override
	  public int hashCode() {
	      return Objects.hash(id);
	  }

	@Override
	public String toString() {
		return "UserDto [id=" + id + ", username=" + username + ", email=" + email + ", roles=" + roles + "]";
	}
	  
	  
}
