package com.ebay.api.PayPalAPI;
import com.ebay.apis.EnhancedDataTypes.EnhancedCompleteRecoupResponseDetailsType;
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
public class CompleteRecoupResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private EnhancedCompleteRecoupResponseDetailsType enhancedCompleteRecoupResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public CompleteRecoupResponseType (){
	}	

	/**
	 * Getter for enhancedCompleteRecoupResponseDetails
	 */
	 public EnhancedCompleteRecoupResponseDetailsType getEnhancedCompleteRecoupResponseDetails() {
	 	return enhancedCompleteRecoupResponseDetails;
	 }
	 
	/**
	 * Setter for enhancedCompleteRecoupResponseDetails
	 */
	 public void setEnhancedCompleteRecoupResponseDetails(EnhancedCompleteRecoupResponseDetailsType enhancedCompleteRecoupResponseDetails) {
	 	this.enhancedCompleteRecoupResponseDetails = enhancedCompleteRecoupResponseDetails;
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
	
	public CompleteRecoupResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("EnhancedCompleteRecoupResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.enhancedCompleteRecoupResponseDetails =  new EnhancedCompleteRecoupResponseDetailsType(childNode);
		}
	}
 
}