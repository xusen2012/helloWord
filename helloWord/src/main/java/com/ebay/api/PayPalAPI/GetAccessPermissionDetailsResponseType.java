package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.GetAccessPermissionDetailsResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetAccessPermissionDetailsResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private GetAccessPermissionDetailsResponseDetailsType getAccessPermissionDetailsResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public GetAccessPermissionDetailsResponseType (){
	}	

	/**
	 * Getter for getAccessPermissionDetailsResponseDetails
	 */
	 public GetAccessPermissionDetailsResponseDetailsType getGetAccessPermissionDetailsResponseDetails() {
	 	return getAccessPermissionDetailsResponseDetails;
	 }
	 
	/**
	 * Setter for getAccessPermissionDetailsResponseDetails
	 */
	 public void setGetAccessPermissionDetailsResponseDetails(GetAccessPermissionDetailsResponseDetailsType getAccessPermissionDetailsResponseDetails) {
	 	this.getAccessPermissionDetailsResponseDetails = getAccessPermissionDetailsResponseDetails;
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
	
	public GetAccessPermissionDetailsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("GetAccessPermissionDetailsResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.getAccessPermissionDetailsResponseDetails =  new GetAccessPermissionDetailsResponseDetailsType(childNode);
		}
	}
 
}