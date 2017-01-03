package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * The status of the update call, Success/Failure. Character
 * length and limitations: 20 single-byte characters 
 */
public class UpdateAccessPermissionsResponseType extends AbstractResponseType {


	/**
	 * The status of the update call, Success/Failure. Character
	 * length and limitations: 20 single-byte characters 	  
	 *@Required	 
	 */ 
	private String status;

	

	/**
	 * Default Constructor
	 */
	public UpdateAccessPermissionsResponseType (){
	}	

	/**
	 * Getter for status
	 */
	 public String getStatus() {
	 	return status;
	 }
	 
	/**
	 * Setter for status
	 */
	 public void setStatus(String status) {
	 	this.status = status;
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
	
	public UpdateAccessPermissionsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("Status", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			String value = childNode.getTextContent();
			this.status = value;
		}
	}
 
}