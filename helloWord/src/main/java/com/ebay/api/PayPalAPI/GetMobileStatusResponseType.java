package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Indicates whether the phone is activated for mobile payments
 * 
 */
public class GetMobileStatusResponseType extends AbstractResponseType {


	/**
	 * Indicates whether the phone is activated for mobile payments
	 * 	  
	 *@Required	 
	 */ 
	private Integer isActivated;

	/**
	 * Indicates whether the password is enabled for particular
	 * account 	  
	 *@Required	 
	 */ 
	private Integer isPasswordSet;

	/**
	 * Indicates whether there is a payment pending from the phone 	  
	 *@Required	 
	 */ 
	private Integer paymentPending;

	

	/**
	 * Default Constructor
	 */
	public GetMobileStatusResponseType (){
	}	

	/**
	 * Getter for isActivated
	 */
	 public Integer getIsActivated() {
	 	return isActivated;
	 }
	 
	/**
	 * Setter for isActivated
	 */
	 public void setIsActivated(Integer isActivated) {
	 	this.isActivated = isActivated;
	 }
	 
	/**
	 * Getter for isPasswordSet
	 */
	 public Integer getIsPasswordSet() {
	 	return isPasswordSet;
	 }
	 
	/**
	 * Setter for isPasswordSet
	 */
	 public void setIsPasswordSet(Integer isPasswordSet) {
	 	this.isPasswordSet = isPasswordSet;
	 }
	 
	/**
	 * Getter for paymentPending
	 */
	 public Integer getPaymentPending() {
	 	return paymentPending;
	 }
	 
	/**
	 * Setter for paymentPending
	 */
	 public void setPaymentPending(Integer paymentPending) {
	 	this.paymentPending = paymentPending;
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
	
	public GetMobileStatusResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("IsActivated", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			this.isActivated = Integer.valueOf(childNode.getTextContent());
		}
	
		childNode = (Node) xpath.evaluate("IsPasswordSet", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			this.isPasswordSet = Integer.valueOf(childNode.getTextContent());
		}
	
		childNode = (Node) xpath.evaluate("PaymentPending", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			this.paymentPending = Integer.valueOf(childNode.getTextContent());
		}
	
	}
 
}