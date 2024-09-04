package com.example2.util;

import com.example2.entity.TradeEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;

public class XMLParserUtil {

    public static TradeEvent parseXML(File xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        String buyerParty = getXPathValue(doc, xpath, "//buyerPartyReference/@href");
        String sellerParty = getXPathValue(doc, xpath, "//sellerPartyReference/@href");

        String amountString = getXPathValue(doc, xpath, "//paymentAmount/amount");
        double premiumAmount = parseDoubleSafe(amountString);

        String premiumCurrency = getXPathValue(doc, xpath, "//paymentAmount/currency");

        return new TradeEvent(null, buyerParty, sellerParty, premiumAmount, premiumCurrency);
    }

    private static String getXPathValue(Document doc, XPath xpath, String expression) throws XPathExpressionException {
        XPathExpression expr = xpath.compile(expression);
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node != null ? node.getNodeValue() : null;
    }

    private static double parseDoubleSafe(String value) {
        if (value == null || value.trim().isEmpty()) {
            return 0.0;
        }
        try {
            return Double.parseDouble(value.trim());
        } catch (NumberFormatException e) {
            throw new RuntimeException("Invalid number format: " + value, e);
        }
    }
}
