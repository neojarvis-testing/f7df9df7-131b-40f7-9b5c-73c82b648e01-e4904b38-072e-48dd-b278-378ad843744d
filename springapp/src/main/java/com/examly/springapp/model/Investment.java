package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
@Entity
@Getter
@Setter
public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long investmentId;
    @NotBlank(message = "Investment name cannot be blank")
    @Size(max = 30, message = "Investment name cannot exceed 30 characters")
    String name;
    @NotBlank(message = "Investment description cannot be blank")
    @Size(max = 100, message = "Investment description cannot exceed 100 characters")
    String description;
    @NotBlank(message = "Investment type cannot be blank")
    String type;
    @NotNull(message = "Purchase Price Cannot be null")
    @Positive(message = "Purchase Price must be positive")
    double purchasePrice;
    @NotNull(message = "Current Price Cannot be null")
    @Positive(message = "Current Price must be positive")
    double currentPrice;
    @NotNull(message = "Investment Quantity Cannot be null")
    @Positive(message = "Investment Quantity must be positive")
    int quantity;
    @NotBlank(message = "Purchase Date cannot be blank")
    @Size(max = 10, message = "Purchase Date cannot exceed 10 characters")
    String purchaseDate;
    @NotBlank(message = "Investment Status cannot be blank")
    @Size(max = 100, message = "Investment Status  cannot exceed 100 characters")
    String status;
}