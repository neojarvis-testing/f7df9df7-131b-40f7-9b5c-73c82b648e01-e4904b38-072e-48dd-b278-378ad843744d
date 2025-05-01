package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.User;

/**
 * Repository interface for managing Feedback entity persistence.
 * Extends JpaRepository to provide CRUD operations.
 */
@Repository
public interface FeedbackRepo extends JpaRepository<Feedback, Long> {

    /**
     * Finds all feedback entries associated with a specific user.
     * 
     * @param user The user whose feedback records are being retrieved.
     * @return A list of feedback submitted by the given user.
     */
    List<Feedback> findByUser(User user);

    /**
     * Finds all feedback entries linked to a specific investment.
     * 
     * @param investment The investment associated with the feedback.
     * @return A list of feedback related to the given investment.
     */
    List<Feedback> findByInvestment(Investment investment);
}