package com.example.spring_docker.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_docker.models.Product;
import com.example.spring_docker.models.Review;

@Repository
public interface reviewRepository extends JpaRepository<Review, Integer>{
    List<Review> findByProduct(Product product);
}
