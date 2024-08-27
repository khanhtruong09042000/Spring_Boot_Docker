package com.example.spring_docker.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_docker.dtos.tokenUser;
import com.example.spring_docker.models.User;
import com.example.spring_docker.services.authService;
import com.example.spring_docker.services.jwtService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/auth")
public class authController {
    @Autowired
    private authService authService;

    @Autowired
    private jwtService jwtService;

    @PostMapping("/register")
    public ResponseEntity<User> Register(@Valid @RequestBody User user){
        User newUser = authService.regiser(user);
        return ResponseEntity.status(201).body(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<tokenUser> Login(@RequestBody User input){
        User authUser = authService.login(input);
        tokenUser oUser = new tokenUser();
        String jwtToken = jwtService.generateToken(authUser);
        oUser.setToken(jwtToken);
        oUser.setExpiresIn(jwtService.getExpTime());

        return ResponseEntity.status(200).body(oUser); 
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> Logout(){
        return ResponseEntity.ok("Logout Successfully!");
    }
}
