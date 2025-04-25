package com.examly.springapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId; // Unique identifier for the user

    @Column(unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    private String email; // User's email address must be unique for registration

    @NotBlank(message = "Password must not be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password; // User's password

    @NotBlank(message = "Username must not be blank")
    @Size(max = 50, message = "Username must not exceed 50 characters")
    private String username; // User's username

    @NotBlank(message = "Mobile number must not be blank")
    @Pattern(regexp = "/^[0-9]{10}$", message = "Mobile number must be exactly 10 digits")
    private String mobileNumber; // User's mobile number

    @NotBlank(message = "User role must not be blank")
    @Pattern(regexp = "ADMIN|USER", message = "User role must be either ADMIN or USER")
    private String userRole; // Role of the user -> ADMIN/USER
}