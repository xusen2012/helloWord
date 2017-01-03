package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.BAUpdateResponseDetailsType;
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
public class BAUpdateResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private BAUpdateResponseDetailsType bAUpdateResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public BAUpdateResponseType (){
	}	

	/**
	 * Getter for bAUpdateResponseDetails
	 */
	 public BAUpdateResponseDetailsType getBAUpdateResponseDetails() {
	 	return bAUpdateResponseDetails;
	 }
	 
	/**
	 * Setter for bAUpdateResponseDetails
	 */
	 public void setBAUpdateResponseDetails(BAUpdateResponseDetailsType bAUpdateResponseDetails) {
	 	this.bAUpdateResponseDetails = bAUpdateResponseDetails;
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
	
	public BAUpdateResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("BAUpdateResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.bAUpdateResponseDetails =  new BAUpdateResponseDetailsType(childNode);
		}
	}
 
}