package com.examly.springapp.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.ToString;

@Entity
@Data // Generates getters, setters, toString, equals, and hashCode
@ToString
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId; // Unique identifier for the user

    @Column(unique = true)
    @Email(message = "Invalid email format")
    @NotBlank(message = "Email must not be blank")
    private String email; // User's email address must be unique for registration

    @NotBlank(message = "Password must not be blank")
    private String password; // User's password

    @NotBlank(message = "Username must not be blank")
    private String username; // User's username

    @NotBlank(message = "Mobile number must not be blank")
    private String mobileNumber; // User's mobile number

    @NotBlank(message = "User role must not be blank")
    private String userRole; // Role of the user -> ADMIN/USER
}