package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.examly.springapp.model.Investment;
import com.examly.springapp.service.InvestmentServiceImpl;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/investments")
public class InvestmentController {
    @Autowired
    InvestmentServiceImpl investmentServiceImpl;

    /**
     * Creates a new investment entry.
     * 
     * param investment The investment object received in request body.
     * return ResponseEntity containing the created investment with HTTP status 201 (Created).
     */
    @PostMapping
    public ResponseEntity<?> addInvestment(@Valid @RequestBody Investment investment){
        investment = investmentServiceImpl.addInvestment(investment);
        if(investment != null){
            return ResponseEntity.status(201).body(investment);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

     /**
     * Retrieves an investment by its unique ID.
     * 
     * param investmentId The ID of the investment to retrieve.
     * return ResponseEntity containing the investment object with HTTP status 200 (OK) if found, otherwise 403 (Forbidden).
     */
    @GetMapping("/{investmentId}")
    public ResponseEntity<?> getInvestmentById(@PathVariable long investmentId){
        Investment investment = investmentServiceImpl.getInvestmentById(investmentId);
        if(investment != null){
            return ResponseEntity.status(200).body(investment);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

    /**
     * Retrieves all investments from the database.
     * 
     * return ResponseEntity containing the list of investments with HTTP status 200 (OK), or 403 if the list is empty.
     */
    @GetMapping
    public ResponseEntity<?> getAllInvestments(){
        List<Investment> list = investmentServiceImpl.getAllInvestments();
        if(!list.isEmpty()){
            return ResponseEntity.status(200).body(list);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

     /**
     * Updates an existing investment record.
     * 
     * InvestmentId The ID of the investment to update.
     * Investment The updated investment details received in request body.
     * return ResponseEntity containing the updated investment object with HTTP status 200 (OK), or 403 if update fails.
     */
    @PutMapping("/{investmentId}")
    public ResponseEntity<?> updateInvestment( @PathVariable long investmentId, @Valid @RequestBody Investment investment){
        investment = investmentServiceImpl.updateInvestment(investmentId, investment);
        if(investment != null){
            return ResponseEntity.status(200).body(investment);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

    /**
     * Deletes an investment record by its ID.
     * 
     * InvestmentId The ID of the investment to delete.
     * return ResponseEntity indicating whether the deletion was successful or failed.
     */
    @DeleteMapping("/{investmentId}")
    public ResponseEntity<?> deleteInvestment(@PathVariable long investmentId){
        boolean result = investmentServiceImpl.deleteInvestment(investmentId);
        if(result){
            return ResponseEntity.status(200).body("Deleted successfully");
        }else{
            return ResponseEntity.status(403).body("Investment with " + investmentId + " not found");
        }
    }
}
