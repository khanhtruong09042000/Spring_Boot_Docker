package com.example.spring_docker.models;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.example.spring_docker.enums.Category;
import com.example.spring_docker.enums.Company;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer id;

    @Column(nullable = true)
    private String name;
    private double price = 0;

    @Column(nullable = true, length = 1000)
    private String descrition;
    private String image = "";

    @Column(nullable = true)
    private Category category;

    @Column(nullable = true)
    private Company company;
    private String color = "#222";
    private Boolean featured = false;
    private Boolean freeShipping = false;
    private int inventory = 15;
    private int averageRating = 0;
    private int numOfReviews = 0;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User user;

    @CreationTimestamp
    private Date createAt;

    @UpdateTimestamp
    private Date updateAt;
}
