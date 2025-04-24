package com.examly.springapp.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.InvestmentInquiry;
import com.examly.springapp.repository.InvestmentInquiryRepo;

@Service
public class InvestmentInquiryServiceImpl implements InvestmentInquiryService{
    @Autowired
    InvestmentInquiryRepo investmentInquiryRepo;

    public InvestmentInquiry createInquiry(InvestmentInquiry investmentinquiry) {
        investmentinquiry.setInquiryDate(LocalDateTime.now());
        investmentinquiry.setStatus("Enquiry");
        return investmentInquiryRepo.save(investmentinquiry);
    }

    public List<InvestmentInquiry> getAllInquries() {
        return investmentInquiryRepo.findAll();
    }

    public InvestmentInquiry getInquiryById(long inquiryId) {
        return investmentInquiryRepo.findById(inquiryId).orElse(null);
    }

    public InvestmentInquiry updateInquiry(long inquiryId, InvestmentInquiry investmentinquiry) {
        InvestmentInquiry existinginvestmentinquiry=investmentInquiryRepo.findById(inquiryId).orElse(null);
        if(existinginvestmentinquiry==null){
            return null;
        }
        investmentinquiry.setInquiryId(inquiryId);
        return investmentInquiryRepo.save(investmentinquiry);
    }

    public boolean deleteInquiry(long inquiryId) {
        if(investmentInquiryRepo.existsById(inquiryId)){
            investmentInquiryRepo.deleteById(inquiryId);
            return true;
        }
        return false;
    }
}