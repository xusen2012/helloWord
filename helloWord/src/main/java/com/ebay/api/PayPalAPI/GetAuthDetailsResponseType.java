package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.GetAuthDetailsResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetAuthDetailsResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private GetAuthDetailsResponseDetailsType getAuthDetailsResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public GetAuthDetailsResponseType (){
	}	

	/**
	 * Getter for getAuthDetailsResponseDetails
	 */
	 public GetAuthDetailsResponseDetailsType getGetAuthDetailsResponseDetails() {
	 	return getAuthDetailsResponseDetails;
	 }
	 
	/**
	 * Setter for getAuthDetailsResponseDetails
	 */
	 public void setGetAuthDetailsResponseDetails(GetAuthDetailsResponseDetailsType getAuthDetailsResponseDetails) {
	 	this.getAuthDetailsResponseDetails = getAuthDetailsResponseDetails;
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
	
	public GetAuthDetailsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("GetAuthDetailsResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.getAuthDetailsResponseDetails =  new GetAuthDetailsResponseDetailsType(childNode);
		}
	}
 
}