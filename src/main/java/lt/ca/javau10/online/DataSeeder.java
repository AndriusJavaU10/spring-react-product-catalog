package lt.ca.javau10.online;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import jakarta.transaction.Transactional;
import lt.ca.javau10.online.entites.Customer;
import lt.ca.javau10.online.entites.Product;
import lt.ca.javau10.online.entites.ProductCategory;
import lt.ca.javau10.online.models.ERole;
import lt.ca.javau10.online.models.Role;
import lt.ca.javau10.online.repositories.CustomerRepository;
import lt.ca.javau10.online.repositories.ProductRepository;
import lt.ca.javau10.online.repositories.RoleRepository;





@Component
public class DataSeeder implements CommandLineRunner  {

	private static final Logger logger = LoggerFactory.getLogger(DataSeeder.class);
	
	@Autowired
    private RoleRepository roleRepository;
	
	private ProductRepository productRepository;	
	private CustomerRepository customerRepository;
		
	
	public DataSeeder (ProductRepository prodRepository, CustomerRepository customerRepository) {
		this.productRepository = prodRepository;		
		this.customerRepository = customerRepository;			
	}
	
	
	@Transactional
	@Override
	public void run(String... args) throws Exception {
		System.out.println("Loading data....");
	
		addPrimaryProducts();
		addEnumRole();
	}
	
	
	void addEnumRole() {
			if (roleRepository.findByName(ERole.ROLE_ADMIN).isEmpty()) {
	            roleRepository.save(new Role(ERole.ROLE_ADMIN));
	            logger.info("ROLE_ADMIN added.");
	        }
	        
	        if (roleRepository.findByName(ERole.ROLE_USER).isEmpty()) {
	            roleRepository.save(new Role(ERole.ROLE_USER));
	            logger.info("ROLE_USER added.");
	        }
	        if (roleRepository.findByName(ERole.ROLE_MODERATOR).isEmpty()) {
	            roleRepository.save(new Role(ERole.ROLE_MODERATOR));
	            logger.info("ROLE_MODERATOR added.");
	        }
		}
	
	
	
	
	void addPrimaryProducts()	{		
		
		if(productRepository.count() > 0) 
			return;
		
		// A product is added and a category is assigned
		Product honeyDrinks = new Product();
		 honeyDrinks.setName("Honey and ginger");
		 honeyDrinks.setDescription("Honey and ginger enzymes drinks");
		 honeyDrinks.setPrice(15);
		 honeyDrinks.setCategory(ProductCategory.FERMENTED_BEVERAGES); // Enum value
		 productRepository.save(honeyDrinks);
		 
	        
	     Product pineNuts = new Product();
	     pineNuts.setName("Pine nuts");
	     pineNuts.setDescription("Organic shelled pine nuts");
	     pineNuts.setPrice(80);
	     pineNuts.setCategory(ProductCategory.NUTS_AND_SEEDS); // Enum value
	     productRepository.save(pineNuts); 		     
		     
		     
		 Product basil = new Product();
		 basil.setName("Organic basil");
		 basil.setDescription("Aromatic spice");
		 basil.setPrice(70);
		 basil.setCategory(ProductCategory.SPICES); // Enum value
		 productRepository.save(basil); 
	
		 Product hemp = new Product();
		 hemp.setName("Organic hemp protein");
		 hemp.setDescription("It is a real protein source with a lot of amino acids");
		 hemp.setPrice(22);
		 hemp.setCategory(ProductCategory.SUPERFOOD); // Enum value
		 productRepository.save(hemp); 
		
		 Product mangoesDried = new Product();
		 mangoesDried.setName("Organic mangoes (dried)");
		 mangoesDried.setDescription("They are rich in essential vitamins and minerals, including vitamin A, vitamin C and potassium.");
		 mangoesDried.setPrice(30);
		 mangoesDried.setCategory(ProductCategory.DRIED_FOOD); // Enum value
		 productRepository.save(mangoesDried); 
		
		 Product teaTree = new Product();
		 teaTree.setName("Organic tea tree essential oil, 10 ml");
		 teaTree.setDescription("Skin and respiratory tract");
		 teaTree.setPrice(10);
		 teaTree.setCategory(ProductCategory.ESSENTIAL_OILS); // Enum value
		 productRepository.save(teaTree); 
			
		 Product soapCoco = new Product();
		 soapCoco.setName("Organic coconut soap, 120 g");
		 soapCoco.setDescription("Organic coconut soap with basil and mint. Esigned to soothe and nourish");
		 soapCoco.setPrice(10);
		 soapCoco.setCategory(ProductCategory.HYGIENE); // Enum value
		 productRepository.save(soapCoco); 
	
		 System.out.println("Duomenys sėkmingai įkelti.");
		 
		 // creating a user
		 Customer  customer = new Customer ();
		 customer.setUsername("username");
		 customer.setEmail("email@example.com");
		 customer.setPassword("password123"); // Password should be encrypted before saving
		 customerRepository.save(customer);
	}
		
		
}
