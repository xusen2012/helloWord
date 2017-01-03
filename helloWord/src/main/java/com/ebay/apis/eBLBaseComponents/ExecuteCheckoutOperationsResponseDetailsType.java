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
public class ExecuteCheckoutOperationsResponseDetailsType{


	/**
	 * 	  
	 *@Required	 
	 */ 
	private SetDataResponseType setDataResponse;

	/**
	 * 	 
	 */ 
	private AuthorizationResponseType authorizationResponse;

	

	/**
	 * Default Constructor
	 */
	public ExecuteCheckoutOperationsResponseDetailsType (){
	}	

	/**
	 * Getter for setDataResponse
	 */
	 public SetDataResponseType getSetDataResponse() {
	 	return setDataResponse;
	 }
	 
	/**
	 * Setter for setDataResponse
	 */
	 public void setSetDataResponse(SetDataResponseType setDataResponse) {
	 	this.setDataResponse = setDataResponse;
	 }
	 
	/**
	 * Getter for authorizationResponse
	 */
	 public AuthorizationResponseType getAuthorizationResponse() {
	 	return authorizationResponse;
	 }
	 
	/**
	 * Setter for authorizationResponse
	 */
	 public void setAuthorizationResponse(AuthorizationResponseType authorizationResponse) {
	 	this.authorizationResponse = authorizationResponse;
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
	
	public ExecuteCheckoutOperationsResponseDetailsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("SetDataResponse", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.setDataResponse =  new SetDataResponseType(childNode);
		}
		childNode = (Node) xpath.evaluate("AuthorizationResponse", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.authorizationResponse =  new AuthorizationResponseType(childNode);
		}
	}
 
}