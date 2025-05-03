package com.examly.springapp.controller;

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

    private final InvestmentService investmentService;

    public InvestmentController(InvestmentService investmentService) {
        this.investmentService = investmentService;
    }

    @Operation(summary = "Create Investment", description = "Creates a new investment entry with the provided details.")
    @PostMapping
    public ResponseEntity<?> addInvestment(@Valid @RequestBody Investment investment) {
        investment = investmentService.addInvestment(investment);
        return investment != null ? ResponseEntity.status(201).body(investment)
                : ResponseEntity.status(403).body("Investment creation failed");
    }

    @Operation(summary = "Get Investment by ID", description = "Retrieves investment details by its unique ID.")
    @GetMapping("/{investmentId}")
    public ResponseEntity<?> getInvestmentById(@PathVariable long investmentId) {
        Investment investment = investmentService.getInvestmentById(investmentId);
        return investment != null ? ResponseEntity.ok(investment)
                : ResponseEntity.status(404).body("Investmefrvdnt not found");
    }

    @Operation(summary = "Get All Investments with Pagination", description = "Retrieves all investment entries with pagination.")
    @GetMapping
    public ResponseEntity<Page<Investment>> getAllInvestments(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Investment> investments = investmentService.getAllInvestments(pageable);
        return ResponseEntity.ok(investments);
    }

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

    @Operation(summary = "Update Investment", description = "Updates an existing investment record with the provided details.")
    @PutMapping("/{investmentId}")
    public ResponseEntity<?> updateInvestment(@PathVariable long investmentId,
            @Valid @RequestBody Investment investment) {
        investment = investmentService.updateInvestment(investmentId, investment);
        return investment != null ? ResponseEntity.ok(investment)
                : ResponseEntity.status(404).body("Investmrsdfgent not found");
    }

    @Operation(summary = "Delete Investment", description = "Deletes an investment record by its unique ID.")
    @DeleteMapping("/{investmentId}")
    public ResponseEntity<?> deleteInvestment(@PathVariable long investmentId) {
        boolean result = investmentService.deleteInvestment(investmentId);
        return result ? ResponseEntity.ok("Investment deleted successfully")
                : ResponseEntity.status(404).body("Investmersfnt not found");
    }
}