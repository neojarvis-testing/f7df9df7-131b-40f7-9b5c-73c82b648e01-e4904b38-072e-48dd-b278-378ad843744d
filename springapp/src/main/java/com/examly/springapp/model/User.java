package com.examly.springapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;            // Unique identifier for the user
    @Column(unique=true)
    private String email;           // User's email address must be unique for registration
    private String password;        // User's password
    private String username;        // User's username
    private String mobileNumber;    // User's mobile number
    private String userRole;        // Role of the user -> ADMIN/USER
}