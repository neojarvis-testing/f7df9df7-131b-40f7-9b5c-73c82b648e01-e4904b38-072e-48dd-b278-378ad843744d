package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.User;

@Repository
public interface FeedbackRepo extends JpaRepository<Feedback,Long>{
    List<Feedback> findByUser(User user);
    List<Feedback> findByInvestment(Investment investment);

    
}
