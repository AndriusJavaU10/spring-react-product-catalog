package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entites.Customer;



@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
