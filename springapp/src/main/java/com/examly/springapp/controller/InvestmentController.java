package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Investment;
import com.examly.springapp.service.InvestmentServiceImpl;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/investments")
@Tag(name = "Investment Management", description = "APIs for managing investment records")
public class InvestmentController {
    @Autowired
    InvestmentServiceImpl investmentServiceImpl;

    /**
     * Creates a new investment entry.
     * 
     * @param investment The investment object received in the request body.
     * @return ResponseEntity containing the created investment with HTTP status 201 (Created).
     */
    @Operation(summary = "Create Investment", description = "Creates a new investment entry with the provided details.")
    @PostMapping
    public ResponseEntity<?> addInvestment(@Valid @RequestBody Investment investment) {
        investment = investmentServiceImpl.addInvestment(investment);
        if (investment != null) {
            return ResponseEntity.status(201).body(investment);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    /**
     * Retrieves an investment by its unique ID.
     * 
     * @param investmentId The ID of the investment to retrieve.
     * @return ResponseEntity containing the investment object with HTTP status 200 (OK) if found, otherwise 403 (Forbidden).
     */
    @Operation(summary = "Get Investment by ID", description = "Retrieves investment details by its unique ID.")
    @GetMapping("/{investmentId}")
    public ResponseEntity<?> getInvestmentById(@PathVariable long investmentId) {
        Investment investment = investmentServiceImpl.getInvestmentById(investmentId);
        if (investment != null) {
            return ResponseEntity.status(200).body(investment);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    /**
     * Retrieves all investments from the database.
     * 
     * @return ResponseEntity containing the list of investments with HTTP status 200 (OK), or 403 if the list is empty.
     */
    @Operation(summary = "Get All Investments", description = "Retrieves all investment entries from the database.")
    @GetMapping
    public ResponseEntity<?> getAllInvestments() {
        List<Investment> list = investmentServiceImpl.getAllInvestments();
        if (!list.isEmpty()) {
            return ResponseEntity.status(200).body(list);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    /**
     * Updates an existing investment record.
     * 
     * @param investmentId The ID of the investment to update.
     * @param investment   The updated investment details received in the request body.
     * @return ResponseEntity containing the updated investment object with HTTP status 200 (OK), or 403 if the update fails.
     */
    @Operation(summary = "Update Investment", description = "Updates an existing investment record with the provided details.")
    @PutMapping("/{investmentId}")
    public ResponseEntity<?> updateInvestment(@PathVariable long investmentId, @Valid @RequestBody Investment investment) {
        investment = investmentServiceImpl.updateInvestment(investmentId, investment);
        if (investment != null) {
            return ResponseEntity.status(200).body(investment);
        } else {
            return ResponseEntity.status(403).body(null);
        }
    }

    /**
     * Deletes an investment record by its ID.
     * 
     * @param investmentId The ID of the investment to delete.
     * @return ResponseEntity indicating whether the deletion was successful or failed.
     */
    @Operation(summary = "Delete Investment", description = "Deletes an investment record by its unique ID.")
    @DeleteMapping("/{investmentId}")
    public ResponseEntity<?> deleteInvestment(@PathVariable long investmentId) {
        boolean result = investmentServiceImpl.deleteInvestment(investmentId);
        if (result) {
            return ResponseEntity.status(200).body("Deleted successfully");
        } else {
            return ResponseEntity.status(403).body("Investment with " + investmentId + " not found");
        }
    }
}