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

@RestController
@RequestMapping("/api/feedback")
public class FeedbackController {
    @Autowired
    FeedbackServiceImpl feedbackServiceImpl;
    @PostMapping
    public ResponseEntity<?> createFeedback(@RequestBody Feedback feedback){
        Feedback feedback2=feedbackServiceImpl.createFeedback(feedback);
        if(feedback2!=null){
            return ResponseEntity.status(201).body(feedback2);
        }
        else{
            return ResponseEntity.status(403).body(null);
        }
        
    }
    @GetMapping("/{feedbackId}")
    public ResponseEntity<?> getFeedbackById(@PathVariable long feedbackId){
        Feedback feedback=feedbackServiceImpl.getFeedbackById(feedbackId);
        if(feedback!=null){
            return ResponseEntity.status(200).body(feedback);
        }
        else{
        return ResponseEntity.status(404).body(null);
        }
    }
    @GetMapping
    public ResponseEntity<?> getAllFeedbacks(){
        List<Feedback> list=feedbackServiceImpl.getAllFeedbacks();
        if(!list.isEmpty()){
            return ResponseEntity.status(200).body(list);
        }
        else{
            return ResponseEntity.status(400).body(null);
        }
        
    }
    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getFeedbacksByUserId(@PathVariable Long userId){
        List<Feedback> list=feedbackServiceImpl.getFeedbacksByUserId(userId);
        if(!list.isEmpty()){
            return ResponseEntity.status(200).body(list);
        }
        else{
         return ResponseEntity.status(404).body(list);
        }
    }
    @DeleteMapping("/{feebackId}")
    public ResponseEntity<?> deleteFeedbackById(@PathVariable long feebackId){
        boolean result=feedbackServiceImpl.deleteFeedbackById(feebackId);
        if(result){
            return ResponseEntity.status(200).body(result);
        }
        else{
            return ResponseEntity.status(404).body(null);
        }

    }
}