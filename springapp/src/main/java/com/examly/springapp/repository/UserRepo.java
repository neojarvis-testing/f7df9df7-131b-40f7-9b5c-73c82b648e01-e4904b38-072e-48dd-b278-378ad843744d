package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.User;

/**
 * Repository interface for managing User entity persistence.
 * Extends JpaRepository to provide CRUD operations for User.
 */
@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    /**
     * Retrieves a user by their email address.
     * 
     * @param email The email of the user to find.
     * @return The User entity matching the given email or null if not found.
     */
    @Query("select user from User user where user.email=?1")
    User findByEmail(String email);
}