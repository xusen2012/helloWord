package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetPalDetailsResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private String pal;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private String locale;

	

	/**
	 * Default Constructor
	 */
	public GetPalDetailsResponseType (){
	}	

	/**
	 * Getter for pal
	 */
	 public String getPal() {
	 	return pal;
	 }
	 
	/**
	 * Setter for pal
	 */
	 public void setPal(String pal) {
	 	this.pal = pal;
	 }
	 
	/**
	 * Getter for locale
	 */
	 public String getLocale() {
	 	return locale;
	 }
	 
	/**
	 * Setter for locale
	 */
	 public void setLocale(String locale) {
	 	this.locale = locale;
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
	
	public GetPalDetailsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("Pal", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.pal = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("Locale", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.locale = childNode.getTextContent();
		}
	
	}
 
}