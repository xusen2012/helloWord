package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.CreateRecurringPaymentsProfileResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class CreateRecurringPaymentsProfileResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private CreateRecurringPaymentsProfileResponseDetailsType createRecurringPaymentsProfileResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public CreateRecurringPaymentsProfileResponseType (){
	}	

	/**
	 * Getter for createRecurringPaymentsProfileResponseDetails
	 */
	 public CreateRecurringPaymentsProfileResponseDetailsType getCreateRecurringPaymentsProfileResponseDetails() {
	 	return createRecurringPaymentsProfileResponseDetails;
	 }
	 
	/**
	 * Setter for createRecurringPaymentsProfileResponseDetails
	 */
	 public void setCreateRecurringPaymentsProfileResponseDetails(CreateRecurringPaymentsProfileResponseDetailsType createRecurringPaymentsProfileResponseDetails) {
	 	this.createRecurringPaymentsProfileResponseDetails = createRecurringPaymentsProfileResponseDetails;
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
	
	public CreateRecurringPaymentsProfileResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("CreateRecurringPaymentsProfileResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.createRecurringPaymentsProfileResponseDetails =  new CreateRecurringPaymentsProfileResponseDetailsType(childNode);
		}
	}
 
}