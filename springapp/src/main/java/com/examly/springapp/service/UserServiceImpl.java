package com.examly.springapp.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

/**
 * Implementation of UserService and UserDetailsService.
 * Handles user authentication, login, and registration logic.
 */
@Service
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepo repo;
    private final PasswordEncoder encoder;
    private final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    /**
     * Constructor-based dependency injection.
     *
     * @param repo Repository for User data persistence.
     * @param encoder Password encoder for secure password storage.
     */
    public UserServiceImpl(UserRepo repo, PasswordEncoder encoder) {
        this.repo = repo;
        this.encoder = encoder;
    }

    /**
     * Logs in a user by checking credentials.
     * 
     * @param user The user attempting to log in.
     * @return The LoginDTO containing user details.
     * @throws InvalidCredentialsException If the user is not found or credentials are invalid.
     */
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

    /**
     * Loads a user by their email.
     *
     * @param email The email address of the user.
     * @return UserDetails containing authentication details.
     * @throws UsernameNotFoundException If the user is not found.
     */

    public UserDetails loadUserByEmail(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return UserPrinciple.build(user);
    }

    /**
     * Registers a new user.
     * Checks for duplicate email before saving.
     *
     * @param user The user to be registered.
     * @return The registered User entity.
     * @throws DuplicateInvestmentException If the email is already registered.
     */
    
    public User registerUser(User user) {
        logger.info("Registering user with email: {}", user.getEmail());

        // Check for duplicate user registration
        if (repo.findByEmail(user.getEmail()) != null) {
            logger.error("Duplicate user registration attempted with email: {}", user.getEmail());
            throw new DuplicateInvestmentException("User with email " + user.getEmail() + " already exists!");
        }

        user.setPassword(encoder.encode(user.getPassword()));
        logger.info("User registered successfully with email: {}", user.getEmail());
        return repo.save(user);
    }

    /**
     * Loads a user by their username (email).
     *
     * @param email The email address used as the username.
     * @return UserDetails containing authentication details.
     * @throws UsernameNotFoundException If the user is not found.
     */
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        logger.info("Loading user by username (email): {}", email);

        User user = repo.findByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return UserPrinciple.build(user);
    }
}