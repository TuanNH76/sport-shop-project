package com.sportshoptest.Controller;

import com.sportshoptest.Entity.Review;
import com.sportshoptest.Repository.ReviewRepository;
import com.sportshoptest.Service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@CrossOrigin
@RestController
public class ReviewController {
    @Autowired
    ReviewService reviewService;
    @Autowired
    ReviewRepository reviewRepository;
    @GetMapping("/review/show/{productId}")
    public Page<Review> getReviewByProduct(@PathVariable("productId") String productId,
                                            @RequestParam(value = "page", defaultValue = "1") Integer page,
                                           @RequestParam(value = "size", defaultValue = "6") Integer size){
        Integer productIdInt = Integer.parseInt(productId);
        PageRequest request = PageRequest.of(page - 1, size);
        return reviewService.getReviewByProduct(productIdInt,request);

    }

    @PostMapping("/review/post")
    public ResponseEntity<Review> postReview(@RequestBody Review review, Principal principal){
        review.setUserEmail(principal.getName());
        if(reviewRepository.findByProductIdAndUserEmail(review.getProductId(),review.getUserEmail()) != null){
                return ResponseEntity.badRequest().build();
        };
        return ResponseEntity.ok(reviewService.postReview(review));
    }
}
