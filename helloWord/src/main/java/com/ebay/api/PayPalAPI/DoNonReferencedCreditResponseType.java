package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.DoNonReferencedCreditResponseDetailsType;
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
public class DoNonReferencedCreditResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private DoNonReferencedCreditResponseDetailsType doNonReferencedCreditResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public DoNonReferencedCreditResponseType (){
	}	

	/**
	 * Getter for doNonReferencedCreditResponseDetails
	 */
	 public DoNonReferencedCreditResponseDetailsType getDoNonReferencedCreditResponseDetails() {
	 	return doNonReferencedCreditResponseDetails;
	 }
	 
	/**
	 * Setter for doNonReferencedCreditResponseDetails
	 */
	 public void setDoNonReferencedCreditResponseDetails(DoNonReferencedCreditResponseDetailsType doNonReferencedCreditResponseDetails) {
	 	this.doNonReferencedCreditResponseDetails = doNonReferencedCreditResponseDetails;
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
	
	public DoNonReferencedCreditResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("DoNonReferencedCreditResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.doNonReferencedCreditResponseDetails =  new DoNonReferencedCreditResponseDetailsType(childNode);
		}
	}
 
}