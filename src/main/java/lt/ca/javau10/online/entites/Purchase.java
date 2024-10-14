package lt.ca.javau10.online.entites;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;


@Entity
public class Purchase   {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long Id;
	
	private LocalDateTime purchaseDate;

	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;
	
		
	@ManyToMany
    @JoinTable(
      name = "purchase_product",
      joinColumns = @JoinColumn(name = "purchase_id"),
      inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products = new ArrayList<>();
	
	public Purchase () {}

	public Purchase(Long id, LocalDateTime purchaseDate, Customer customer, List<Product> products) {
		super();
		Id = id;
		this.purchaseDate = purchaseDate;
		
		this.customer = customer;
		this.products = products;
	}

	public Purchase(Long id, LocalDateTime purchaseDate) {
		super();
		Id = id;
		this.purchaseDate = purchaseDate;
		
	}

	public Long getId() {
		return Id;
	}

	public void setId(Long id) {
		Id = id;
	}

	public LocalDateTime getPurchaseDate() {
		return purchaseDate;
	}

	public void setPurchaseDate(LocalDateTime purchaseDate) {
		this.purchaseDate = purchaseDate;
	}

	
	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
	}


	
}	