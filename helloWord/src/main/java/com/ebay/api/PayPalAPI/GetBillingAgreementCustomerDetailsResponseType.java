package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.GetBillingAgreementCustomerDetailsResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetBillingAgreementCustomerDetailsResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private GetBillingAgreementCustomerDetailsResponseDetailsType getBillingAgreementCustomerDetailsResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public GetBillingAgreementCustomerDetailsResponseType (){
	}	

	/**
	 * Getter for getBillingAgreementCustomerDetailsResponseDetails
	 */
	 public GetBillingAgreementCustomerDetailsResponseDetailsType getGetBillingAgreementCustomerDetailsResponseDetails() {
	 	return getBillingAgreementCustomerDetailsResponseDetails;
	 }
	 
	/**
	 * Setter for getBillingAgreementCustomerDetailsResponseDetails
	 */
	 public void setGetBillingAgreementCustomerDetailsResponseDetails(GetBillingAgreementCustomerDetailsResponseDetailsType getBillingAgreementCustomerDetailsResponseDetails) {
	 	this.getBillingAgreementCustomerDetailsResponseDetails = getBillingAgreementCustomerDetailsResponseDetails;
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
	
	public GetBillingAgreementCustomerDetailsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("GetBillingAgreementCustomerDetailsResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.getBillingAgreementCustomerDetailsResponseDetails =  new GetBillingAgreementCustomerDetailsResponseDetailsType(childNode);
		}
	}
 
}