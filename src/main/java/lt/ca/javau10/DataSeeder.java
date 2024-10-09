package lt.ca.javau10;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import jakarta.transaction.Transactional;
import lt.ca.javau10.entites.Customer;
import lt.ca.javau10.entites.Product;
import lt.ca.javau10.entites.ProductCategory;
import lt.ca.javau10.repositories.CustomerRepository;
import lt.ca.javau10.repositories.ProductRepository;


@Component
public class DataSeeder implements CommandLineRunner  {

	
	private ProductRepository productRepository;	
	private CustomerRepository customerRepository;
	
	
	public DataSeeder (ProductRepository prodRepository, CustomerRepository customerRepository) {
		this.productRepository = prodRepository;		
		this.customerRepository = customerRepository;		
		
	}
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Įkraunami duomenenys....");
		
		if(productRepository.count() > 0) 
			return;
	
		Product honeyDrinks = new Product();
		 honeyDrinks.setName("Honey and ginger");
		 honeyDrinks.setDescription("Honey and ginger enzymes drinks");
		 honeyDrinks.setPrice(15);
		 honeyDrinks.setCategory(ProductCategory.FERMENTED_BEVERAGES); // Enum reikšmė
		 productRepository.save(honeyDrinks);
		 
	        
	     Product pineNuts = new Product();
	     pineNuts.setName("Pine nuts");
	     pineNuts.setDescription("Organic shelled pine nuts");
	     pineNuts.setPrice(80);
	     pineNuts.setCategory(ProductCategory.NUTS_AND_SEEDS); // Enum reikšmė
	     productRepository.save(pineNuts); 
		     
		     
		     
		 Product basil = new Product();
		 basil.setName("Organic basil");
		 basil.setDescription("Aromatic spice");
		 basil.setPrice(70);
		 basil.setCategory(ProductCategory.SPICES); // Enum reikšmė
		 productRepository.save(basil); 
	
		 Product hemp = new Product();
		 hemp.setName("Organic hemp protein");
		 hemp.setDescription("It is a real protein source with a lot of amino acids");
		 hemp.setPrice(22);
		 hemp.setCategory(ProductCategory.SUPERFOOD); // Enum reikšmė
		 productRepository.save(hemp); 
		
		 Product mangoesDried = new Product();
		 mangoesDried.setName("Organic mangoes (dried)");
		 mangoesDried.setDescription("They are rich in essential vitamins and minerals, including vitamin A, vitamin C and potassium.");
		 mangoesDried.setPrice(30);
		 mangoesDried.setCategory(ProductCategory.DRIED_FOOD); // Enum reikšmė
		 productRepository.save(mangoesDried); 
		
		 Product teaTree = new Product();
		 teaTree.setName("Organic tea tree essential oil, 10 ml");
		 teaTree.setDescription("Skin and respiratory tract");
		 teaTree.setPrice(10);
		 teaTree.setCategory(ProductCategory.ESSENTIAL_OILS); // Enum reikšmė
		 productRepository.save(teaTree); 
			
		 Product soapCoco = new Product();
		 soapCoco.setName("Organic coconut soap, 120 g");
		 soapCoco.setDescription("Organic coconut soap with basil and mint. Esigned to soothe and nourish");
		 soapCoco.setPrice(10);
		 soapCoco.setCategory(ProductCategory.HYGIENE); // Enum reikšmė
		 productRepository.save(soapCoco); 
	
		 System.out.println("Duomenys sėkmingai įkelti.");
		 
		 // Sukuriam vartotoją
		 Customer  customer = new Customer ();
		 customer.setUsername("username");
		 customer.setEmail("email@example.com");
		 customer.setPassword("password123"); // Pastaba: slaptažodį reiktų užšifruoti prieš saugant
		 customerRepository.save(customer);
	}
}
