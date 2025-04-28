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

     /**
     * Handles DuplicateInvestmentException, which occurs when an investment already exists.
     * 
     * The DuplicateInvestmentException instance containing the error message.
     * return ResponseEntity with HTTP status 400 (Bad Request) and the exception message.
     */
    @ExceptionHandler(DuplicateInvestmentException.class)
    public ResponseEntity<String>duplicateInvestmentMethod(DuplicateInvestmentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

     /**
     * Handles InvestmentException, which may occur during various investment-related operations.
     * 
     * The InvestmentException instance containing the error message.
     * return ResponseEntity with HTTP status 400 (Bad Request) and the exception message.
     */
    @ExceptionHandler(InvestmentException.class)
    public ResponseEntity<String>investmentMethod(InvestmentException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

     /**
     * Handles InvestmentInquiryException, which occurs when an investment inquiry fails.
     * Typically, this exception might be thrown due to unauthorized access or invalid inquiries.
     * 
     * The InvestmentInquiryException instance containing the error message.
     * return ResponseEntity with HTTP status 400 (Bad Request) and the exception message.
     */
    @ExceptionHandler(InvestmentInquiryException.class)
    public ResponseEntity<String>investmentInquiryMethod(InvestmentInquiryException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

     /**
     * Handles InvalidCredentialsException, which occurs when incorrect login credentials are provided.
     * 
     * The InvalidCredentialsException instance containing the error message.
     * return ResponseEntity with HTTP status 400 (Bad Request) and the exception message.
     */

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<String>invalidCredentialsMethod(InvalidCredentialsException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

     /**
     * Handles MethodArgumentNotValidException, which occurs when validation errors happen
     * in request parameters annotated with @Valid.
     * 
     * The MethodArgumentNotValidException instance containing validation errors.
     * return ResponseEntity with HTTP status 400 (Bad Request) and a map of field errors.
     */
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