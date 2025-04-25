package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.InvestmentInquiry;
import com.examly.springapp.service.InvestmentInquiryServiceImpl;

import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/inquiries")
public class InvestmentInquiryController {

    @Autowired
    InvestmentInquiryServiceImpl investmentinquiryService;

    //To add a nre Invest Inquiry
    @PostMapping
    public ResponseEntity<InvestmentInquiry> createInquiry(@Valid @RequestBody InvestmentInquiry investmentinquiry){
        investmentinquiry=investmentinquiryService.createInquiry(investmentinquiry);
        return ResponseEntity.status(201).body(investmentinquiry);
    }
    
    //To get a list of Inquries
    @GetMapping
    public ResponseEntity<List<InvestmentInquiry>> getAllInquries(){
        List<InvestmentInquiry> list=investmentinquiryService.getAllInquries();
        return ResponseEntity.status(200).body(list);
    }

    //To get a Inquiry by their Inquiry ID
    @GetMapping("/{inquiryId}")
    public ResponseEntity<InvestmentInquiry> getInquiryById(@PathVariable long inquiryId){
        InvestmentInquiry investmentInquiry=investmentinquiryService.getInquiryById(inquiryId);
        return ResponseEntity.status(200).body(investmentInquiry);
    }

    //To get list of Inquries by User ID
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvestmentInquiry>> getInquiriesByUserId(@PathVariable long userId){
        List<InvestmentInquiry> list=investmentinquiryService.getInquiriesByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }

    //To Update the Investment Inquiry
    @PutMapping("/{inquiryId}")
    public ResponseEntity<InvestmentInquiry> updateInquiry(@PathVariable long inquiryId,@Valid @RequestBody InvestmentInquiry investmentinquiry){
        InvestmentInquiry existinginvestmentinquiry=investmentinquiryService.updateInquiry(inquiryId, investmentinquiry);
        return ResponseEntity.status(200).body(existinginvestmentinquiry);
    }
    
    //To Delete Inquiry by their ID
    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<?> deleteInquiry(@PathVariable long inquiryId){
        boolean result=investmentinquiryService.deleteInquiry(inquiryId);
        if(result){
            return ResponseEntity.status(200).body("Inquiry deleted successfully...");
        } else {
            return ResponseEntity.status(403).body("Inquiry ID does not exist!...");
        }
    }

}
