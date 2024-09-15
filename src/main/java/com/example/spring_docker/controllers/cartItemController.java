package com.example.spring_docker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_docker.models.CartItem;
import com.example.spring_docker.models.User;
import com.example.spring_docker.services.cartItemService;
import com.example.spring_docker.services.cartService;

@RestController
@RequestMapping("/api/v1/cart-item")
public class cartItemController {
    @Autowired
    private cartItemService cartItemService;

    @Autowired
    private cartService cartService;

    @PostMapping("/add")
    public ResponseEntity<?> createCartItem(@RequestParam(required = false) Integer cartId, @RequestParam Integer productId, @RequestParam int quantity){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        if(cartId == null){
            cartId = cartService.createCart(user);
        }
        CartItem cartItem = cartItemService.addItemToCart(cartId, productId, quantity);
        return ResponseEntity.status(201).body(cartItem); 
    }
}
