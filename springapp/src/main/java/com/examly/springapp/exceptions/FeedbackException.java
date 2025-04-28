package com.examly.springapp.exceptions;

import org.springframework.http.HttpStatus;

public class FeedbackException extends RuntimeException{
    public FeedbackException(String message){
        super(message);
    }
}
