package com.example2.repository;

import com.example2.entity.TradeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TradeEventRepository extends JpaRepository<TradeEvent, Long> {

    // Custom query method to find TradeEvents based on sellerParty and premiumCurrency
    List<TradeEvent> findBySellerPartyAndPremiumCurrency(String sellerParty, String premiumCurrency);
}
