package com.example.spring_docker.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.spring_docker.models.User;

@Repository
public interface userRepo extends JpaRepository<User, Integer>{
    
}
