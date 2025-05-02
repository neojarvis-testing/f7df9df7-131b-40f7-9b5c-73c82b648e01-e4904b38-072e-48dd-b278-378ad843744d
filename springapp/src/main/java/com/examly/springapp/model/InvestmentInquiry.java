package com.examly.springapp.model;

import java.time.LocalDate;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

/**
 * InvestmentInquiry entity represents inquiries made by users regarding investments.
 * It includes details such as message content, priority, status, and timestamps.
 */
@Entity
@Data
public class InvestmentInquiry {

    /**
     * Primary key for the inquiry table, auto-generated.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inquiryId;

    /**
     * Many-to-One relationship with User.
     * Ensures each inquiry is associated with a valid user.
     */
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @NotNull(message = "User cannot be null")
    private User user;

    /**
     * Many-to-One relationship with Investment.
     * Ensures each inquiry is linked to an existing investment.
     */
    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = false)
    @NotNull(message = "Investment cannot be null")
    private Investment investment;

    /**
     * Inquiry message provided by the user.
     * Must not be empty and should not exceed 500 characters.
     */
    @NotBlank(message = "Message cannot be empty")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;

    /**
     * Status of the inquiry (e.g., Pending, Resolved).
     * Must not be empty.
     */
    @NotBlank(message = "Status cannot be empty")
    private String status;

    /**
     * Priority level of the inquiry (e.g., High, Medium, Low).
     * Must not be empty.
     */
    @NotBlank(message = "Priority cannot be empty")
    private String priority;

    /**
     * Timestamp indicating when the inquiry was made.
     */
    private LocalDate inquiryDate;

    /**
     * Timestamp indicating when the inquiry was responded to.
     */
    private LocalDate responseDate;

    /**
     * Response from the admin addressing the inquiry.
     * Should not exceed 500 characters.
     */
    @Size(max = 500, message = "Admin response cannot exceed 500 characters")
    private String adminResponse;

    /**
     * Contact details provided by the user.
     * Could be useful for follow-ups, but validation is commented out for flexibility.
     */
    // @NotBlank(message = "Contact details cannot be empty")
    private String contactDetails;
}