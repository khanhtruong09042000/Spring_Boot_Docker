package com.example.spring_docker.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.spring_docker.models.User;
import com.example.spring_docker.repositories.userRepository;

@Service
public class authService {
    @Autowired
    private userRepository userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    public User regiser(User user) {
        User newUser = new User();
        newUser.setName(user.getName());
        newUser.setEmail(user.getEmail()); 
        newUser.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepo.save(newUser);
        return newUser;
    }

    public User login(User input) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(input.getEmail(), input.getPassword()));

        User user = userRepo.findByEmail(input.getEmail());
        return user;
    }


}
