package com.example.spring_docker.dtos;

import com.example.spring_docker.models.Role;

import lombok.Data;

@Data
public class tokenUser {
    private String name;
    private Integer id;
    private Role role;
    private String token;
}
