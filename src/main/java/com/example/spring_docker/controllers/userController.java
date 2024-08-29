package com.example.spring_docker.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.spring_docker.models.User;
import com.example.spring_docker.services.userService;

@RestController
@RequestMapping("/api/v1/user")
public class userController {
    @Autowired
    private userService userService;

    @GetMapping()
    @PreAuthorize("hasAnyRole('ADMIN')")
    public ResponseEntity<List<User>> FindAllUsers(){
        List<User> users = userService.findAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/showme")
    public ResponseEntity<User> ShowMe(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User cUser = (User)authentication.getPrincipal();
        return ResponseEntity.ok(cUser);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> FindById(@PathVariable("id") Integer id){
        User user = userService.findById(id);
        return ResponseEntity.ok(user);
    }

    @PatchMapping("/updateUser")
    public ResponseEntity<User> UpdateUser(@RequestBody User input){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        String email = user.getEmail();
        User userUpdate = userService.updateUser(email, input);
        return ResponseEntity.ok(userUpdate);
    }

    @PatchMapping("/updatePassword")
    public ResponseEntity<String> UpdatePassword(@RequestBody User input){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        String email = user.getEmail();
        userService.updatePassword(input, email);
        return ResponseEntity.ok("Password is updated!");
    }
}
