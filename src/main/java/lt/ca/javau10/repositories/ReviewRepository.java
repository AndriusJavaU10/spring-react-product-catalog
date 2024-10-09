package lt.ca.javau10.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lt.ca.javau10.entites.Review;



@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

	List<Review> findByProductId(Long productId);
}
