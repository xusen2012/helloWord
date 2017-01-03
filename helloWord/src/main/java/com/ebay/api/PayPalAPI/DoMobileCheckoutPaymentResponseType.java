package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.DoMobileCheckoutPaymentResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class DoMobileCheckoutPaymentResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private DoMobileCheckoutPaymentResponseDetailsType doMobileCheckoutPaymentResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public DoMobileCheckoutPaymentResponseType (){
	}	

	/**
	 * Getter for doMobileCheckoutPaymentResponseDetails
	 */
	 public DoMobileCheckoutPaymentResponseDetailsType getDoMobileCheckoutPaymentResponseDetails() {
	 	return doMobileCheckoutPaymentResponseDetails;
	 }
	 
	/**
	 * Setter for doMobileCheckoutPaymentResponseDetails
	 */
	 public void setDoMobileCheckoutPaymentResponseDetails(DoMobileCheckoutPaymentResponseDetailsType doMobileCheckoutPaymentResponseDetails) {
	 	this.doMobileCheckoutPaymentResponseDetails = doMobileCheckoutPaymentResponseDetails;
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
	
	public DoMobileCheckoutPaymentResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("DoMobileCheckoutPaymentResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.doMobileCheckoutPaymentResponseDetails =  new DoMobileCheckoutPaymentResponseDetailsType(childNode);
		}
	}
 
}