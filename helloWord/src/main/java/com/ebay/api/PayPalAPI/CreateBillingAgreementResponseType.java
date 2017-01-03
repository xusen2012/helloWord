package com.ebay.api.PayPalAPI;
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
public class CreateBillingAgreementResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private String billingAgreementID;

	

	/**
	 * Default Constructor
	 */
	public CreateBillingAgreementResponseType (){
	}	

	/**
	 * Getter for billingAgreementID
	 */
	 public String getBillingAgreementID() {
	 	return billingAgreementID;
	 }
	 
	/**
	 * Setter for billingAgreementID
	 */
	 public void setBillingAgreementID(String billingAgreementID) {
	 	this.billingAgreementID = billingAgreementID;
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
	
	public CreateBillingAgreementResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("BillingAgreementID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			String value = childNode.getTextContent();
			this.billingAgreementID = value;
		}
	}
 
}