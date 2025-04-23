package com.examly.springapp.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
@Entity
public class Feedback {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long feedbackId;
    String feedbackText;
    String date;
    @ManyToOne
    @JoinColumn(name = "userId", nullable=false)
    User user;
    @ManyToOne
    @JoinColumn(name = "investmentId" ,nullable=true)
    Investment investment;
    String category;
    public long getFeedbackId() {
        return feedbackId;
    }
    public void setFeedbackId(long feedbackId) {
        this.feedbackId = feedbackId;
    }
    public String getFeedbackText() {
        return feedbackText;
    }
    public void setFeedbackText(String feedbackText) {
        this.feedbackText = feedbackText;
    }
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public User getUser() {
        return user;
    }
    public void setUser(User user) {
        this.user = user;
    }
    public Investment getInvestment() {
        return investment;
    }
    public void setInvestment(Investment investment) {
        this.investment = investment;
    }
    public String getCategory() {
        return category;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public Feedback() {
    }
    public Feedback(int feedbackId, String feedbackText, String date, User user, Investment investment,
            String category) {
        this.feedbackId = feedbackId;
        this.feedbackText = feedbackText;
        this.date = date;
        this.user = user;
        this.investment = investment;
        this.category = category;
    }
    

}
