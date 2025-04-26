package com.examly.springapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api") // Base URL mapping for authentication-related endpoints
public class AuthController {
    @Autowired
    private UserServiceImpl service; // Service layer for user operations
    @Autowired
    private AuthenticationManager authenticationManager; // Manages authentication process
    @Autowired
    private JwtUtils jwtUtils; // Utility class for JWT generation

    /**
     * Register a new user.
     * 
     * user The user object containing registration details.
     * return HTTP 201 (Created) response with registration status.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user) {
        try {
            user = service.registerUser(user);
            return ResponseEntity.status(201).body(user); // Returning created user instead of 'true' for better API clarity
        } catch (Exception e) {
            return ResponseEntity.status(400).body("Registration failed: " + e.getMessage()); // Handling potential errors
        }
    }

    /**
     * Authenticate and log in a user.
     * 
     * user The user object containing login credentials.
     * return HTTP 200 (OK) response with login details and JWT token.
     */
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user) {
        try {
            // Authenticate user credentials
            Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));

            SecurityContextHolder.getContext().setAuthentication(authentication);

            String token = jwtUtils.genrateToken(authentication); 

            LoginDTO loginDTO = service.loginUser(user);
            loginDTO.setToken(token);

            return ResponseEntity.ok(loginDTO); // Returning proper HTTP 200 response with login details
        } catch (Exception e) {
            return ResponseEntity.status(401).body("Invalid credentials"); // Returning appropriate status for failed login
        }
    }
}