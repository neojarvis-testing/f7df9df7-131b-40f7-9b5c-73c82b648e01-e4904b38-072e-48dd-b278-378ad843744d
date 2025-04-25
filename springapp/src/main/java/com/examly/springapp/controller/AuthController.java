package com.examly.springapp.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.examly.springapp.config.JwtUtils;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.service.UserServiceImpl;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api")
public class AuthController {
    @Autowired
    UserServiceImpl service;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtlis;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody User user){
        user = service.registerUser(user);
        return ResponseEntity.status(201).body(true);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getEmail(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtlis.genrateToken(authentication);
        LoginDTO loginDTO = service.loginUser(user);
        loginDTO.setToken(token);
        return ResponseEntity.status(200).body(loginDTO);
    }
}