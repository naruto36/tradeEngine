package com.example2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TradeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String buyerParty;
    private String sellerParty;
    private double premiumAmount;
    private String premiumCurrency;

    // Constructors
    public TradeEvent() {
    }

    public TradeEvent(Long id, String buyerParty, String sellerParty, double premiumAmount, String premiumCurrency) {
        this.id = id;
        this.buyerParty = buyerParty;
        this.sellerParty = sellerParty;
        this.premiumAmount = premiumAmount;
        this.premiumCurrency = premiumCurrency;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuyerParty() {
        return buyerParty;
    }

    public void setBuyerParty(String buyerParty) {
        this.buyerParty = buyerParty;
    }

    public String getSellerParty() {
        return sellerParty;
    }

    public void setSellerParty(String sellerParty) {
        this.sellerParty = sellerParty;
    }

    public double getPremiumAmount() {
        return premiumAmount;
    }

    public void setPremiumAmount(double premiumAmount) {
        this.premiumAmount = premiumAmount;
    }

    public String getPremiumCurrency() {
        return premiumCurrency;
    }

    public void setPremiumCurrency(String premiumCurrency) {
        this.premiumCurrency = premiumCurrency;
    }
}