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

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    public ResponseEntity<?> addInvestment(@Valid @RequestBody Investment investment){
        investment = investmentServiceImpl.addInvestment(investment);
        if(investment != null){
            return ResponseEntity.status(201).body(investment);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

    @GetMapping("/{investmentId}")
    public ResponseEntity<?> getInvestmentById(@PathVariable long investmentId){
        Investment investment = investmentServiceImpl.getInvestmentById(investmentId);
        if(investment != null){
            return ResponseEntity.status(200).body(investment);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

    @GetMapping
    public ResponseEntity<?> getAllInvestments(){
        List<Investment> list = investmentServiceImpl.getAllInvestments();
        if(!list.isEmpty()){
            return ResponseEntity.status(200).body(list);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

    @PutMapping("/{investmentId}")
    public ResponseEntity<?> updateInvestment( @PathVariable long investmentId, @Valid @RequestBody Investment investment){
        investment = investmentServiceImpl.updateInvestment(investmentId, investment);
        if(investment != null){
            return ResponseEntity.status(200).body(investment);
        }else{
            return ResponseEntity.status(403).body(null);
        }
    }

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
