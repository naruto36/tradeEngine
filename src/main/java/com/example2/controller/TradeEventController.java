package com.example2.controller;

import com.example2.entity.TradeEvent;
import com.example2.service.TradeEventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/trade-events")
public class TradeEventController {

    @Autowired
    private TradeEventService service;

    @GetMapping("/report")
    public List<TradeEvent> getTradeEventReport() {
        return service.getFilteredEvents();
    }
}
