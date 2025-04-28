package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/feedback") // Base URL mapping for all feedback-related endpoints
public class FeedbackController {

    @Autowired
    private FeedbackServiceImpl feedbackServiceImpl; // Injecting service layer for feedback operations

    /**
     * Create a new feedback entry.
     * 
     * feedback The feedback object to be created.
     * return HTTP 201 (Created) response containing the saved feedback.
     */
    @PostMapping
    public ResponseEntity<?> createFeedback(@Valid @RequestBody Feedback feedback) {
        feedback = feedbackServiceImpl.createFeedback(feedback);
        return ResponseEntity.status(201).body(feedback);
    }

    /**
     * Retrieve a feedback entry by its ID.
     * 
     * feedbackId The ID of the feedback.
     * return HTTP 200 (OK) if found, otherwise HTTP 404 (Not Found).
     */
    @GetMapping("/{feedbackId}")
    public ResponseEntity<?> getFeedbackById(@PathVariable long feedbackId) {
        Feedback feedback = feedbackServiceImpl.getFeedbackById(feedbackId);
        if (feedback != null) {
            return ResponseEntity.status(200).body(feedback);
        }
        return ResponseEntity.status(404).body("Feedback not found!"); // Improved null handling
    }

    /**
     * Retrieve all feedback entries.
     * 
     * return HTTP 200 (OK) response containing a list of feedbacks.
     */
    @GetMapping
    public ResponseEntity<?> getAllFeedbacks() {
        List<Feedback> list = feedbackServiceImpl.getAllFeedbacks();
        return ResponseEntity.status(200).body(list);
    }

    /**
     * Retrieve all feedback entries submitted by a specific user.
     * 
     * userId The ID of the user.
     * return HTTP 200 (OK) response containing the list of feedbacks by the user.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFeedbacksByUserId(@PathVariable Long userId) {
        List<Feedback> list = feedbackServiceImpl.getFeedbacksByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }

    /**
     * Delete a feedback entry by its ID.
     * 
     * feedbackId The ID of the feedback to be deleted.
     * return HTTP 200 (OK) if deleted successfully, otherwise HTTP 404 (Not Found).
     */
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<?> deleteFeedbackById(@PathVariable long feedbackId) {
        boolean result = feedbackServiceImpl.deleteFeedbackById(feedbackId);
        if (result) {
            return ResponseEntity.status(200).body("Feedback with ID " + feedbackId + " deleted successfully.");
        }
        return ResponseEntity.status(404).body("Feedback with ID " + feedbackId + " not found!");
    }
}