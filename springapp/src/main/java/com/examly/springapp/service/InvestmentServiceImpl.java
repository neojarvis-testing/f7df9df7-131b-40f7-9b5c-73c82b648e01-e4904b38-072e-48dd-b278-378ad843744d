package com.examly.springapp.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.exceptions.DuplicateInvestmentException;
import com.examly.springapp.model.Investment;
import com.examly.springapp.repository.InvestmentRepo;

@Service
public class InvestmentServiceImpl implements InvestmentService{
    @Autowired
    InvestmentRepo investmentRepo;

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
        if(existingInvestment == null){
            return null;
        }
        updateInvestment.setInvestmentId(investmentId);
        return investmentRepo.save(updateInvestment);
    }

    @Override
    public Investment getInvestmentById(long investmentId) {
        return investmentRepo.findById(investmentId).orElse(null);
    }

    @Override
    public List<Investment> getAllInvestments() {
        return investmentRepo.findAll();
    }

    @Override
    public List<Investment> getInvestmentsByType(String type) {
        return investmentRepo.getInvestmentsByType(type);
    }

    @Override
    public List<Investment> getInvestmentsByStatus(String status) {
        return investmentRepo.getInvestmentsByStatus(status);
    }

    @Override
    public List<Investment> searchInvestments(String keyword) {
        List<Investment> list = getAllInvestments();
        List<Investment> listWithKeyword = new ArrayList<>();
        if(keyword == null){
            return listWithKeyword;
        }
        for(int i=0;i<list.size();i++){
            String value = list.get(i).toString();
            if(value.toLowerCase().contains(keyword.toLowerCase())){
                listWithKeyword.add(list.get(i));
            }
        } 
        return listWithKeyword;
    }

    @Override
    public boolean deleteInvestment(long investmentId) {
        Investment investment = investmentRepo.findById(investmentId).orElse(null);
        if(investment == null){
            return false;
        }
        investmentRepo.delete(investment);
        return true;
    }
}