package com.example.spring_docker.dtos;

import lombok.Data;

@Data
public class tokenUser {
    private String token;
    private Long expiresIn;
}
