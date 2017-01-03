package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.DoExpressCheckoutPaymentResponseDetailsType;
import com.ebay.apis.eBLBaseComponents.FMFDetailsType;
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
public class DoExpressCheckoutPaymentResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private DoExpressCheckoutPaymentResponseDetailsType doExpressCheckoutPaymentResponseDetails;

	/**
	 * 	 
	 */ 
	private FMFDetailsType fMFDetails;

	

	/**
	 * Default Constructor
	 */
	public DoExpressCheckoutPaymentResponseType (){
	}	

	/**
	 * Getter for doExpressCheckoutPaymentResponseDetails
	 */
	 public DoExpressCheckoutPaymentResponseDetailsType getDoExpressCheckoutPaymentResponseDetails() {
	 	return doExpressCheckoutPaymentResponseDetails;
	 }
	 
	/**
	 * Setter for doExpressCheckoutPaymentResponseDetails
	 */
	 public void setDoExpressCheckoutPaymentResponseDetails(DoExpressCheckoutPaymentResponseDetailsType doExpressCheckoutPaymentResponseDetails) {
	 	this.doExpressCheckoutPaymentResponseDetails = doExpressCheckoutPaymentResponseDetails;
	 }
	 
	/**
	 * Getter for fMFDetails
	 */
	 public FMFDetailsType getFMFDetails() {
	 	return fMFDetails;
	 }
	 
	/**
	 * Setter for fMFDetails
	 */
	 public void setFMFDetails(FMFDetailsType fMFDetails) {
	 	this.fMFDetails = fMFDetails;
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
	
	public DoExpressCheckoutPaymentResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("DoExpressCheckoutPaymentResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.doExpressCheckoutPaymentResponseDetails =  new DoExpressCheckoutPaymentResponseDetailsType(childNode);
		}
		childNode = (Node) xpath.evaluate("FMFDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.fMFDetails =  new FMFDetailsType(childNode);
		}
	}
 
}