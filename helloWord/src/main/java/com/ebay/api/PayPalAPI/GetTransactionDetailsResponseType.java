package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.PaymentTransactionType;
import com.ebay.apis.eBLBaseComponents.ThreeDSecureInfoType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetTransactionDetailsResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private PaymentTransactionType paymentTransactionDetails;

	/**
	 * 	 
	 */ 
	private ThreeDSecureInfoType threeDSecureDetails;

	

	/**
	 * Default Constructor
	 */
	public GetTransactionDetailsResponseType (){
	}	

	/**
	 * Getter for paymentTransactionDetails
	 */
	 public PaymentTransactionType getPaymentTransactionDetails() {
	 	return paymentTransactionDetails;
	 }
	 
	/**
	 * Setter for paymentTransactionDetails
	 */
	 public void setPaymentTransactionDetails(PaymentTransactionType paymentTransactionDetails) {
	 	this.paymentTransactionDetails = paymentTransactionDetails;
	 }
	 
	/**
	 * Getter for threeDSecureDetails
	 */
	 public ThreeDSecureInfoType getThreeDSecureDetails() {
	 	return threeDSecureDetails;
	 }
	 
	/**
	 * Setter for threeDSecureDetails
	 */
	 public void setThreeDSecureDetails(ThreeDSecureInfoType threeDSecureDetails) {
	 	this.threeDSecureDetails = threeDSecureDetails;
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
	
	public GetTransactionDetailsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("PaymentTransactionDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.paymentTransactionDetails =  new PaymentTransactionType(childNode);
		}
		childNode = (Node) xpath.evaluate("ThreeDSecureDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.threeDSecureDetails =  new ThreeDSecureInfoType(childNode);
		}
	}
 
}