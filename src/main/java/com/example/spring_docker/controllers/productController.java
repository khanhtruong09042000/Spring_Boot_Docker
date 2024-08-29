package com.example.spring_docker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_docker.models.Product;
import com.example.spring_docker.models.User;
import com.example.spring_docker.services.productService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class productController {
    @Autowired
    private productService productService;

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        product.setUser(user);
        Product newProduct = productService.createProduct(product);
        return ResponseEntity.status(201).body(newProduct);
    }
}
