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

/**
 * Investment entity represents an investment record.
 * It includes details such as name, type, purchase price, current price, and quantity.
 */
@Entity
@Getter
@Setter
public class Investment {

    /**
     * Primary key for the investment entity.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long investmentId;

    /**
     * Name of the investment.
     * Must not be blank and cannot exceed 30 characters.
     */
    @NotBlank(message = "Investment name cannot be blank")
    @Size(max = 30, message = "Investment name cannot exceed 30 characters")
    private String name;

    /**
     * Description of the investment.
     * Must not be blank and cannot exceed 100 characters.
     */
    @NotBlank(message = "Investment description cannot be blank")
    @Size(max = 100, message = "Investment description cannot exceed 100 characters")
    private String description;

    /**
     * Type of investment (e.g., stocks, real estate, etc.).
     * Must not be blank.
     */
    @NotBlank(message = "Investment type cannot be blank")
    private String type;

    /**
     * Purchase price of the investment.
     * Must be a positive number and cannot be null.
     */
    @NotNull(message = "Purchase Price Cannot be null")
    @Positive(message = "Purchase Price must be positive")
    private double purchasePrice;

    /**
     * Current price of the investment.
     * Must be a positive number and cannot be null.
     */
    @NotNull(message = "Current Price Cannot be null")
    @Positive(message = "Current Price must be positive")
    private double currentPrice;

    /**
     * Quantity of investment holdings.
     * Must be a positive number and cannot be null.
     */
    @NotNull(message = "Investment Quantity Cannot be null")
    @Positive(message = "Investment Quantity must be positive")
    private int quantity;

    /**
     * Date when the investment was purchased.
     * Must not be blank.
     * Uncomment validation if limiting length is required.
     */
    @NotBlank(message = "Purchase Date cannot be blank")
    // @Size(max = 10, message = "Purchase Date cannot exceed 10 characters")
    private String purchaseDate;

    /**
     * Status of the investment (e.g., Active, Sold, Pending).
     * Must not be blank and cannot exceed 100 characters.
     */
    @NotBlank(message = "Investment Status cannot be blank")
    @Size(max = 100, message = "Investment Status cannot exceed 100 characters")
    private String status;
}