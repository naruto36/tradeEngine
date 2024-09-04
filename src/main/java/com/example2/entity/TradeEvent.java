package com.example2.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TradeEvent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String buyerParty;
    private String sellerParty;
    private double premiumAmount;
    private String premiumCurrency;

    // Getters and setters
    // Constructors
}
