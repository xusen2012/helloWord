package com.ebay.apis.eBLBaseComponents;
import com.ebay.apis.CoreComponentTypes.BasicAmountType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * SubscriptionTermsType Terms of a PayPal subscription. 
 */
public class SubscriptionTermsType{


	/**
	 * 	 
	 */ 
	private BasicAmountType amount;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private String period;

	

	/**
	 * Default Constructor
	 */
	public SubscriptionTermsType (){
	}	

	/**
	 * Getter for amount
	 */
	 public BasicAmountType getAmount() {
	 	return amount;
	 }
	 
	/**
	 * Setter for amount
	 */
	 public void setAmount(BasicAmountType amount) {
	 	this.amount = amount;
	 }
	 
	/**
	 * Getter for period
	 */
	 public String getPeriod() {
	 	return period;
	 }
	 
	/**
	 * Setter for period
	 */
	 public void setPeriod(String period) {
	 	this.period = period;
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
	
	public SubscriptionTermsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("Amount", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.amount =  new BasicAmountType(childNode);
		}
		childNode = (Node) xpath.evaluate("period", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.period = childNode.getTextContent();
		}
	
	}
 
}