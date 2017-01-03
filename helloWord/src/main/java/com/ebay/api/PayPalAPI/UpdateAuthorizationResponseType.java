package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.AuthorizationInfoType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * An authorization identification number. Character length and
 * limits: 19 single-byte characters 
 */
public class UpdateAuthorizationResponseType extends AbstractResponseType {


	/**
	 * An authorization identification number. Character length and
	 * limits: 19 single-byte characters 	 
	 */ 
	private String transactionID;

	/**
	 * 	 
	 */ 
	private AuthorizationInfoType authorizationInfo;

	

	/**
	 * Default Constructor
	 */
	public UpdateAuthorizationResponseType (){
	}	

	/**
	 * Getter for transactionID
	 */
	 public String getTransactionID() {
	 	return transactionID;
	 }
	 
	/**
	 * Setter for transactionID
	 */
	 public void setTransactionID(String transactionID) {
	 	this.transactionID = transactionID;
	 }
	 
	/**
	 * Getter for authorizationInfo
	 */
	 public AuthorizationInfoType getAuthorizationInfo() {
	 	return authorizationInfo;
	 }
	 
	/**
	 * Setter for authorizationInfo
	 */
	 public void setAuthorizationInfo(AuthorizationInfoType authorizationInfo) {
	 	this.authorizationInfo = authorizationInfo;
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
	
	public UpdateAuthorizationResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("TransactionID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.transactionID = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("AuthorizationInfo", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.authorizationInfo =  new AuthorizationInfoType(childNode);
		}
	}
 
}
