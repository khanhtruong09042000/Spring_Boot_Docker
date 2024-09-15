package com.example.spring_docker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_docker.models.Product;
import com.example.spring_docker.models.Review;
import com.example.spring_docker.models.User;
import com.example.spring_docker.repositories.productRepository;
import com.example.spring_docker.repositories.reviewRepository;
import com.example.spring_docker.requests.reviewRequest;

@Service
public class reviewService {
    @Autowired
    private reviewRepository reviewRepository;

    @Autowired
    private productRepository productRepository;

    public Review createReview(reviewRequest input, User user) {
        Review review = new Review();
        review.setRating(input.getRating());
        review.setComment(input.getComment());
        review.setTitle(input.getTitle());
        review.setUser(user);

        Product product = productRepository.findById(input.getProduct_id()).get();
        review.setProduct(product);
        reviewRepository.save(review);

        List<Review> listReviews = reviewRepository.findByProduct(product);
        product.setAverageRating(calculateAverageRating(listReviews));
        product.setNumOfReviews(listReviews.size());
        productRepository.save(product);

        return review;
    }

    public List<Review> findAllReviews() {
        List<Review> lReviews = reviewRepository.findAll();
        return lReviews;
    }

    private int calculateAverageRating(List<Review> listReviews){
        int totalAverage = 0;
        int x = 0;
        for(int i = 0; i < listReviews.size(); i++){
            x += listReviews.get(i).getRating();
        }
        totalAverage = x / listReviews.size();
        return totalAverage;
    }

    public Review findReviewByID(Integer id) {
        Review review = reviewRepository.findById(id).get();
        return review;
    }

    public Review UpdateReview(Integer id, Review input) {
        Review review = reviewRepository.findById(id).get();
        review.setComment(input.getComment());
        review.setRating(input.getRating());
        review.setTitle(input.getTitle());
        reviewRepository.save(review);

        Product product = productRepository.findById(review.getProduct().getId()).get();

        List<Review> listReviews = reviewRepository.findByProduct(product);
        product.setAverageRating(calculateAverageRating(listReviews));
        productRepository.save(product);

        return review;
    }

    public void deleteReview(Integer id) {
        Review review = reviewRepository.findById(id).get();

        Product product = productRepository.findById(review.getProduct().getId()).get();

        reviewRepository.deleteById(id);

        List<Review> listReviews = reviewRepository.findByProduct(product);
        product.setAverageRating(calculateAverageRating(listReviews));
        product.setNumOfReviews(listReviews.size());
        productRepository.save(product);

    }

    public List<Review> findProductReviews(Integer id) {
        Product product = productRepository.findById(id).get();
        List<Review> lReviews = reviewRepository.findByProduct(product);
        return lReviews;
    }

}
