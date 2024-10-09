package lt.ca.javau10.entites;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Review { 

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long Id;
	
	private int rating;
	
	@Column(length = 2000)
	private String comment;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
	@JsonIgnoreProperties
    private Product product;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)	
	@JsonIgnoreProperties
	private Customer customer;
	
	
	public Review() {}


	public Review(Long id, int rating, String comment, Product product, Customer customer) {
		super();
		Id = id;
		this.rating = rating;
		this.comment = comment;
		
		this.product = product;
		this.customer = customer;
	}

	

	public Review(Long id, int rating, String comment) {
		super();
		Id = id;
		this.rating = rating;
		this.comment = comment;
	}


	public Long getId() {
		return Id;
	}


	public void setId(Long id) {
		Id = id;
	}


	public int getRating() {
		return rating;
	}


	public void setRating(int rating) {
		this.rating = rating;
	}


	public String getComment() {
		return comment;
	}


	public void setComment(String comment) {
		this.comment = comment;
	}




	public Product getProducts() {
		return product;
	}


	public void setProducts(Product products) {
		this.product = products;
	}


	public Customer getUsers() {
		return customer;
	}


	public void setUsers(Customer userFut) {
		this.customer = userFut;
	}
	
	
}
