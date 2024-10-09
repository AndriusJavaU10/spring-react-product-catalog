package lt.ca.javau10.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entites.Purchase;



@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
