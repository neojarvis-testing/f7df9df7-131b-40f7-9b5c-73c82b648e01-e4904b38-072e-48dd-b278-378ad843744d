package com.examly.springapp.exceptions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
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
    @ExceptionHandler(FeedbackException.class)
    public ResponseEntity<String>feedbackMethod(FeedbackException e){
        return ResponseEntity.status(403).body(e.getMessage());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(MethodArgumentNotValidException e) {
        List<FieldError> errors = e.getBindingResult().getFieldErrors();
        Map<String, String> map = new HashMap<>();
        for(FieldError err: errors){
            map.put(err.getField(), err.getDefaultMessage());
        }
        return ResponseEntity.status(400).body(map.toString());
    }
}