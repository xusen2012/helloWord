package com.ebay.apis.eBLBaseComponents;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * InstrumentDetailsType Promotional Instrument Information. 
 */
public class InstrumentDetailsType{


	/**
	 * This field holds the category of the instrument only when it
	 * is promotional. Return value 1 represents BML, 2 reprasents
	 * PLCC/Cobranded. 	 
	 */ 
	private String instrumentCategory;

	/**
	 * This field holds the InstrumentID issued by external party
	 * corresponding to the funding source used in payment. 	 
	 */ 
	private String instrumentID;

	

	/**
	 * Default Constructor
	 */
	public InstrumentDetailsType (){
	}	

	/**
	 * Getter for instrumentCategory
	 */
	 public String getInstrumentCategory() {
	 	return instrumentCategory;
	 }
	 
	/**
	 * Setter for instrumentCategory
	 */
	 public void setInstrumentCategory(String instrumentCategory) {
	 	this.instrumentCategory = instrumentCategory;
	 }
	 
	/**
	 * Getter for instrumentID
	 */
	 public String getInstrumentID() {
	 	return instrumentID;
	 }
	 
	/**
	 * Setter for instrumentID
	 */
	 public void setInstrumentID(String instrumentID) {
	 	this.instrumentID = instrumentID;
	 }
	 



	private  boolean isWhitespaceNode(Node n) {
		if (n.getNodeType() == Node.TEXT_NODE) {
			String val = n.getNodeValue();
			return val.trim().length() == 0;
		} else if (n.getNodeType() == Node.ELEMENT_NODE ) {
			return (n.getChildNodes().getLength() == 0);
		} else {
			return false;
		}
	}
	
	public InstrumentDetailsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("InstrumentCategory", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.instrumentCategory = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("InstrumentID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.instrumentID = childNode.getTextContent();
		}
	
	}
 
}