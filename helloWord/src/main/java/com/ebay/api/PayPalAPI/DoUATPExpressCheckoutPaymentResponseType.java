package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.UATPDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class DoUATPExpressCheckoutPaymentResponseType extends DoExpressCheckoutPaymentResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private UATPDetailsType uATPDetails;

	

	/**
	 * Default Constructor
	 */
	public DoUATPExpressCheckoutPaymentResponseType (){
	}	

	/**
	 * Getter for uATPDetails
	 */
	 public UATPDetailsType getUATPDetails() {
	 	return uATPDetails;
	 }
	 
	/**
	 * Setter for uATPDetails
	 */
	 public void setUATPDetails(UATPDetailsType uATPDetails) {
	 	this.uATPDetails = uATPDetails;
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
	
	public DoUATPExpressCheckoutPaymentResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("UATPDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.uATPDetails =  new UATPDetailsType(childNode);
		}
	}
 
}