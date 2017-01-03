package com.ebay.apis.eBLBaseComponents;
import java.util.List;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Status will denote whether Auto authorization was successful
 * or not. 
 */
public class AuthorizationResponseType{


	/**
	 * Status will denote whether Auto authorization was successful
	 * or not. 	  
	 *@Required	 
	 */ 
	private AckCodeType status;

	/**
	 * 	 
	 */ 
	private List<ErrorType> authorizationError = new ArrayList<ErrorType>();

	

	/**
	 * Default Constructor
	 */
	public AuthorizationResponseType (){
	}	

	/**
	 * Getter for status
	 */
	 public AckCodeType getStatus() {
	 	return status;
	 }
	 
	/**
	 * Setter for status
	 */
	 public void setStatus(AckCodeType status) {
	 	this.status = status;
	 }
	 
	/**
	 * Getter for authorizationError
	 */
	 public List<ErrorType> getAuthorizationError() {
	 	return authorizationError;
	 }
	 
	/**
	 * Setter for authorizationError
	 */
	 public void setAuthorizationError(List<ErrorType> authorizationError) {
	 	this.authorizationError = authorizationError;
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
	
	public AuthorizationResponseType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("Status", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.status = AckCodeType.fromValue(childNode.getTextContent());
		}
        nodeList = (NodeList) xpath.evaluate("AuthorizationError", node, XPathConstants.NODESET);
		if (nodeList != null && nodeList.getLength() > 0) {
			for(int i=0; i < nodeList.getLength(); i++) {
				Node subNode = nodeList.item(i);
				this.authorizationError.add(new ErrorType(subNode));
			}
		}
	}
 
}