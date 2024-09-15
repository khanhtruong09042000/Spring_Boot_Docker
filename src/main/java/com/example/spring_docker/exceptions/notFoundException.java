package com.example.spring_docker.exceptions;

public class notFoundException extends RuntimeException{
    public notFoundException(String message){
        super(message);
    }
}
