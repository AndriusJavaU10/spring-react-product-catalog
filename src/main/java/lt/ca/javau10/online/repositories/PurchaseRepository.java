package lt.ca.javau10.online.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.online.entites.Purchase;



@Repository
public interface PurchaseRepository extends JpaRepository<Purchase, Long> {

}
