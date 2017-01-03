package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.ReverseTransactionResponseDetailsType;
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
public class ReverseTransactionResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private ReverseTransactionResponseDetailsType reverseTransactionResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public ReverseTransactionResponseType (){
	}	

	/**
	 * Getter for reverseTransactionResponseDetails
	 */
	 public ReverseTransactionResponseDetailsType getReverseTransactionResponseDetails() {
	 	return reverseTransactionResponseDetails;
	 }
	 
	/**
	 * Setter for reverseTransactionResponseDetails
	 */
	 public void setReverseTransactionResponseDetails(ReverseTransactionResponseDetailsType reverseTransactionResponseDetails) {
	 	this.reverseTransactionResponseDetails = reverseTransactionResponseDetails;
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
	
	public ReverseTransactionResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("ReverseTransactionResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.reverseTransactionResponseDetails =  new ReverseTransactionResponseDetailsType(childNode);
		}
	}
 
}