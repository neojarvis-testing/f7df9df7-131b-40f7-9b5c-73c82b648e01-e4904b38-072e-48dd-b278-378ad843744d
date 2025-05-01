package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.FeedbackException;
import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.InvestmentRepo;
import com.examly.springapp.repository.UserRepo;

/**
 * Implementation of FeedbackService interface.
 * Provides business logic for handling feedback operations.
 */
@Service
public class FeedbackServiceImpl implements FeedbackService {

    private final FeedbackRepo feedbackRepo;
    private final UserRepo userRepo;
    private final InvestmentRepo investmentRepo;

    /**
     * Constructor-based dependency injection.
     *
     * @param feedbackRepo Repository for Feedback data persistence.
     * @param userRepo Repository for User data persistence.
     * @param investmentRepo Repository for Investment data persistence.
     */
    public FeedbackServiceImpl(FeedbackRepo feedbackRepo, UserRepo userRepo, InvestmentRepo investmentRepo) {
        this.feedbackRepo = feedbackRepo;
        this.userRepo = userRepo;
        this.investmentRepo = investmentRepo;
    }

    /**
     * Creates a new feedback entry.
     * Ensures the associated user and investment exist before saving.
     *
     * @param feedback The feedback object to be saved.
     * @return The saved Feedback entity.
     * @throws FeedbackException If user or investment details are missing.
     */
    @Override
    public Feedback createFeedback(Feedback feedback) {
        User user = userRepo.findById(feedback.getUser().getUserId()).orElse(null);
        Investment investment = investmentRepo.findById(feedback.getInvestment().getInvestmentId()).orElse(null);

        if (user == null || investment == null) {
            throw new FeedbackException("User or Investment details not found");
        }

        feedback.setDate(LocalDate.now());
        feedback.setUser(user);
        feedback.setInvestment(investment);
        return feedbackRepo.save(feedback);
    }

    /**
     * Retrieves a feedback entry by its unique ID.
     *
     * @param feedbackId The unique ID of the feedback.
     * @return The Feedback entity if found, otherwise null.
     */
    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        Feedback feedback = feedbackRepo.findById(feedbackId).orElse(null);
        return feedbackRepo.existsById(feedbackId) ? feedback : null;
    }

    /**
     * Retrieves all feedback entries.
     *
     * @return A list of all feedback records.
     * @throws FeedbackException If no feedback entries exist.
     */
    @Override
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list = feedbackRepo.findAll();
        if (list.isEmpty()) {
            throw new FeedbackException("No feedback entries found");
        }
        return list;
    }

    /**
     * Deletes a feedback entry by its unique ID.
     *
     * @param feedbackId The ID of the feedback to be deleted.
     * @return true if deletion was successful, false otherwise.
     */
    @Override
    public boolean deleteFeedbackById(Long feedbackId) {
        if (feedbackRepo.existsById(feedbackId)) {
            feedbackRepo.deleteById(feedbackId);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all feedback entries submitted by a specific user.
     *
     * @param userId The ID of the user whose feedback is being fetched.
     * @return A list of feedback associated with the given user.
     * @throws FeedbackException If the user does not exist.
     */
    @Override
    public List<Feedback> getFeedbacksByUserId(Long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new FeedbackException("Feedback with userId " + userId + " not found");
        }
        return feedbackRepo.findByUser(user);
    }

    /**
     * Retrieves all feedback entries linked to a specific investment.
     *
     * @param investmentId The ID of the investment associated with the feedback.
     * @return A list of feedback records related to the given investment.
     * @throws FeedbackException If the investment does not exist.
     */
    @Override
    public List<Feedback> getFeedbacksByInvestmentId(Long investmentId) {
        Investment investment = investmentRepo.findById(investmentId).orElse(null);
        if (investment == null) {
            throw new FeedbackException("Feedback with investment Id " + investmentId + " not found");
        }
        return feedbackRepo.findByInvestment(investment);
    }
}