package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackServiceImpl feedbackServiceImpl;
    @PostMapping
    public ResponseEntity<?> createFeedback(@Valid @RequestBody Feedback feedback){
            feedback=feedbackServiceImpl.createFeedback(feedback);
            return ResponseEntity.status(201).body(feedback);
    }
    @GetMapping("/{feedbackId}")
    public ResponseEntity<?> getFeedbackById(@PathVariable long feedbackId){
        Feedback feedback=feedbackServiceImpl.getFeedbackById(feedbackId);
            return ResponseEntity.status(200).body(feedback);
    }
    @GetMapping
    public ResponseEntity<?> getAllFeedbacks(){
        List<Feedback> list=feedbackServiceImpl.getAllFeedbacks();
            return ResponseEntity.status(200).body(list);
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFeedbacksByUserId(@PathVariable Long userId){
        List<Feedback> list=feedbackServiceImpl.getFeedbacksByUserId(userId);
            return ResponseEntity.status(200).body(list); 
    }
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<?> deleteFeedbackById(@PathVariable long feedbackId){
        boolean result=feedbackServiceImpl.deleteFeedbackById(feedbackId);
        if(result){
            return ResponseEntity.status(200).body("Feedback with ID "+feedbackId+" deleted Successfully.");
        }
        else{
            return ResponseEntity.status(404).body("Feedback with ID "+feedbackId+" not found!");
        }

    }
}