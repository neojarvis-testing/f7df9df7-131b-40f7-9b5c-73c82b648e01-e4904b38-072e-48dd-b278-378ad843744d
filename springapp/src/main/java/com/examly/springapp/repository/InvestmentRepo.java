package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Investment;

/**
 * Repository interface for managing Investment entity persistence.
 * Extends JpaRepository to provide CRUD operations for Investment.
 */
@Repository
public interface InvestmentRepo extends JpaRepository<Investment, Long> {

    /**
     * Retrieves an investment by its name.
     * 
     * @param name The name of the investment.
     * @return The Investment entity matching the given name.
     */
    @Query("select investment from Investment investment where investment.name = ?1")
    Investment getInvestmentByName(String name);

    /**
     * Retrieves a list of investments filtered by type.
     * 
     * @param type The type of investment (e.g., Real Estate, Stock, Bond).
     * @return A list of investments matching the specified type.
     */
    @Query("select investment from Investment investment where investment.type = ?1")
    List<Investment> getInvestmentsByType(String type);

    /**
     * Retrieves a list of investments filtered by status.
     * 
     * @param status The status of the investment (e.g., Active, Sold, Pending).
     * @return A list of investments matching the specified status.
     */
    @Query("select investment from Investment investment where investment.status = ?1")
    List<Investment> getInvestmentsByStatus(String status);
}