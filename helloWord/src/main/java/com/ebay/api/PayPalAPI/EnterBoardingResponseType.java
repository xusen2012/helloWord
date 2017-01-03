package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A unique token that identifies this boarding session. Use
 * this token with the GetBoarding Details API call.Character
 * length and limitations: 64 alphanumeric characters. The
 * token has the following format:OB-61characterstring
 */
public class EnterBoardingResponseType extends AbstractResponseType {


	/**
	 * A unique token that identifies this boarding session. Use
	 * this token with the GetBoarding Details API call.Character
	 * length and limitations: 64 alphanumeric characters. The
	 * token has the following format:OB-61characterstring	  
	 *@Required	 
	 */ 
	private String token;

	

	/**
	 * Default Constructor
	 */
	public EnterBoardingResponseType (){
	}	

	/**
	 * Getter for token
	 */
	 public String getToken() {
	 	return token;
	 }
	 
	/**
	 * Setter for token
	 */
	 public void setToken(String token) {
	 	this.token = token;
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
	
	public EnterBoardingResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("Token", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.token = childNode.getTextContent();
		}
	
	}
 
}