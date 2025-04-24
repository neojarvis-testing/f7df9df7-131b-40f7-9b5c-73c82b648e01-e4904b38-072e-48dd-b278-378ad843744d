package com.examly.springapp.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class InvestmentInquiry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long inquiryId;
    @ManyToOne
    @JoinColumn(name = "userId", nullable = false)
    User user;
    @ManyToOne
    @JoinColumn(name = "investmentId", nullable = false)
    Investment investment;
    String message;
    String status;
    String priority;
    LocalDateTime inquiryDate;
    LocalDateTime responseDate;
    String adminResponse;
    String contactDetails;
    public long getInquiryId() {
        return inquiryId;
    }
    public void setInquiryId(long inquiryId) {
        this.inquiryId = inquiryId;
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
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getPriority() {
        return priority;
    }
    public void setPriority(String priority) {
        this.priority = priority;
    }
    public LocalDateTime getInquiryDate() {
        return inquiryDate;
    }
    public void setInquiryDate(LocalDateTime inquiryDate) {
        this.inquiryDate = inquiryDate;
    }
    public LocalDateTime getResponseDate() {
        return responseDate;
    }
    public void setResponseDate(LocalDateTime responseDate) {
        this.responseDate = responseDate;
    }
    public String getAdminResponse() {
        return adminResponse;
    }
    public void setAdminResponse(String adminResponse) {
        this.adminResponse = adminResponse;
    }
    public String getContactDetails() {
        return contactDetails;
    }
    public void setContactDetails(String contactDetails) {
        this.contactDetails = contactDetails;
    }
}