package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Investment;

@Repository
public interface InvestmentRepo extends JpaRepository<Investment, Long>{

    @Query("select investment from Investment investment where investment.name = ?1")
    Investment getInvestmentByName(String name);

    @Query("select investment from Investment investment where investment.type = ?1")
    List<Investment> getInvestmentsByType(String type);

    @Query("select investment from Investment investment where investment.status = ?1")
    List<Investment> getInvestmentsByStatus(String status);
}
