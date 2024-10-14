package lt.ca.javau10.online.entites;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;

@Entity
public class Product {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private String name;
	private String description;
	private double price;
	
	@Enumerated(EnumType.STRING)
    private ProductCategory category;  // Enum vietoj string kategorijos
	
	
	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	@JsonIgnoreProperties
	private List<Review> reviews = new ArrayList<>();
	
	@ManyToMany(mappedBy = "products")
    private List<Purchase> purchases = new ArrayList<>();
	
	
	public Product() {}  

	

	
	public Product(Long id, String name, String description, double price, ProductCategory category) {
		super();
		Id = id;
		this.name = name;
		this.description = description;
		this.price = price;
		this.category = category;
	}






	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public double getPrice() {
		return price;
	}


	public void setPrice(double price) {
		this.price = price;
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


	public ProductCategory getCategory() {
		return category;
	}




	public void setCategory(ProductCategory category) {
		this.category = category;
	}



	

	


	


	

	

	
	
}