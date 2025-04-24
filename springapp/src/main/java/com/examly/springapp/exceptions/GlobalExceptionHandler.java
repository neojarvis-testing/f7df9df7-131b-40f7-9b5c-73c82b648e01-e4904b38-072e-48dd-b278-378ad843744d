package com.examly.springapp.exceptions;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(DuplicateInvestmentException.class)
    public ResponseEntity<String>duplicateInvestmentMethod(DuplicateInvestmentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(InvestmentException.class)
    public ResponseEntity<String>investmentMethod(InvestmentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(InvestmentInquiryException.class)
    public ResponseEntity<String>investmentInquiryMethod(InvestmentInquiryException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String>invalidCredentialsMethod(InvalidCredentialsException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }
}