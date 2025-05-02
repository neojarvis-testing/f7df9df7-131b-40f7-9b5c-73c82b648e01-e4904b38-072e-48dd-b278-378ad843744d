package com.examly.springapp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Investment;

/**
 * Repository interface for managing Investment entity persistence.
 * Extends JpaRepository to provide CRUD operations for Investment with pagination support.
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
     * Retrieves a paginated list of investments filtered by type.
     * 
     * @param type The type of investment (e.g., Real Estate, Stock, Bond).
     * @param pageable Pageable object to manage pagination.
     * @return A paginated list of investments matching the specified type.
     */
    @Query("select investment from Investment investment where investment.type = ?1")
    Page<Investment> getInvestmentsByType(String type, Pageable pageable);

    /**
     * Retrieves a paginated list of investments filtered by status.
     * 
     * @param status The status of the investment (e.g., Active, Sold, Pending).
     * @param pageable Pageable object to manage pagination.
     * @return A paginated list of investments matching the specified status.
     */
    @Query("select investment from Investment investment where investment.status = ?1")
    Page<Investment> getInvestmentsByStatus(String status, Pageable pageable);
}