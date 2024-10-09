package lt.ca.javau10.entites;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

@Entity
public class Customer {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;	
	
	@Column(nullable = false, unique = true)
	private String username;
	private String email;
	
//	@JsonIgnore
	@Column(nullable = false)
	private String password;
	
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name="role",
			joinColumns = @JoinColumn (name="user_id") )
	@Column(name="role")
	private Set<String> roles = new HashSet<>();
	
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)	 
	 @JsonIgnoreProperties
	 private List<Review> reviews;
	
	 @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)	 
	 private List<Purchase> purchases = new ArrayList<>();
	 
	 
	 public Customer() {}


	public Customer(Long id, String username, String email, String password, List<Review> reviews,
			List<Purchase> purchases) {
		super();
		Id = id;
		this.username = username;
		this.email = email;
		this.password = password;
		this.reviews = reviews;
		this.purchases = purchases;
	}

	public Customer(String username, String email, String password, Set<String> roles) {
		super();
		this.username = username;
		this.email = email;
		this.password = password;
		this.roles = roles;
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


	public Set<String> getRoles() {
		return roles;
	}


	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}


	
	 
	 
}


