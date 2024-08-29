package com.example.spring_docker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_docker.models.Product;
import com.example.spring_docker.repositories.productRepository;

@Service
public class productService {

    @Autowired
    private productRepository productRepository;

    public Product createProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }
    
}
