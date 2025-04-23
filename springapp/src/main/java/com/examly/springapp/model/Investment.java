package com.examly.springapp.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Investment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long investmentId;
    String name;
    String description;
    String type;
    double purchasePrice;
    double currentPrice;
    int quantity;
    String purchaseDate;
    String status;
    public long getInvestmentId() {
        return investmentId;
    }
    public void setInvestmentId(long investmentId) {
        this.investmentId = investmentId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public double getPurchasePrice() {
        return purchasePrice;
    }
    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }
    public double getCurrentPrice() {
        return currentPrice;
    }
    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public String getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
}
