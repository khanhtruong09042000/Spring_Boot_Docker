package com.example.spring_docker.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_docker.models.User;
import com.example.spring_docker.repositories.userRepository;

@Service
public class userService {
    @Autowired
    private userRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<User> findAllUsers() {
        List<User> users = new ArrayList<>();
        userRepo.findAll().forEach(users::add);
        return users;
    }

    public User findById(Integer id) {
        User user = userRepo.findById(id).get();
        return user;
    }

    public User updateUser(String email, User input) {
        User user = userRepo.findByEmail(email);
        user.setName(input.getName());
        userRepo.save(user);
        return user;
    }

    public void updatePassword(User input, String email) {
        User user = userRepo.findByEmail(email);
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        userRepo.save(user);
    }
    
}
