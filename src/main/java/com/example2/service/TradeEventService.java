package com.example2.service;

import com.example.entity.TradeEvent;
import com.example.repository.TradeEventRepository;
import com.example.util.XMLParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TradeEventService {

    @Autowired
    private TradeEventRepository repository;

    @PostConstruct
    public void processXMLFiles() throws Exception {
        File xmlDir = new File("src/main/resources/xml");
        for (File xmlFile : xmlDir.listFiles()) {
            TradeEvent event = XMLParserUtil.parseXML(xmlFile);
            repository.save(event);
        }
    }

    public List<TradeEvent> getFilteredEvents() {
        return repository.findAll().stream()
                .filter(event -> (event.getSellerParty().equals("EMU_BANK") && event.getPremiumCurrency().equals("AUD")) ||
                        (event.getSellerParty().equals("BISON_BANK") && event.getPremiumCurrency().equals("USD")))
                .filter(event -> !isAnagram(event.getBuyerParty(), event.getSellerParty()))
                .collect(Collectors.toList());
    }

    private boolean isAnagram(String str1, String str2) {
        if (str1.length() != str2.length()) {
            return false;
        }
        char[] str1Array = str1.toCharArray();
        char[] str2Array = str2.toCharArray();
        java.util.Arrays.sort(str1Array);
        java.util.Arrays.sort(str2Array);
        return java.util.Arrays.equals(str1Array, str2Array);
    }
}
