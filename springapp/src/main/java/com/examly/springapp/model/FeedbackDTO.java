package com.examly.springapp.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FeedbackDTO {
    private String feedbackText;
    private LocalDate date;
    private long userId;
    private long investmentId;
    private String category;
    
}
