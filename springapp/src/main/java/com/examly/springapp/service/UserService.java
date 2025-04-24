package com.examly.springapp.service;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;

public interface UserService {
    public User registerUser(User user);
    public LoginDTO loginUser(User user);
}