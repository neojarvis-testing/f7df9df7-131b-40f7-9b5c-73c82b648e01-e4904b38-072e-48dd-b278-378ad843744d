package com.examly.springapp.model;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class InvestmentInquiryDTO {
     private long userId;
     private long investmentId;
     private String message;
     private String priority;
     private String contactDetails;
    
}
