package com.examly.springapp.config;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.examly.springapp.model.User;

/**
 * UserPrinciple implements Spring Security's UserDetails interface
 * to provide authentication and authorization details for a user.
 */
public class UserPrinciple implements UserDetails {
    
    private final User user;

    /**
     * Constructor to initialize UserPrinciple with a User object.
     * @param user the user entity containing authentication details.
     */
    public UserPrinciple(User user) {
        this.user = user;
    }

    /**
     * Factory method to create a UserPrinciple instance from a User entity.
     * @param user the user entity containing authentication details.
     * @return a new instance of UserPrinciple.
     */
    public static UserDetails build(User user) {
        return new UserPrinciple(user);
    }

    /**
     * Retrieves the authorities (roles) granted to the user.
     * @return a collection of granted authorities.
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(() -> "ROLE_" + user.getUserRole()); // Assign role dynamically
    }

    /**
     * Retrieves the password associated with the user.
     * @return the user's password.
     */
    @Override
    public String getPassword() {
        return user.getPassword();
    }

    /**
     * Retrieves the username (email) associated with the user.
     * @return the user's email address.
     */
    @Override
    public String getUsername() {
        return user.getEmail();
    }

    /**
     * Indicates whether the user's account is non-expired.
     * Always returns true, meaning accounts do not expire.
     * @return true (account never expires).
     */
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user's account is non-locked.
     * Always returns true, meaning accounts are never locked.
     * @return true (account is never locked).
     */
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    /**
     * Indicates whether the user's credentials (password) are non-expired.
     * Always returns true, meaning credentials never expire.
     * @return true (credentials never expire).
     */
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    /**
     * Indicates whether the user is enabled.
     * Always returns true, meaning all users are enabled by default.
     * @return true (user is enabled).
     */
    @Override
    public boolean isEnabled() {
        return true;
    }
}