package com.example2.repository;

import com.example.entity.TradeEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TradeEventRepository extends JpaRepository<TradeEvent, Long> {
    // Custom query methods can be added here if needed
}
