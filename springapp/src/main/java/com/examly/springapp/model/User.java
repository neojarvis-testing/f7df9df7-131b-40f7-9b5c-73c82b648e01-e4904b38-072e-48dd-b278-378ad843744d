package com.examly.springapp.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;             //Unique identifier for the user
    @Column(unique=true)
    private String email;           //User's Email Address must be unique for registration purpose
    private String password;        //User's Password
    private String username;        //User's username
    private String mobileNumber;    //User's Mobile Number
    private String userRole;        //Role of the user->ADMIN/USER
}