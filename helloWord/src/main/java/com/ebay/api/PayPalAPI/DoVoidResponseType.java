package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * The authorization identification number you specified in the
 * request. Character length and limits: 19 single-byte
 * characters 
 */
public class DoVoidResponseType extends AbstractResponseType {


	/**
	 * The authorization identification number you specified in the
	 * request. Character length and limits: 19 single-byte
	 * characters	 
	 */ 
	private String authorizationID;

	/**
	 * Return msgsubid back to merchant	 
	 */ 
	private String msgSubID;

	

	/**
	 * Default Constructor
	 */
	public DoVoidResponseType (){
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
	
	public DoVoidResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("AuthorizationID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.authorizationID = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("MsgSubID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.msgSubID = childNode.getTextContent();
		}
	
	}
 
}
