package com.examly.springapp.model;

import java.time.LocalDate;
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

    private LocalDate inquiryDate;
    private LocalDate responseDate;

    @Size(max = 500, message = "Admin response cannot exceed 500 characters")
    private String adminResponse;

    // @NotBlank(message = "Contact details cannot be empty")
    private String contactDetails;
}