package com.examly.springapp.exceptions;

public class FeedbackException extends RuntimeException{
    public FeedbackException(String message){
        super(message);
    }
}
