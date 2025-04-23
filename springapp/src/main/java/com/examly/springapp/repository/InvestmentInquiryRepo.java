package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.examly.springapp.model.InvestmentInquiry;

public interface InvestmentInquiryRepo extends JpaRepository<Long, InvestmentInquiry>{

    

}
