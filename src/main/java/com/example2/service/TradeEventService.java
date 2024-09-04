package com.example2.service;

import com.example2.entity.TradeEvent;
import com.example2.repository.TradeEventRepository;
import com.example2.util.XMLParserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TradeEventService {

    @Autowired
    private TradeEventRepository repository;

    @PostConstruct
    public void processXMLFiles() throws Exception {
        File xmlDir = new File("src/main/resources/xml");
        if (!xmlDir.exists() || !xmlDir.isDirectory()) {
            throw new RuntimeException("Directory 'src/main/resources/xml' does not exist or is not a directory");
        }

        File[] xmlFiles = xmlDir.listFiles();
        if (xmlFiles == null || xmlFiles.length == 0) {
            throw new RuntimeException("No XML files found in 'src/main/resources/xml' directory");
        }

        for (File xmlFile : xmlFiles) {
            TradeEvent event = XMLParserUtil.parseXML(xmlFile);
            repository.save(event);
        }
    }

    public List<TradeEvent> getFilteredEvents() {
        return repository.findAll().stream()
                .filter(event -> (Objects.equals(event.getSellerParty(), "EMU_BANK") && Objects.equals(event.getPremiumCurrency(), "AUD")) ||
                        (Objects.equals(event.getSellerParty(), "BISON_BANK") && Objects.equals(event.getPremiumCurrency(), "USD")))
                .filter(event -> !isAnagram(event.getBuyerParty(), event.getSellerParty()))
                .collect(Collectors.toList());
    }

    private boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }
        char[] str1Array = str1.toCharArray();
        char[] str2Array = str2.toCharArray();
        java.util.Arrays.sort(str1Array);
        java.util.Arrays.sort(str2Array);
        return java.util.Arrays.equals(str1Array, str2Array);
    }
}
