package com.examly.springapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.DuplicateInvestmentException;
import com.examly.springapp.model.Investment;
import com.examly.springapp.repository.FeedbackRepo;
import com.examly.springapp.repository.InvestmentInquiryRepo;
import com.examly.springapp.repository.InvestmentRepo;


/**
 * Implementation of InvestmentService interface.
 * Handles business logic related to investment operations.
 */
@Service
public class InvestmentServiceImpl implements InvestmentService {
    private final InvestmentRepo investmentRepo;

    /**
     * Constructor-based dependency injection.
     *
     * @param investmentRepo Repository for Investment data persistence.
     */
    public InvestmentServiceImpl(InvestmentRepo investmentRepo) {
        this.investmentRepo = investmentRepo;
    }

    @Override
    public Investment addInvestment(Investment investment) {
        if (investmentRepo.existsById(investment.getInvestmentId())) {
            throw new DuplicateInvestmentException("Investment already exists with ID: " + investment.getInvestmentId());
        }
        return investmentRepo.save(investment);
    }

    @Override
    public Investment updateInvestment(long investmentId, Investment updateInvestment) {
        Investment existingInvestment = investmentRepo.findById(investmentId).orElse(null);
        if (existingInvestment == null) {
            return null;
        }
        updateInvestment.setInvestmentId(investmentId);
        return investmentRepo.save(updateInvestment);
    }

    @Override
    public Investment getInvestmentById(long investmentId) {
        return investmentRepo.findById(investmentId).orElse(null);
    }

    /**
     * Retrieves all investments with pagination support.
     *
     * @param pageable Pageable object for pagination.
     * @return A paginated list of investments.
     */
    @Override
    public List<Investment> getAllInvestments() {
        return investmentRepo.findAll();
    }

    @Override
    public Page<Investment> getInvestmentsByType(String type, Pageable pageable) {
        return investmentRepo.getInvestmentsByType(type, pageable);
    }

    @Override
    public Page<Investment> getInvestmentsByStatus(String status, Pageable pageable) {
        return investmentRepo.getInvestmentsByStatus(status, pageable);
    }

    @Override
    public boolean deleteInvestment(long investmentId) {
        Investment investment = investmentRepo.findById(investmentId).orElse(null);
        if (investment == null) {
            return false;
        }
        investmentRepo.delete(investment);
        return true;
    }
}