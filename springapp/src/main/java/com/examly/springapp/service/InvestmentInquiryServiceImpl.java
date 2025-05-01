package com.examly.springapp.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.InvestmentInquiryException;
import com.examly.springapp.model.Investment;
import com.examly.springapp.model.InvestmentInquiry;
import com.examly.springapp.model.User;
import com.examly.springapp.repository.InvestmentInquiryRepo;
import com.examly.springapp.repository.InvestmentRepo;
import com.examly.springapp.repository.UserRepo;

/**
 * Implementation of InvestmentInquiryService interface.
 * Handles business logic for managing investment inquiries.
 */
@Service
public class InvestmentInquiryServiceImpl implements InvestmentInquiryService {

    private final InvestmentInquiryRepo investmentInquiryRepo;
    private final UserRepo userRepo;
    private final InvestmentRepo investmentRepo;

    /**
     * Constructor-based dependency injection.
     *
     * @param investmentInquiryRepo Repository for Investment Inquiry persistence.
     * @param userRepo Repository for User data persistence.
     * @param investmentRepo Repository for Investment data persistence.
     */
    public InvestmentInquiryServiceImpl(InvestmentInquiryRepo investmentInquiryRepo, UserRepo userRepo, InvestmentRepo investmentRepo) {
        this.investmentInquiryRepo = investmentInquiryRepo;
        this.userRepo = userRepo;
        this.investmentRepo = investmentRepo;
    }

    /**
     * Creates a new investment inquiry.
     * Ensures that both the associated user and investment exist before saving.
     *
     * @param investmentInquiry The investment inquiry object to be saved.
     * @return The saved InvestmentInquiry entity.
     * @throws InvestmentInquiryException If user or investment details are missing.
     */
    @Override
    public InvestmentInquiry createInquiry(InvestmentInquiry investmentInquiry) {
        User user = userRepo.findById(investmentInquiry.getUser().getUserId()).orElse(null);
        Investment investment = investmentRepo.findById(investmentInquiry.getInvestment().getInvestmentId()).orElse(null);

        if (user == null || investment == null) {
            throw new InvestmentInquiryException("Investment Inquiry details not found!");
        }

        investmentInquiry.setInquiryDate(LocalDate.now());
        investmentInquiry.setStatus("Pending");
        investmentInquiry.setUser(user);
        investmentInquiry.setInvestment(investment);
        return investmentInquiryRepo.save(investmentInquiry);
    }

    /**
     * Retrieves all investment inquiries.
     *
     * @return A list of all investment inquiries.
     * @throws InvestmentInquiryException If no inquiries exist.
     */
    @Override
    public List<InvestmentInquiry> getAllInquiries() {
        List<InvestmentInquiry> list = investmentInquiryRepo.findAll();
        if (list.isEmpty()) {
            throw new InvestmentInquiryException("No investment inquiries found!");
        }
        return list;
    }

    /**
     * Retrieves an investment inquiry by its unique ID.
     *
     * @param inquiryId The unique ID of the investment inquiry.
     * @return The InvestmentInquiry entity if found.
     * @throws InvestmentInquiryException If the inquiry is not found.
     */
    @Override
    public InvestmentInquiry getInquiryById(long inquiryId) {
        InvestmentInquiry investmentInquiry = investmentInquiryRepo.findById(inquiryId).orElse(null);
        if (investmentInquiry == null) {
            throw new InvestmentInquiryException("Investment Inquiry not found!");
        }
        return investmentInquiry;
    }

    /**
     * Updates an existing investment inquiry by its ID.
     *
     * @param inquiryId The unique ID of the investment inquiry to update.
     * @param investmentInquiry The updated investment inquiry data.
     * @return The updated InvestmentInquiry entity.
     * @throws InvestmentInquiryException If the inquiry does not exist.
     */
    @Override
    public InvestmentInquiry updateInquiry(long inquiryId, InvestmentInquiry investmentInquiry) {
        InvestmentInquiry existingInquiry = investmentInquiryRepo.findById(inquiryId).orElse(null);
        if (existingInquiry == null) {
            throw new InvestmentInquiryException("Investment Inquiry not found to update!");
        }

        existingInquiry.setMessage(investmentInquiry.getMessage());
        existingInquiry.setStatus(investmentInquiry.getStatus());
        existingInquiry.setPriority(investmentInquiry.getPriority());
        existingInquiry.setAdminResponse(investmentInquiry.getAdminResponse());
        existingInquiry.setContactDetails(investmentInquiry.getContactDetails());

        return investmentInquiryRepo.save(existingInquiry);
    }

    /**
     * Deletes an investment inquiry by its unique ID.
     *
     * @param inquiryId The ID of the investment inquiry to delete.
     * @return true if deletion was successful, false otherwise.
     */
    @Override
    public boolean deleteInquiry(long inquiryId) {
        if (investmentInquiryRepo.existsById(inquiryId)) {
            investmentInquiryRepo.deleteById(inquiryId);
            return true;
        }
        return false;
    }

    /**
     * Retrieves all investment inquiries submitted by a specific user.
     *
     * @param userId The ID of the user whose inquiries are being fetched.
     * @return A list of inquiries associated with the given user.
     * @throws InvestmentInquiryException If the user does not exist or has no inquiries.
     */
    @Override
    public List<InvestmentInquiry> getInquiriesByUserId(long userId) {
        User user = userRepo.findById(userId).orElse(null);
        if (user == null) {
            throw new InvestmentInquiryException("User not found with ID: " + userId);
        }

        List<InvestmentInquiry> inquiries = investmentInquiryRepo.findByUser_UserId(userId);
        if (inquiries.isEmpty()) {
            throw new InvestmentInquiryException("No investment inquiries found for user ID: " + userId);
        }
        return inquiries;
    }
}