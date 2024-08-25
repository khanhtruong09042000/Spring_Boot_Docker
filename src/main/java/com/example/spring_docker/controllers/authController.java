package com.example.spring_docker.controllers;

import java.net.HttpCookie;
import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

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

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
    public ResponseEntity<tokenUser> Login(@RequestBody User input, HttpServletResponse response){
        User authUser = authService.login(input);
        tokenUser oUser = new tokenUser();
        oUser.setId(authUser.getId());
        oUser.setName(authUser.getName());
        oUser.setRole(authUser.getRole());

        String jwtToken = jwtService.generateToken(authUser);
        oUser.setToken(jwtToken);

        Cookie cookie = new Cookie("accessToken", jwtToken);
        cookie.setHttpOnly(true);
        cookie.setMaxAge(jwtService.getExpTime());
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.status(200).body(oUser); 
    }

    @DeleteMapping("/logout")
    public ResponseEntity<String> Logout(HttpServletResponse response, HttpServletRequest request){
        Cookie[] cookies = request.getCookies();
        Cookie cookie = new Cookie("accessToken", "logout");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(1);
        cookie.setSecure(true);
        response.addCookie(cookie);

        return ResponseEntity.ok("Logout Successfully!");
    }
}
