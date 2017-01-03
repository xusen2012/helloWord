package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.MerchantPullPaymentResponseType;
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
public class BillUserResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private MerchantPullPaymentResponseType billUserResponseDetails;

	/**
	 * 	 
	 */ 
	private FMFDetailsType fMFDetails;

	

	/**
	 * Default Constructor
	 */
	public BillUserResponseType (){
	}	

	/**
	 * Getter for billUserResponseDetails
	 */
	 public MerchantPullPaymentResponseType getBillUserResponseDetails() {
	 	return billUserResponseDetails;
	 }
	 
	/**
	 * Setter for billUserResponseDetails
	 */
	 public void setBillUserResponseDetails(MerchantPullPaymentResponseType billUserResponseDetails) {
	 	this.billUserResponseDetails = billUserResponseDetails;
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
	
	public BillUserResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("BillUserResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.billUserResponseDetails =  new MerchantPullPaymentResponseType(childNode);
		}
		childNode = (Node) xpath.evaluate("FMFDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.fMFDetails =  new FMFDetailsType(childNode);
		}
	}
 
}