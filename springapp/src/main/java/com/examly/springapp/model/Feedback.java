package com.examly.springapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long feedbackId;

    @NotBlank(message = "Feedback text cannot be blank")
    @Size(max = 500, message = "Feedback text cannot exceed 500 characters")
    private String feedbackText;

    @PastOrPresent(message = "Feedback date cannot be future")
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    @NotNull(message = "User cannot be null")
    private User user;

    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = true)
    private Investment investment;

    @NotBlank(message = "Category cannot be blank")
    private String category;
}
