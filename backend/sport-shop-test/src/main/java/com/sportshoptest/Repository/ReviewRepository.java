package com.sportshoptest.Repository;

import com.sportshoptest.Entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends JpaRepository<Review,Integer> {

    Page<Review> findAllByProductIdOrderByCreateTimeDesc(Integer productId,Pageable pageable);
    Review findByProductIdAndUserEmail(Integer productId, String userEmail);
}
