package com.example.spring_docker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.spring_docker.models.Cart;
import com.example.spring_docker.models.CartItem;
import com.example.spring_docker.models.Product;
import com.example.spring_docker.repositories.cartItemRepository;
import com.example.spring_docker.repositories.cartRepository;
import com.example.spring_docker.repositories.productRepository;



@Service
public class cartItemService {

    @Autowired
    private cartItemRepository cartItemRepository;

    @Autowired
    private productRepository productRepository;

    @Autowired
    private cartRepository cartRepository;

    public CartItem addItemToCart(Integer cartId, Integer productId, int quantity) {
        Cart cart = cartRepository.findById(cartId).get();
        Product product = productRepository.findById(productId).get();
        CartItem cartItem = cart.getItems().stream().filter(item -> item.getProduct().getId().equals(productId)).findFirst().orElse(new CartItem());
        if(cartItem.getId() == null){
            cartItem.setCart(cart);  
            cartItem.setProduct(product);
            cartItem.setPrice(product.getPrice());
            cartItem.setQuantity(quantity);
        }else{
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }
        cartItem.setTotalPrice();
        cart.addItems(cartItem);
        cartItemRepository.save(cartItem);
        cartRepository.save(cart);
        return cartItem;
    }
    
}
