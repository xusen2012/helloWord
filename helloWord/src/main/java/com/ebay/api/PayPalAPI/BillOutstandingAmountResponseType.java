package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.BillOutstandingAmountResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class BillOutstandingAmountResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private BillOutstandingAmountResponseDetailsType billOutstandingAmountResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public BillOutstandingAmountResponseType (){
	}	

	/**
	 * Getter for billOutstandingAmountResponseDetails
	 */
	 public BillOutstandingAmountResponseDetailsType getBillOutstandingAmountResponseDetails() {
	 	return billOutstandingAmountResponseDetails;
	 }
	 
	/**
	 * Setter for billOutstandingAmountResponseDetails
	 */
	 public void setBillOutstandingAmountResponseDetails(BillOutstandingAmountResponseDetailsType billOutstandingAmountResponseDetails) {
	 	this.billOutstandingAmountResponseDetails = billOutstandingAmountResponseDetails;
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
	
	public BillOutstandingAmountResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("BillOutstandingAmountResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.billOutstandingAmountResponseDetails =  new BillOutstandingAmountResponseDetailsType(childNode);
		}
	}
 
}