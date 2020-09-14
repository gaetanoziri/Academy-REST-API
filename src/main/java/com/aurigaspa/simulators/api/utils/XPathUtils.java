package com.aurigaspa.simulators.api.utils;

import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

public class XPathUtils {
	
	public static String findString(String xmlString, String xPath) throws ParserConfigurationException, SAXException, IOException, XPathExpressionException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder;
        Document doc = null;
        String output  = null;
        
        builder = factory.newDocumentBuilder();
        doc = builder.parse(new InputSource(new StringReader(xmlString)));

        // Create XPathFactory object
        XPathFactory xpathFactory = XPathFactory.newInstance();

        // Create XPath object
        XPath xpath = xpathFactory.newXPath();

        XPathExpression expr = xpath.compile(xPath);
        output = (String) expr.evaluate(doc, XPathConstants.STRING);
        
        return output;
		
	}

}
