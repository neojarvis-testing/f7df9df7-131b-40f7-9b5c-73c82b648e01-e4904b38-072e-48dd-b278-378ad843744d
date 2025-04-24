package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Feedback;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.InvestmentRepo;
import com.examly.springapp.repository.UserRepo;



@Service
public class FeedbackServiceImpl implements FeedbackService{
    @Autowired
    FeedbackRepo feedbackRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    InvestmentRepo investmentRepo;

    @Override
    public Feedback createFeedback(Feedback feedback) {
        User user = userRepo.findById(feedback.getUser().getUserId()).orElse(null);
        Investment investment=investmentRepo.findById(feedback.getInvestment().getInvestmentId()).orElse(null);
        if(user==null || investment==null){
            return null;
        }
        feedback.setUser(user);
        feedback.setInvestment(investment);
        return feedbackRepo.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        return feedbackRepo.findById(feedbackId).orElse(null);
        
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
       return feedbackRepo.findAll();
    }

    @Override
    public boolean deleteFeedbackById(Long feedbackId) {
        if(feedbackRepo.existsById(feedbackId)){
            feedbackRepo.deleteById(feedbackId);
            return true;
        }
        return false;
    }

    @Override
    public List<Feedback> getFeedbacksByUserId(Long userId) {
         User user=userRepo.findById(userId).orElse(null);
        if(user!=null){
            return feedbackRepo.findByUser(user);
        }
        return null;
    }

    @Override
    public List<Feedback> getFeedbacksByInvestmentId(Long investmentId) {
        Investment investment=investmentRepo.findById(investmentId).orElse(null);
        if(investment!=null){
            return feedbackRepo.findByInvestment(investment);
        }
        return null;
    }
    
}
