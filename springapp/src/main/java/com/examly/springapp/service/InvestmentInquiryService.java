package com.examly.springapp.service;

import java.util.List;

import com.examly.springapp.model.InvestmentInquiry;

public interface InvestmentInquiryService {

    InvestmentInquiry createInquiry(InvestmentInquiry investmentinquiry);
    List<InvestmentInquiry> getAllInquries();
    
}
