package com.sportshoptest.Service.impl;

import com.sportshoptest.Entity.Review;
import com.sportshoptest.Repository.ReviewRepository;
import com.sportshoptest.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewRepository reviewRepository;
    @Override
    public Page<Review> getReviewByProduct(Integer productId, Pageable pageable) {
        return reviewRepository.findAllByProductIdOrderByCreateTimeDesc(productId,pageable);
    }

    @Override
    public Review postReview(Review review) {
        return reviewRepository.save(review);
    }
}
