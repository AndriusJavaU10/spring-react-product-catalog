package lt.ca.javau10.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import lt.ca.javau10.online.entites.Product;


public interface ProductRepository extends JpaRepository<Product, Long> {

	
}
