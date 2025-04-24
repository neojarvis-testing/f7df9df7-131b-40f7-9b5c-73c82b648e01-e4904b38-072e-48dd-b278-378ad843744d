package com.examly.springapp.model;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
@Entity
@Data
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long feedbackId;
    String feedbackText;
    String date;
    @ManyToOne
    @JoinColumn(name = "userId", nullable=false)
    User user;
    @ManyToOne
    @JoinColumn(name = "investmentId" ,nullable=true)
    Investment investment;
    String category;