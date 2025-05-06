package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.service.FeedbackService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/feedback") // Base URL mapping for all feedback-related endpoints
@Tag(name = "Feedback Management", description = "APIs for managing feedback entries")
public class FeedbackController {

    @Autowired
    private FeedbackService feedbackService; // Injecting service layer for feedback operations

    /**
     * Create a new feedback entry.
     * 
     * @param feedback The feedback object to be created.
     * @return HTTP 201 (Created) response containing the saved feedback.
     */
    @Operation(summary = "Create Feedback", description = "Creates a new feedback entry with the provided details.")
    @PostMapping
    public ResponseEntity<Feedback> createFeedback(@Valid @RequestBody Feedback feedback) {
        feedback = feedbackService.createFeedback(feedback);
        return ResponseEntity.status(201).body(feedback);
    }

    /**
     * Retrieve a feedback entry by its ID.
     * 
     * @param feedbackId The ID of the feedback.
     * @return HTTP 200 (OK) if found, otherwise HTTP 404 (Not Found).
     */
    @Operation(summary = "Get Feedback by ID", description = "Retrieves feedback details by its unique ID.")
    @GetMapping("/{feedbackId}")
    public ResponseEntity<?> getFeedbackById(@PathVariable long feedbackId) {
        Feedback feedback = feedbackService.getFeedbackById(feedbackId);
        if (feedback != null) {
            return ResponseEntity.status(200).body(feedback);
        }
        return ResponseEntity.status(404).body("Feedback not found!"); // Improved null handling
    }

    /**
     * Retrieve all feedback entries.
     * 
     * @return HTTP 200 (OK) response containing a list of feedbacks.
     */
    @Operation(summary = "Get All Feedbacks", description = "Retrieves all feedback entries available.")
    @GetMapping
    public ResponseEntity<?> getAllFeedbacks() {
        List<Feedback> list = feedbackService.getAllFeedbacks();
        return ResponseEntity.status(200).body(list);
    }

    /**
     * Retrieve all feedback entries submitted by a specific user.
     * 
     * @param userId The ID of the user.
     * @return HTTP 200 (OK) response containing the list of feedbacks by the user.
     */
    @Operation(summary = "Get Feedbacks by User ID", description = "Retrieves all feedback entries submitted by a specific user.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFeedbacksByUserId(@PathVariable Long userId) {
        List<Feedback> list = feedbackService.getFeedbacksByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }

    /**
     * Delete a feedback entry by its ID.
     * 
     * @param feedbackId The ID of the feedback to be deleted.
     * @return HTTP 200 (OK) if deleted successfully, otherwise HTTP 404 (Not Found).
     */
    @Operation(summary = "Delete Feedback by ID", description = "Deletes a feedback entry by its unique ID.")
    @DeleteMapping("/{feedbackId}")
    public ResponseEntity<?> deleteFeedbackById(@PathVariable long feedbackId) {
        boolean result = feedbackService.deleteFeedbackById(feedbackId);
        if (result) {
            return ResponseEntity.status(200).body(null);
        }
        return ResponseEntity.status(404).body(null);
    }
}