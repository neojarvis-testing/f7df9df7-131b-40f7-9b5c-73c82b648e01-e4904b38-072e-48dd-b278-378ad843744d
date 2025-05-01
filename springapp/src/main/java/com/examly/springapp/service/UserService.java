package com.examly.springapp.service;

import org.springframework.security.core.userdetails.UserDetails;
 

public interface UserService {
    
    public UserDetails loadUserByEmail(String email);
}