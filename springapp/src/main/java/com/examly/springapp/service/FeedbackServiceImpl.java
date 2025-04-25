package com.examly.springapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.FeedbackException;
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
            throw new FeedbackException("User or Investment details not found");
        }
        feedback.setDate(LocalDateTime.now());
        feedback.setUser(user);
        feedback.setInvestment(investment);
        return feedbackRepo.save(feedback);
    }

    @Override
    public Feedback getFeedbackById(Long feedbackId) {
        Feedback feedback= feedbackRepo.findById(feedbackId).orElse(null); 
        if(feedback==null){
            throw new FeedbackException("Feedback id is invalid");
        } 
        return feedback;
    }

    @Override
    public List<Feedback> getAllFeedbacks() {
        List<Feedback> list=feedbackRepo.findAll();
        if(list.isEmpty()){
            throw new FeedbackException("No feedback entries found");
        }
       return list;
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
        if(user==null){
            throw new FeedbackException("Feedback with userId "+userId+" not found");
        }
        return feedbackRepo.findByUser(user);
    }

    @Override
    public List<Feedback> getFeedbacksByInvestmentId(Long investmentId) {
        Investment investment=investmentRepo.findById(investmentId).orElse(null);
        if(investment==null){
            throw new FeedbackException("Feedback with investment Id "+investmentId+" not found");
        }
        return feedbackRepo.findByInvestment(investment);
    }
    
}
