package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * A timestamped token by which you identify to PayPal that you
 * are processing this payment with Mobile Checkout. The token
 * expires after three hours. Character length and limitations:
 * 20 single-byte characters
 */
public class SetMobileCheckoutResponseType extends AbstractResponseType {


	/**
	 * A timestamped token by which you identify to PayPal that you
	 * are processing this payment with Mobile Checkout. The token
	 * expires after three hours. Character length and limitations:
	 * 20 single-byte characters	  
	 *@Required	 
	 */ 
	private String token;

	

	/**
	 * Default Constructor
	 */
	public SetMobileCheckoutResponseType (){
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
	
	public SetMobileCheckoutResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("Token", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			String value = childNode.getTextContent();
			this.token = value;
		}
	}
 
}