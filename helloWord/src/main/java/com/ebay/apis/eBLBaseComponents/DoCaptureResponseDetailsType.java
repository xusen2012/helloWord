package com.ebay.apis.eBLBaseComponents;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The authorization identification number you specified in the
 * request. Character length and limits: 19 single-byte
 * characters maximum 
 */
public class DoCaptureResponseDetailsType{


	/**
	 * The authorization identification number you specified in the
	 * request. Character length and limits: 19 single-byte
	 * characters maximum	 
	 */ 
	private String authorizationID;

	/**
	 * Information about the transaction 	 
	 */ 
	private PaymentInfoType paymentInfo;

	/**
	 * Return msgsubid back to merchant 	 
	 */ 
	private String msgSubID;

	/**
	 * Partner funding source id corresponding to the FS used in
	 * authorization. 	 
	 */ 
	private String partnerFundingSourceID;

	

	/**
	 * Default Constructor
	 */
	public DoCaptureResponseDetailsType (){
	}	

	/**
	 * Getter for authorizationID
	 */
	 public String getAuthorizationID() {
	 	return authorizationID;
	 }
	 
	/**
	 * Setter for authorizationID
	 */
	 public void setAuthorizationID(String authorizationID) {
	 	this.authorizationID = authorizationID;
	 }
	 
	/**
	 * Getter for paymentInfo
	 */
	 public PaymentInfoType getPaymentInfo() {
	 	return paymentInfo;
	 }
	 
	/**
	 * Setter for paymentInfo
	 */
	 public void setPaymentInfo(PaymentInfoType paymentInfo) {
	 	this.paymentInfo = paymentInfo;
	 }
	 
	/**
	 * Getter for msgSubID
	 */
	 public String getMsgSubID() {
	 	return msgSubID;
	 }
	 
	/**
	 * Setter for msgSubID
	 */
	 public void setMsgSubID(String msgSubID) {
	 	this.msgSubID = msgSubID;
	 }
	 
	/**
	 * Getter for partnerFundingSourceID
	 */
	 public String getPartnerFundingSourceID() {
	 	return partnerFundingSourceID;
	 }
	 
	/**
	 * Setter for partnerFundingSourceID
	 */
	 public void setPartnerFundingSourceID(String partnerFundingSourceID) {
	 	this.partnerFundingSourceID = partnerFundingSourceID;
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
	
	public DoCaptureResponseDetailsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("AuthorizationID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.authorizationID = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("PaymentInfo", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.paymentInfo =  new PaymentInfoType(childNode);
		}
		childNode = (Node) xpath.evaluate("MsgSubID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.msgSubID = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("PartnerFundingSourceID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.partnerFundingSourceID = childNode.getTextContent();
		}
	
	}
 
}