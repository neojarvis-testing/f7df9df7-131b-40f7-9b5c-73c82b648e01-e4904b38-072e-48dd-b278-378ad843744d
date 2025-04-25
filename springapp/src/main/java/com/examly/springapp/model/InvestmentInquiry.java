package com.examly.springapp.model;

import java.time.LocalDateTime;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;

@Entity
@Data
public class InvestmentInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long inquiryId;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @NotNull(message = "User cannot be null")
    private User user;

    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = false)
    @NotNull(message = "Investment cannot be null")
    private Investment investment;

    @NotBlank(message = "Message cannot be empty")
    @Size(max = 500, message = "Message cannot exceed 500 characters")
    private String message;

    @NotBlank(message = "Status cannot be empty")
    private String status;

    @NotBlank(message = "Priority cannot be empty")
    private String priority;

    @PastOrPresent(message = "Inquiry date cannot be in the future")
    private LocalDateTime inquiryDate;

    @PastOrPresent(message = "Response date cannot be in the future")
    private LocalDateTime responseDate;

    @Size(max = 500, message = "Admin response cannot exceed 500 characters")
    private String adminResponse;

    @NotBlank(message = "Contact details cannot be empty")
    @Pattern(regexp = "^[0-9]{10}$", message = "Contact details must be a valid 10-digit phone number")
    private String contactDetails;
}