package com.sportshoptest.Service;

import com.sportshoptest.Entity.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ReviewService {
    Page<Review> getReviewByProduct(Integer productId, Pageable pageable);
    Review postReview(Review review);
}
