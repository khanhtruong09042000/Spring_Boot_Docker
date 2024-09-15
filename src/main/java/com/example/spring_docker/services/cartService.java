package com.example.spring_docker.services;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_docker.models.Cart;
import com.example.spring_docker.models.User;
import com.example.spring_docker.repositories.cartRepository;

@Service
public class cartService {

    @Autowired
    private cartRepository cartRepository;

    private AtomicInteger cartIdInteger = new AtomicInteger(0);

    public Integer createCart(User user) {
        Cart cart = new Cart();
        Integer cart_id = cartIdInteger.incrementAndGet();
        cart.setId(cart_id);
        cart.setUser(user);
        cartRepository.save(cart);
        return cart_id;
    }
    
}
