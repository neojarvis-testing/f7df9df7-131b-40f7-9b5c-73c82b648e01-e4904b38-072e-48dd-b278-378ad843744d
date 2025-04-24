package com.examly.springapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.DuplicateInvestmentException;
import com.examly.springapp.exceptions.InvalidCredentialsException;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.utility.UserMapper;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;

    // Constructor-based injection
    @Autowired
    public UserServiceImpl(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Autowired
    PasswordEncoder encoder;

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    // Business Logic to register a new User
    public User registerUser(User user) {
        logger.info("Registering user with email: {}", user.getEmail());

        // Check for duplicate user registration
        if (userRepo.findByEmail(user.getEmail()) != null) {
            logger.error("Duplicate user registration attempted with email: {}", user.getEmail());
            throw new DuplicateInvestmentException("User with email " + user.getEmail() + " already exists!");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        logger.info("User registered successfully with email: {}", user.getEmail());
        return userRepo.save(user);
    }

    // Business Logic for User login
    public LoginDTO loginUser(User user) {
        logger.info("Login attempt started for email: {}", user.getEmail());

        User existingUser = userRepo.findByEmail(user.getEmail());
        if (existingUser == null) {
            logger.error("User not found with email: {}", user.getEmail());
            throw new InvalidCredentialsException("Invalid login credentials!");
        }

        logger.info("Login successful for email: {}", user.getEmail());
        return UserMapper.mappedToLoginDTO(existingUser);
    }

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserDetails user = userRepo.findByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user;
    }

}