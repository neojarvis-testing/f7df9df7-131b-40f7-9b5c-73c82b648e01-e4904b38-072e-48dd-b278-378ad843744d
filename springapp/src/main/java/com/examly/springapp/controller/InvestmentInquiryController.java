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

     /**
     * Creates a new investment inquiry.
     * 
     * Investmentinquiry The investment inquiry object received in request body.
     * return ResponseEntity containing the created inquiry with HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<InvestmentInquiry> createInquiry(@Valid @RequestBody InvestmentInquiry investmentinquiry){
        investmentinquiry=investmentinquiryService.createInquiry(investmentinquiry);
        return ResponseEntity.status(201).body(investmentinquiry);
    }
    
    /**
     * Retrieves all investment inquiries.
     * 
     * return ResponseEntity containing the list of all inquiries with HTTP status 200 (OK).
     */
    @GetMapping
    public ResponseEntity<List<InvestmentInquiry>> getAllInquries(){
        List<InvestmentInquiry> list=investmentinquiryService.getAllInquries();
        return ResponseEntity.status(200).body(list);
    }


    /**
     * Retrieves an inquiry by its unique inquiry ID.
     * 
     * InquiryId The ID of the inquiry to retrieve.
     * return ResponseEntity containing the inquiry object with HTTP status 200 (OK).
     */
    @GetMapping("/{inquiryId}")
    public ResponseEntity<InvestmentInquiry> getInquiryById(@PathVariable long inquiryId){
        InvestmentInquiry investmentInquiry=investmentinquiryService.getInquiryById(inquiryId);
        return ResponseEntity.status(200).body(investmentInquiry);
    }

    /**
     * Retrieves all inquiries related to a specific user ID.
     * 
     * UserId The user ID for which inquiries should be fetched.
     * return ResponseEntity containing the list of inquiries associated with the given user ID.
     */
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvestmentInquiry>> getInquiriesByUserId(@PathVariable long userId){
        List<InvestmentInquiry> list=investmentinquiryService.getInquiriesByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }

    /**
     * Updates an existing investment inquiry.
     * 
     * InquiryId The ID of the inquiry to update.
     * Investmentinquiry The updated inquiry details received in request body.
     * return ResponseEntity containing the updated inquiry object with HTTP status 200 (OK).
     */
    @PutMapping("/{inquiryId}")
    public ResponseEntity<InvestmentInquiry> updateInquiry(@PathVariable long inquiryId,@Valid @RequestBody InvestmentInquiry investmentinquiry){
        InvestmentInquiry existinginvestmentinquiry=investmentinquiryService.updateInquiry(inquiryId, investmentinquiry);
        return ResponseEntity.status(200).body(existinginvestmentinquiry);
    }
    
    /**
     * Deletes an inquiry by its ID.
     * 
     * InquiryId The ID of the inquiry to delete.
     * return ResponseEntity indicating whether the deletion was successful or failed.
     */
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
