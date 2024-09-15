package com.example.spring_docker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_docker.models.Review;
import com.example.spring_docker.models.User;
import com.example.spring_docker.requests.reviewRequest;
import com.example.spring_docker.services.reviewService;

@RestController
@RequestMapping("/api/v1/review")
public class reviewController {

    @Autowired
    private reviewService reviewService;

    @PostMapping()
    public ResponseEntity<Review> createReview(@RequestBody reviewRequest input){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Review nReview = reviewService.createReview(input, user);
        return ResponseEntity.status(201).body(nReview);
    }

    @GetMapping()
    public ResponseEntity<List<Review>> FindAllReviews(){
        List<Review> allReviews = reviewService.findAllReviews();
        return ResponseEntity.ok(allReviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> FindReviewID(@PathVariable Integer id){
        Review review = reviewService.findReviewByID(id);
        return ResponseEntity.ok(review);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> UpdateReview(@PathVariable Integer id, @RequestBody Review input){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Review reviewID = reviewService.findReviewByID(id);
        if(!user.equals(reviewID.getUser())){
           return ResponseEntity.status(403).body("Authorized to access route");
        }
        Review review = reviewService.UpdateReview(id, input);
        return ResponseEntity.ok(review);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> DeleteReview(@PathVariable Integer id){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        Review reviewID = reviewService.findReviewByID(id);
        if(!user.equals(reviewID.getUser())){
           return ResponseEntity.status(403).body("Authorized to access route");
        }
        reviewService.deleteReview(id);
        return ResponseEntity.ok("Delete review successfully!");
    }
}
