package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.InvestmentInquiry;
/*
 *    InvestmentInequiryRepo   used to handle  InvestmentInquiry entity
 */
@Repository
public interface InvestmentInquiryRepo extends JpaRepository<InvestmentInquiry, Long>{

    //To get a list of InvestmentInquires by UserId
    List<InvestmentInquiry> findByUser_UserId(long userId);
   
}
