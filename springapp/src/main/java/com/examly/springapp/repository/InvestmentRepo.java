package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Investment;

@Repository
public interface InvestmentRepo extends JpaRepository<Investment, Long>{

    @Query("select i from Investment i where i.name = ?1")
    Investment getInvestmentByName(String name);
}
