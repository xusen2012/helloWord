package com.ebay.apis.eBLBaseComponents;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetBillingAgreementCustomerDetailsResponseDetailsType{


	/**
	 * 	  
	 *@Required	 
	 */ 
	private PayerInfoType payerInfo;

	/**
	 * Customer's billing address. Optional If you have a credit
	 * card mapped in your PayPal account, PayPal returns the
	 * billing address of the credit billing address otherwise your
	 * primary address as billing address in
	 * GetBillingAgreementCustomerDetails.	 
	 */ 
	private AddressType billingAddress;

	

	/**
	 * Default Constructor
	 */
	public GetBillingAgreementCustomerDetailsResponseDetailsType (){
	}	

	/**
	 * Getter for payerInfo
	 */
	 public PayerInfoType getPayerInfo() {
	 	return payerInfo;
	 }
	 
	/**
	 * Setter for payerInfo
	 */
	 public void setPayerInfo(PayerInfoType payerInfo) {
	 	this.payerInfo = payerInfo;
	 }
	 
	/**
	 * Getter for billingAddress
	 */
	 public AddressType getBillingAddress() {
	 	return billingAddress;
	 }
	 
	/**
	 * Setter for billingAddress
	 */
	 public void setBillingAddress(AddressType billingAddress) {
	 	this.billingAddress = billingAddress;
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
	
	public GetBillingAgreementCustomerDetailsResponseDetailsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("PayerInfo", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.payerInfo =  new PayerInfoType(childNode);
		}
		childNode = (Node) xpath.evaluate("BillingAddress", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.billingAddress =  new AddressType(childNode);
		}
	}
 
}