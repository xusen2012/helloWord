package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.ManageRecurringPaymentsProfileStatusResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class ManageRecurringPaymentsProfileStatusResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private ManageRecurringPaymentsProfileStatusResponseDetailsType manageRecurringPaymentsProfileStatusResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public ManageRecurringPaymentsProfileStatusResponseType (){
	}	

	/**
	 * Getter for manageRecurringPaymentsProfileStatusResponseDetails
	 */
	 public ManageRecurringPaymentsProfileStatusResponseDetailsType getManageRecurringPaymentsProfileStatusResponseDetails() {
	 	return manageRecurringPaymentsProfileStatusResponseDetails;
	 }
	 
	/**
	 * Setter for manageRecurringPaymentsProfileStatusResponseDetails
	 */
	 public void setManageRecurringPaymentsProfileStatusResponseDetails(ManageRecurringPaymentsProfileStatusResponseDetailsType manageRecurringPaymentsProfileStatusResponseDetails) {
	 	this.manageRecurringPaymentsProfileStatusResponseDetails = manageRecurringPaymentsProfileStatusResponseDetails;
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
	
	public ManageRecurringPaymentsProfileStatusResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("ManageRecurringPaymentsProfileStatusResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.manageRecurringPaymentsProfileStatusResponseDetails =  new ManageRecurringPaymentsProfileStatusResponseDetailsType(childNode);
		}
	}
 
}