package com.examly.springapp.service;

import org.springframework.security.core.userdetails.UserDetails;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;

public interface UserService {
    public UserDetails loadUserByEmail(String email);
     public LoginDTO loginUser(User user);
     public User registerUser(User user);
}