package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.Investment;

public interface InvestmentService {
    Investment addInvestment(Investment investment);
    Investment updateInvestment(long investmentId, Investment updateInvestment);
    Investment getInvestmentById(long investmentId);
    List<Investment> getAllInvestments();
    List<Investment> getInvestmentsByType(String type);
    List<Investment> getInvestmentsByStatus(String status);
    List<Investment> searchInvestments(String keyword);
    boolean deleteInvestment(long investmentId);
}
