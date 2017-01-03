package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.UpdateRecurringPaymentsProfileResponseDetailsType;
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
public class UpdateRecurringPaymentsProfileResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private UpdateRecurringPaymentsProfileResponseDetailsType updateRecurringPaymentsProfileResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public UpdateRecurringPaymentsProfileResponseType (){
	}	

	/**
	 * Getter for updateRecurringPaymentsProfileResponseDetails
	 */
	 public UpdateRecurringPaymentsProfileResponseDetailsType getUpdateRecurringPaymentsProfileResponseDetails() {
	 	return updateRecurringPaymentsProfileResponseDetails;
	 }
	 
	/**
	 * Setter for updateRecurringPaymentsProfileResponseDetails
	 */
	 public void setUpdateRecurringPaymentsProfileResponseDetails(UpdateRecurringPaymentsProfileResponseDetailsType updateRecurringPaymentsProfileResponseDetails) {
	 	this.updateRecurringPaymentsProfileResponseDetails = updateRecurringPaymentsProfileResponseDetails;
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
	
	public UpdateRecurringPaymentsProfileResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("UpdateRecurringPaymentsProfileResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.updateRecurringPaymentsProfileResponseDetails =  new UpdateRecurringPaymentsProfileResponseDetailsType(childNode);
		}
	}
 
}