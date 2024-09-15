package com.example.spring_docker.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_docker.models.Product;
import com.example.spring_docker.models.Review;
import com.example.spring_docker.repositories.productRepository;
import com.example.spring_docker.repositories.reviewRepository;

@Service
public class productService {

    @Autowired
    private productRepository productRepository;

    @Autowired
    private reviewRepository reviewRepository;

    public Product createProduct(Product product) {
        Product newProduct = productRepository.save(product);
        return newProduct;
    }

    public List<Product> getProducts() {
        List<Product> products = productRepository.findAll();
        return products;
    }

    public Product getProductID(Integer id) {
        Product product = productRepository.findById(id).get();
        return product;
    }

    public Product updateProduct(Integer id, Product input) {
        Product product = productRepository.findById(id).get();
        product.setPrice(input.getPrice());
        product.setFreeShipping(input.getFreeShipping());
        product.setName(input.getName());
        product.setDescrition(input.getDescrition());
        product.setImage(input.getImage());
        product.setCategory(input.getCategory());
        product.setCompany(input.getCompany());
        product.setColor(input.getColor());
        product.setFeatured(input.getFeatured());
        product.setInventory(input.getInventory());
        productRepository.save(product);
        return product;
    }

    public void deleteProduct(Integer id) {
        Product product = productRepository.findById(id).get();
        List<Review> lReviews = reviewRepository.findByProduct(product);
        for(int i = 0; i < lReviews.size(); i++){
            reviewRepository.deleteById(lReviews.get(i).getId());
        }
        productRepository.deleteById(id);
    }
    
}
