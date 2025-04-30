package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.InvestmentInquiryException;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.InvestmentInquiry;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.InvestmentInquiryRepo;
import com.examly.springapp.repository.InvestmentRepo;
import com.examly.springapp.repository.UserRepo;

@Service
public class InvestmentInquiryServiceImpl implements InvestmentInquiryService{
    @Autowired
    InvestmentInquiryRepo investmentInquiryRepo;

    @Autowired
    UserRepo userRepo;

    @Autowired
    InvestmentRepo investmentRepo;

     //To add a Investment Inquiry
    public InvestmentInquiry createInquiry(InvestmentInquiry investmentinquiry) {
        User user = userRepo.findById(investmentinquiry.getUser().getUserId()).orElse(null);
        Investment investment=investmentRepo.findById(investmentinquiry.getInvestment().getInvestmentId()).orElse(null);
        if(user==null || investment==null){
            throw new InvestmentInquiryException("Investment Inquiry details not found!...");
        }
        investmentinquiry.setInquiryDate(LocalDate.now());
        investmentinquiry.setStatus("Pending");
        investmentinquiry.setUser(user);
        investmentinquiry.setInvestment(investment);
        return investmentInquiryRepo.save(investmentinquiry);
    }

    //To get a Inquiry by their Inquiry ID
    public List<InvestmentInquiry> getAllInquries() {
        List<InvestmentInquiry>list= investmentInquiryRepo.findAll();
        if(list.isEmpty()){
            throw new InvestmentInquiryException("There are no Inquries found!...");
        }
        return list;
    }

    //To get a list of Inquries
    public InvestmentInquiry getInquiryById(long inquiryId) {
        InvestmentInquiry investmentInquiry=investmentInquiryRepo.findById(inquiryId).orElse(null);
        if(investmentInquiry == null){
            throw new InvestmentInquiryException("Investment Inquiry is not found!....");
        }
        return investmentInquiry;
    }

    //To Update the Investment Inquiry
    public InvestmentInquiry updateInquiry(long inquiryId, InvestmentInquiry investmentinquiry) {
        InvestmentInquiry existinginvestmentinquiry=investmentInquiryRepo.findById(inquiryId).orElse(null);
        if(existinginvestmentinquiry==null){
            throw new InvestmentInquiryException("Investment Inquiry not found to update...");
        }
        existinginvestmentinquiry.setMessage(investmentinquiry.getMessage());
        existinginvestmentinquiry.setStatus(investmentinquiry.getStatus());
        existinginvestmentinquiry.setPriority(investmentinquiry.getPriority());
        //existinginvestmentinquiry.setResponseDate(LocalDateTime.now()); // Updating response date
        existinginvestmentinquiry.setAdminResponse(investmentinquiry.getAdminResponse());
        existinginvestmentinquiry.setContactDetails(investmentinquiry.getContactDetails());
        return investmentInquiryRepo.save(investmentinquiry);
    }

    //To Delete Inquiry by their ID
    public boolean deleteInquiry(long inquiryId) {
        if(investmentInquiryRepo.existsById(inquiryId)){
            investmentInquiryRepo.deleteById(inquiryId);
            return true;
        }
        return false;
    }

     //To get list of Inquries by User ID
    @Override
    public List<InvestmentInquiry> getInquiriesByUserId(long userId) {
        // Fetch the user and check if they exist
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new InvestmentInquiryException("User not found with ID: " + userId);
        }
        // Fetch investment inquiries related to the user
        List<InvestmentInquiry> inquiries = investmentInquiryRepo.findByUser_UserId(userId);
        if (inquiries.isEmpty()) {
            throw new InvestmentInquiryException("No investment inquiries found for user ID: " + userId);
        }
        return inquiries;
    }
    
}