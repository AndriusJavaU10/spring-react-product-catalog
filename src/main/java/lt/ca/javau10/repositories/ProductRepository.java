package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import lt.ca.javau10.entites.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	
}
