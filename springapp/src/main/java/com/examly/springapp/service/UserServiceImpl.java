package com.examly.springapp.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.utility.UserMapper;
@Service
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;

    //Constructor based Injection
    @Autowired
    public UserServiceImpl(UserRepo userRepo){
        this.userRepo=userRepo;
    }
    @Autowired
    PasswordEncoder encoder;

    //Business Logic to register a new User
    public User registerUser(User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    //Business Logic for User login
    public LoginDTO loginUser(User user) {
        user =  userRepo.findByEmail(user.getEmail());
        return UserMapper.mappedToLoginDTO(user);
    }
}
