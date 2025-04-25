package com.examly.springapp.service;

import java.util.Collection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.examly.springapp.config.UserPrinciple;
import com.examly.springapp.exceptions.DuplicateInvestmentException;
import com.examly.springapp.exceptions.InvalidCredentialsException;
import com.examly.springapp.model.LoginDTO;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.UserRepo;
import com.examly.springapp.utility.UserMapper;

@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    UserRepo repo;
    @Autowired
    PasswordEncoder encoder;

    public LoginDTO loginUser(User user) {
      logger.info("Login attempt started for email: {}", user.getEmail());

      User existingUser = repo.findByEmail(user.getEmail());
      if (existingUser == null) {
          logger.error("User not found with email: {}", user.getEmail());
          throw new InvalidCredentialsException("Invalid login credentials!");
      }

      logger.info("Login successful for email: {}", user.getEmail());
      return UserMapper.mappedToLoginDTO(existingUser);
    }

    @Override
    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
      User user = repo.findByEmail(email);
      if(user==null)
         throw new UsernameNotFoundException("User not found");
      UserDetails u =UserPrinciple.build(user);
      return u;
    }

    // Business Logic to register a new User
    public User registerUser(User user) {
        logger.info("Registering user with email: {}", user.getEmail());
        System.out.println(user);
        // Check for duplicate user registration
        if (repo.findByEmail(user.getEmail()) != null) {
          logger.error("Duplicate user registration attempted with email: {}", user.getEmail());
          throw new DuplicateInvestmentException("User with email " + user.getEmail() + " already exists!");
        }
        logger.info("User registered successfully with email: {}", user.getEmail());
        user.setPassword(encoder.encode(user.getPassword()));
        System.out.println(user);
        return repo.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
      System.out.println("Email:"+ email);
      User user = repo.findByEmail(email);
      System.out.println("User="+user);
      if(user==null)
         throw new UsernameNotFoundException("User not found");
      UserDetails userDetails  =UserPrinciple.build(user);
      return userDetails;
    }
}