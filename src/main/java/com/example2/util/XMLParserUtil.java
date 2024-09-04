package com.example2.util;

import com.example.entity.TradeEvent;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import java.io.File;
import java.io.IOException;

public class XMLParserUtil {

    public static TradeEvent parseXML(File xmlFile) throws Exception {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        XPathFactory xPathFactory = XPathFactory.newInstance();
        XPath xpath = xPathFactory.newXPath();

        String buyerParty = getXPathValue(doc, xpath, "//buyerPartyReference/@href");
        String sellerParty = getXPathValue(doc, xpath, "//sellerPartyReference/@href");
        double premiumAmount = Double.parseDouble(getXPathValue(doc, xpath, "//paymentAmount/amount"));
        String premiumCurrency = getXPathValue(doc, xpath, "//paymentAmount/currency");

        return new TradeEvent(null, buyerParty, sellerParty, premiumAmount, premiumCurrency);
    }

    private static String getXPathValue(Document doc, XPath xpath, String expression) throws XPathExpressionException {
        XPathExpression expr = xpath.compile(expression);
        Node node = (Node) expr.evaluate(doc, XPathConstants.NODE);
        return node.getNodeValue();
    }
}
