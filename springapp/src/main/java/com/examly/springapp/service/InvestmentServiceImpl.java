package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.DuplicateInvestmentException;
import com.examly.springapp.model.Investment;
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

    /**
     * Creates a new investment entry.
     * Ensures the investment does not already exist before saving.
     *
     * @param investment The Investment entity to be saved.
     * @return The saved Investment entity.
     * @throws DuplicateInvestmentException If an investment with the same ID already exists.
     */
    @Override
    public Investment addInvestment(Investment investment) {
        if (investmentRepo.existsById(investment.getInvestmentId())) {
            throw new DuplicateInvestmentException("Investment already exists with ID: " + investment.getInvestmentId());
        }
        return investmentRepo.save(investment);
    }

    /**
     * Updates an existing investment record.
     * If investment does not exist, returns null.
     *
     * @param investmentId The ID of the investment to update.
     * @param updateInvestment The updated Investment details.
     * @return The updated Investment entity or null if the investment does not exist.
     */
    @Override
    public Investment updateInvestment(long investmentId, Investment updateInvestment) {
        Investment existingInvestment = investmentRepo.findById(investmentId).orElse(null);
        if (existingInvestment == null) {
            return null;
        }
        updateInvestment.setInvestmentId(investmentId);
        return investmentRepo.save(updateInvestment);
    }

    /**
     * Retrieves an investment by its unique ID.
     *
     * @param investmentId The ID of the investment.
     * @return The Investment entity if found, otherwise null.
     */
    @Override
    public Investment getInvestmentById(long investmentId) {
        return investmentRepo.findById(investmentId).orElse(null);
    }

    /**
     * Retrieves all investments from the database.
     *
     * @return A list of all Investment entities.
     */
    @Override
    public List<Investment> getAllInvestments() {
        return investmentRepo.findAll();
    }

    /**
     * Retrieves a list of investments by their type.
     *
     * @param type The type of investments to fetch.
     * @return A list of investments matching the given type.
     */
    @Override
    public List<Investment> getInvestmentsByType(String type) {
        return investmentRepo.getInvestmentsByType(type);
    }

    /**
     * Retrieves a list of investments by their status.
     *
     * @param status The status of investments to fetch.
     * @return A list of investments matching the given status.
     */
    @Override
    public List<Investment> getInvestmentsByStatus(String status) {
        return investmentRepo.getInvestmentsByStatus(status);
    }

    /**
     * Searches for investments based on a given keyword.
     * Matches investments whose attributes contain the keyword.
     *
     * @param keyword The search term.
     * @return A list of investments matching the keyword.
     */
    @Override
    public List<Investment> searchInvestments(String keyword) {
        List<Investment> list = getAllInvestments();
        List<Investment> listWithKeyword = new ArrayList<>();
        
        if (keyword == null) {
            return listWithKeyword;
        }

        for (Investment investment : list) {
            String value = investment.toString();
            if (value.toLowerCase().contains(keyword.toLowerCase())) {
                listWithKeyword.add(investment);
            }
        }
        return listWithKeyword;
    }

    /**
     * Deletes an investment record by its unique ID.
     *
     * @param investmentId The ID of the investment to delete.
     * @return true if deletion was successful, false otherwise.
     */
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