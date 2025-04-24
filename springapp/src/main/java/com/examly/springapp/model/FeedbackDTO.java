package com.examly.springapp.model;

import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class FeedbackDTO {
    private String feedbackText;
    private String date;
    private long userId;
    private long investmentId;
    private String category;
    
}
