package com.examly.springapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class InvestmentInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long inquiryId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    User user;
    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = false)
    Investment investment;
    String message;
    String status;
    String priority;
    LocalDateTime inquiryDate;
    LocalDateTime responseDate;
    String adminResponse;
    String contactDetails;
    
}