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

@RestController
@RequestMapping("/api")
public class AuthController {

    // private final UserServiceImpl userService;

    // //Constructor based Injection
    // @Autowired
    // public AuthController(UserServiceImpl userService){
    //     this.userService=userService;
    // }
    // @Autowired
    // AuthenticationManager authenticationManager;
    // @Autowired
    // JwtUtils jwtUtils;
    /* 
    //EndPoint to register a new user
    @PostMapping("/register")
    public ResponseEntity<?>userRegister(@RequestBody User user){
        user = userService.registerUser(user);
        if(user != null){
            return ResponseEntity.status(201).body("registered");
        }else{
            return ResponseEntity.status(400).body(null);
        }  
    }
    @PostMapping("/registers")
    public ResponseEntity<User>userRegisters(@RequestBody User user){
        user = userService.registerUser(user);
        return ResponseEntity.status(201).body(user);
    }
    */
    //EndPoint for user login
    // @PostMapping("/login")
    // public ResponseEntity<LoginDTO>loginUser(@RequestBody User user){
    //     LoginDTO loginDTO  = userService.loginUser(user);
    //     if(loginDTO != null){
    //         return ResponseEntity.status(200).body(loginDTO);
    //     }else{
    //         return ResponseEntity.status(400).body(null);
    //     }
    // }

    /*
    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User user){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtUtils.generateToken(authentication);
        LoginDTO loginDTO = userService.loginUser(user);
        loginDTO.setToken(token);
        return ResponseEntity.status(201).body(loginDTO);
    }
    */
    //  @PostMapping("/logins")
    // public ResponseEntity<User>loginUsers(@RequestBody User user){
    //     user = userService.loginUser(user);
    //     user.setPassword(null);
    //     return ResponseEntity.status(201).body(user);
    // }
    @Autowired
    UserServiceImpl service;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    JwtUtils jwtUtlis;
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody User user){
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