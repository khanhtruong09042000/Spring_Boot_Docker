package com.example.spring_docker.requests;

import lombok.Data;

@Data
public class reviewRequest {
    private int rating;
    private String title;
    private String comment;
    private Integer product_id;
}
