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


@RestController
@RequestMapping("/api/inquiries")
public class InvestmentInquiryController {

    @Autowired
    InvestmentInquiryServiceImpl investmentinquiryService;

    @PostMapping
    public ResponseEntity<?> createInquiry(@RequestBody InvestmentInquiry investmentinquiry){
        investmentinquiry=investmentinquiryService.createInquiry(investmentinquiry);
        if(investmentinquiry!=null){
            return ResponseEntity.status(201).body(investmentinquiry);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }
    
    @GetMapping
    public ResponseEntity<?> getAllInquries(){
        List<InvestmentInquiry> list=investmentinquiryService.getAllInquries();
        if(!list.isEmpty()){
            return ResponseEntity.status(200).body(list);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    @GetMapping("/{inquiryId}")
    public ResponseEntity<?> getInquiryById(@PathVariable long inquiryId){
        InvestmentInquiry investmentInquiry=investmentinquiryService.getInquiryById(inquiryId);
        if(investmentInquiry!=null){
            return ResponseEntity.status(200).body(investmentInquiry);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getInquiriesByUserId(@PathVariable long userId){
        List<InvestmentInquiry> list=investmentinquiryService.getInquiriesByUserId(userId);
        if(!list.isEmpty()){
            return ResponseEntity.status(200).body(list);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    @PutMapping("/{inquiryId}")
    public ResponseEntity<?> updateInquiry(@PathVariable long inquiryId,@RequestBody InvestmentInquiry investmentinquiry){
        InvestmentInquiry existinginvestmentinquiry=investmentinquiryService.updateInquiry(inquiryId, investmentinquiry);
        if(existinginvestmentinquiry!=null){
            return ResponseEntity.status(200).body(existinginvestmentinquiry);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }
    
    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<?> deleteInquiry(@PathVariable long inquiryId){
        boolean result=investmentinquiryService.deleteInquiry(inquiryId);
        if(result){
            return ResponseEntity.status(200).body(result);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

}
