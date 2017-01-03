package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.GetRecurringPaymentsProfileDetailsResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetRecurringPaymentsProfileDetailsResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private GetRecurringPaymentsProfileDetailsResponseDetailsType getRecurringPaymentsProfileDetailsResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public GetRecurringPaymentsProfileDetailsResponseType (){
	}	

	/**
	 * Getter for getRecurringPaymentsProfileDetailsResponseDetails
	 */
	 public GetRecurringPaymentsProfileDetailsResponseDetailsType getGetRecurringPaymentsProfileDetailsResponseDetails() {
	 	return getRecurringPaymentsProfileDetailsResponseDetails;
	 }
	 
	/**
	 * Setter for getRecurringPaymentsProfileDetailsResponseDetails
	 */
	 public void setGetRecurringPaymentsProfileDetailsResponseDetails(GetRecurringPaymentsProfileDetailsResponseDetailsType getRecurringPaymentsProfileDetailsResponseDetails) {
	 	this.getRecurringPaymentsProfileDetailsResponseDetails = getRecurringPaymentsProfileDetailsResponseDetails;
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
	
	public GetRecurringPaymentsProfileDetailsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("GetRecurringPaymentsProfileDetailsResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.getRecurringPaymentsProfileDetailsResponseDetails =  new GetRecurringPaymentsProfileDetailsResponseDetailsType(childNode);
		}
	}
 
}