package lt.ca.javau10.online.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lt.ca.javau10.online.models.Role;



@Entity
@Table(name = "customers")
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;	
	
	@Column(nullable = false, unique = true)	
	private String username;
	
	@JsonIgnore
	@Column(nullable = false, unique = false)
	private String password;
	
	@Column(nullable = false, unique = true)
	private String email;
	

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(  name = "customer_roles", 
    			joinColumns = @JoinColumn(name = "customer_id"), 
    			inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)	 
	 @JsonIgnoreProperties
	 private List<Review> reviews;
	
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)	 
	 private List<Purchase> purchases = new ArrayList<>();
	 
	 
	public Customer() {}


	public Customer(String username, String password, String email) {
		super();
		this.username = username;		
		this.password = password;
		this.email = email;
		
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
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


	public List<Review> getReviews() {
		return reviews;
	}


	public void setReviews(List<Review> reviews) {
		this.reviews = reviews;
	}


	public List<Purchase> getPurchases() {
		return purchases;
	}



	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}


	public Set<Role> getRoles() {
		return roles;
	}


	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}


	@Override
	public String toString() {
		return "Customer [Id=" + Id + ", username=" + username + ", password=" + password + ", email=" + email
				+ ", roles=" + roles + "]";
	}



	
	 
	 
}


