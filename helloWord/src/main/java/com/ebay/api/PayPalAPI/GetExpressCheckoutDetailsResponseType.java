package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.GetExpressCheckoutDetailsResponseDetailsType;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetExpressCheckoutDetailsResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private GetExpressCheckoutDetailsResponseDetailsType getExpressCheckoutDetailsResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public GetExpressCheckoutDetailsResponseType (){
	}	

	/**
	 * Getter for getExpressCheckoutDetailsResponseDetails
	 */
	 public GetExpressCheckoutDetailsResponseDetailsType getGetExpressCheckoutDetailsResponseDetails() {
	 	return getExpressCheckoutDetailsResponseDetails;
	 }
	 
	/**
	 * Setter for getExpressCheckoutDetailsResponseDetails
	 */
	 public void setGetExpressCheckoutDetailsResponseDetails(GetExpressCheckoutDetailsResponseDetailsType getExpressCheckoutDetailsResponseDetails) {
	 	this.getExpressCheckoutDetailsResponseDetails = getExpressCheckoutDetailsResponseDetails;
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
	
	public GetExpressCheckoutDetailsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("GetExpressCheckoutDetailsResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.getExpressCheckoutDetailsResponseDetails =  new GetExpressCheckoutDetailsResponseDetailsType(childNode);
		}
	}
 
}