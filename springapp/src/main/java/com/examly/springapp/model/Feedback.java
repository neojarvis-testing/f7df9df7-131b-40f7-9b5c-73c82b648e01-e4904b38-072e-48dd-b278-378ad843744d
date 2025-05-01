package com.examly.springapp.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * Feedback entity represents feedback submitted by a user.
 * It includes details like feedback message, associated user, investment, date, and category.
 */
@Entity
@Data
public class Feedback {

    /**
     * Primary key for the feedback entity.
     * Auto-generated using identity strategy.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackId;

    /**
     * Feedback message provided by the user.
     * Must not be blank and should not exceed 500 characters.
     */
    @NotBlank(message = "Feedback text cannot be blank")
    @Size(max = 500, message = "Feedback text cannot exceed 500 characters")
    private String feedbackText;

    /**
     * Date when the feedback was submitted.
     * Must be in the past or present (cannot be a future date).
     */
    @PastOrPresent(message = "Feedback date cannot be future")
    private LocalDate date;

    /**
     * Many-to-One relationship with User.
     * Ensures feedback is linked to a valid user.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @NotNull(message = "User cannot be null")
    private User user;

    /**
     * Many-to-One relationship with Investment.
     * Feedback can optionally be linked to an investment.
     * If null, it indicates general feedback not related to a specific investment.
     */
    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = true)
    private Investment investment;

    /**
     * Category of the feedback (e.g., Complaint, Suggestion, Inquiry).
     * Must not be blank.
     */
    @NotBlank(message = "Category cannot be blank")
    private String category;
}