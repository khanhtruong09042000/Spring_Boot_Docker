package com.example.spring_docker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.spring_docker.models.Product;
import com.example.spring_docker.models.Review;
import com.example.spring_docker.models.User;
import com.example.spring_docker.services.productService;
import com.example.spring_docker.services.reviewService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/product")
public class productController {
    @Autowired
    private productService productService;

    @Autowired
    private reviewService reviewService;

    @PostMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<Product> createProduct(@Valid @RequestBody Product product){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        product.setUser(user);
        Product newProduct = productService.createProduct(product);
        return ResponseEntity.status(201).body(newProduct);
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getProducts(){
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProductID(@PathVariable Integer id){
        Product product = productService.getProductID(id);
        return ResponseEntity.ok(product);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PatchMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer id, @Valid @RequestBody Product input){
        Product updateProduct = productService.updateProduct(id, input);
        return ResponseEntity.ok(updateProduct);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer id){
        productService.deleteProduct(id);
        return ResponseEntity.ok("This product deleted successfully!");
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<?> GetSingleProductReviews(@PathVariable Integer id){
        List<Review> lReviews = reviewService.findProductReviews(id);
        return ResponseEntity.ok(lReviews);
    }

    @PreAuthorize("hasAnyRole('ADMIN')")
    @PostMapping("/uploadImage")
    public ResponseEntity<?> UploadImage(@RequestParam MultipartFile file){
        System.out.println(file.getOriginalFilename());
        System.out.println(System.getProperty("user.dir"));
        return ResponseEntity.ok("Upload Image successfully!");
    }
}
