package com.examly.springapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Investment;
import com.examly.springapp.service.InvestmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/investments")
@Tag(name = "Investment Management", description = "APIs for managing investment records")
public class InvestmentController {

    @Autowired
    private InvestmentService investmentService;

    /**
     * Creates a new investment entry.
     *
     * @param investment The investment object received in the request body.
     * @return ResponseEntity containing the created investment with HTTP status 201
     *         (Created).
     */
    @Operation(summary = "Create Investment", description = "Creates a new investment entry with the provided details.")
    @PostMapping
    public ResponseEntity<?> addInvestment(@Valid @RequestBody Investment investment) {
        investment = investmentService.addInvestment(investment);
        return investment != null ? ResponseEntity.status(201).body(investment)
                : ResponseEntity.status(403).body("Investment creation failed");
    }

    /**
     * Retrieves an investment by its unique ID.
     *
     * @param investmentId The ID of the investment to retrieve.
     * @return ResponseEntity containing the investment object with HTTP status 200
     *         (OK) if found, otherwise 404 (Not Found).
     */
    @Operation(summary = "Get Investment by ID", description = "Retrieves investment details by its unique ID.")
    @GetMapping("/{investmentId}")
    public ResponseEntity<?> getInvestmentById(@PathVariable long investmentId) {
        Investment investment = investmentService.getInvestmentById(investmentId);
        return investment != null ? ResponseEntity.ok(investment)
                : ResponseEntity.status(404).body("Investment not found");
    }

    /**
     * Retrieves all investments with pagination support.
     *
     * @param page The page number (default: 0).
     * @param size The number of records per page (default: 10).
     * @return ResponseEntity containing the paginated list of investments.
     */
    @Operation(summary = "Get All Investments with Pagination", description = "Retrieves all investment entries with pagination.")
    @GetMapping
    public ResponseEntity<?> getAllInvestments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        List<Investment> investments = investmentService.getAllInvestments();
        return ResponseEntity.ok(investments);
    }

    /**
     * Retrieves investments by type with pagination.
     *
     * @param type The type of investment.
     * @param page The page number (default: 0).
     * @param size The number of records per page (default: 10).
     * @return ResponseEntity containing the paginated list of investments.
     */
    @Operation(summary = "Get Investments by Type with Pagination", description = "Retrieves investments filtered by type with pagination.")
    @GetMapping("/type/{type}")
    public ResponseEntity<Page<Investment>> getInvestmentsByType(
            @PathVariable String type,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Investment> investments = investmentService.getInvestmentsByType(type, pageable);
        return ResponseEntity.ok(investments);
    }

    /**
     * Retrieves investments by status with pagination.
     *
     * @param status The status of the investment.
     * @param page   The page number (default: 0).
     * @param size   The number of records per page (default: 10).
     * @return ResponseEntity containing the paginated list of investments.
     */
    @Operation(summary = "Get Investments by Status with Pagination", description = "Retrieves investments filtered by status with pagination.")
    @GetMapping("/status/{status}")
    public ResponseEntity<Page<Investment>> getInvestmentsByStatus(
            @PathVariable String status,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Investment> investments = investmentService.getInvestmentsByStatus(status, pageable);
        return ResponseEntity.ok(investments);
    }

    /**
     * Updates an existing investment record.
     *
     * @param investmentId The ID of the investment to update.
     * @param investment   The updated investment details received in the request
     *                     body.
     * @return ResponseEntity containing the updated investment object with HTTP
     *         status 200 (OK), or 404 if the update fails.
     */
    @Operation(summary = "Update Investment", description = "Updates an existing investment record with the provided details.")
    @PutMapping("/{investmentId}")
    public ResponseEntity<?> updateInvestment(@PathVariable long investmentId,
            @Valid @RequestBody Investment investment) {
        investment = investmentService.updateInvestment(investmentId, investment);
        return investment != null ? ResponseEntity.ok(investment)
                : ResponseEntity.status(404).body("Investment not found");
    }

    /**
     * Deletes an investment record by its ID.
     *
     * @param investmentId The ID of the investment to delete.
     * @return ResponseEntity indicating whether the deletion was successful or
     *         failed.
     */
    @Operation(summary = "Delete Investment", description = "Deletes an investment record by its unique ID.")
    @DeleteMapping("/{investmentId}")
    public ResponseEntity<?> deleteInvestment(@PathVariable long investmentId) {
        boolean result = investmentService.deleteInvestment(investmentId);
        return result ? ResponseEntity.ok("Investment deleted successfully")
                : ResponseEntity.status(404).body("Investment not found");
    }
}