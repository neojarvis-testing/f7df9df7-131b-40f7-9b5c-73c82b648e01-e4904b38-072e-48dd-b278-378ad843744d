package com.examly.springapp.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.examly.springapp.model.Investment;

public interface InvestmentService {
    Investment addInvestment(Investment investment);
    Investment updateInvestment(long investmentId, Investment updateInvestment);
    Investment getInvestmentById(long investmentId);
    
    // Updated methods to support pagination
    List<Investment> getAllInvestments();
    Page<Investment> getInvestmentsByType(String type, Pageable pageable);
    Page<Investment> getInvestmentsByStatus(String status, Pageable pageable);
    
    boolean deleteInvestment(long investmentId);
}