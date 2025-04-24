package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Feedback;

public interface FeedbackService {
    public Feedback createFeedback(Feedback feedback);
    public Feedback getFeedbackById(Long feedbackId);
    public List<Feedback> getAllFeedbacks();
    public boolean deleteFeedbackById(Long feedbackId);
    public List<Feedback> getFeedbacksByUserId(Long userId);
    public List<Feedback> getFeedbacksByInvestmentId(Long investmentId);
}
