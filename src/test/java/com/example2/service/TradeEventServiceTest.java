package com.example2.service;

import com.example2.entity.TradeEvent;
import com.example2.repository.TradeEventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class TradeEventServiceTest {

    @Mock
    private TradeEventRepository repository;

    @InjectMocks
    private TradeEventService service;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetFilteredEvents() {
        TradeEvent event1 = new TradeEvent(null, "LEFT_BANK", "EMU_BANK", 100.0, "AUD");
        TradeEvent event2 = new TradeEvent(null, "LEFT_BANK", "BISON_BANK", 150.0, "USD");
        TradeEvent event3 = new TradeEvent(null, "LEFT_BANK", "EMU_BANK", 200.0, "USD"); // Doesn't match criteria

        when(repository.findAll()).thenReturn(Arrays.asList(event1, event2, event3));

        List<TradeEvent> result = service.getFilteredEvents();

        assertEquals(2, result.size());
        assertEquals("EMU_BANK", result.get(0).getSellerParty());
        assertEquals("BISON_BANK", result.get(1).getSellerParty());
    }

    @Test
    public void testIsAnagram() {
        // Testing private method via public functionality
        TradeEvent event1 = new TradeEvent(null, "LEFT_BANK", "EMU_BANK", 100.0, "AUD");
        TradeEvent event2 = new TradeEvent(null, "EMU_BANK", "EMU_BANK", 150.0, "USD");

        when(repository.findAll()).thenReturn(Arrays.asList(event1, event2));

        List<TradeEvent> result = service.getFilteredEvents();

        assertEquals(1, result.size());
        assertEquals("EMU_BANK", result.get(0).getSellerParty());
    }
}
