package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.InvestmentInquiry;
import com.examly.springapp.service.InvestmentInquiryServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/inquiries")
@Tag(name = "Investment Inquiry Management", description = "APIs for managing investment inquiries")
public class InvestmentInquiryController {

    @Autowired
    InvestmentInquiryServiceImpl investmentinquiryService;

    /**
     * Creates a new investment inquiry.
     * 
     * @param investmentinquiry The investment inquiry object received in the request body.
     * @return ResponseEntity containing the created inquiry with HTTP status 201 (Created).
     */
    @Operation(summary = "Create Investment Inquiry", description = "Creates a new investment inquiry with the provided details.")
    @PostMapping
    public ResponseEntity<InvestmentInquiry> createInquiry(@Valid @RequestBody InvestmentInquiry investmentinquiry) {
        investmentinquiry = investmentinquiryService.createInquiry(investmentinquiry);
        return ResponseEntity.status(201).body(investmentinquiry);
    }

    /**
     * Retrieves all investment inquiries.
     * 
     * @return ResponseEntity containing the list of all inquiries with HTTP status 200 (OK).
     */
    @Operation(summary = "Get All Investment Inquiries", description = "Retrieves all investment inquiries from the database.")
    @GetMapping
    public ResponseEntity<List<InvestmentInquiry>> getAllInquries() {
        List<InvestmentInquiry> list = investmentinquiryService.getAllInquries();
        return ResponseEntity.status(200).body(list);
    }

    /**
     * Retrieves an inquiry by its unique inquiry ID.
     * 
     * @param inquiryId The ID of the inquiry to retrieve.
     * @return ResponseEntity containing the inquiry object with HTTP status 200 (OK).
     */
    @Operation(summary = "Get Investment Inquiry by ID", description = "Retrieves the details of an investment inquiry by its unique ID.")
    @GetMapping("/{inquiryId}")
    public ResponseEntity<InvestmentInquiry> getInquiryById(@PathVariable long inquiryId) {
        InvestmentInquiry investmentInquiry = investmentinquiryService.getInquiryById(inquiryId);
        return ResponseEntity.status(200).body(investmentInquiry);
    }

    /**
     * Retrieves all inquiries related to a specific user ID.
     * 
     * @param userId The user ID for which inquiries should be fetched.
     * @return ResponseEntity containing the list of inquiries associated with the given user ID.
     */
    @Operation(summary = "Get Investment Inquiries by User ID", description = "Retrieves all investment inquiries associated with a specific user ID.")
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<InvestmentInquiry>> getInquiriesByUserId(@PathVariable long userId) {
        List<InvestmentInquiry> list = investmentinquiryService.getInquiriesByUserId(userId);
        return ResponseEntity.status(200).body(list);
    }

    /**
     * Updates an existing investment inquiry.
     * 
     * @param inquiryId The ID of the inquiry to update.
     * @param investmentinquiry The updated inquiry details received in the request body.
     * @return ResponseEntity containing the updated inquiry object with HTTP status 200 (OK).
     */
    @Operation(summary = "Update Investment Inquiry", description = "Updates the details of an existing investment inquiry.")
    @PutMapping("/{inquiryId}")
    public ResponseEntity<InvestmentInquiry> updateInquiry(@PathVariable long inquiryId, @Valid @RequestBody InvestmentInquiry investmentinquiry) {
        InvestmentInquiry existinginvestmentinquiry = investmentinquiryService.updateInquiry(inquiryId, investmentinquiry);
        return ResponseEntity.status(200).body(existinginvestmentinquiry);
    }

    /**
     * Deletes an inquiry by its ID.
     * 
     * @param inquiryId The ID of the inquiry to delete.
     * @return ResponseEntity indicating whether the deletion was successful or failed.
     */
    @Operation(summary = "Delete Investment Inquiry", description = "Deletes an investment inquiry by its unique ID.")
    @DeleteMapping("/{inquiryId}")
    public ResponseEntity<?> deleteInquiry(@PathVariable long inquiryId) {
        boolean result = investmentinquiryService.deleteInquiry(inquiryId);
        if (result) {
            return ResponseEntity.status(200).body("Inquiry deleted successfully...");
        } else {
            return ResponseEntity.status(403).body("Inquiry ID does not exist!...");
        }
    }
}