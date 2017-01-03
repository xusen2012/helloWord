package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.ExecuteCheckoutOperationsResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class ExecuteCheckoutOperationsResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private ExecuteCheckoutOperationsResponseDetailsType executeCheckoutOperationsResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public ExecuteCheckoutOperationsResponseType (){
	}	

	/**
	 * Getter for executeCheckoutOperationsResponseDetails
	 */
	 public ExecuteCheckoutOperationsResponseDetailsType getExecuteCheckoutOperationsResponseDetails() {
	 	return executeCheckoutOperationsResponseDetails;
	 }
	 
	/**
	 * Setter for executeCheckoutOperationsResponseDetails
	 */
	 public void setExecuteCheckoutOperationsResponseDetails(ExecuteCheckoutOperationsResponseDetailsType executeCheckoutOperationsResponseDetails) {
	 	this.executeCheckoutOperationsResponseDetails = executeCheckoutOperationsResponseDetails;
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
	
	public ExecuteCheckoutOperationsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("ExecuteCheckoutOperationsResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.executeCheckoutOperationsResponseDetails =  new ExecuteCheckoutOperationsResponseDetailsType(childNode);
		}
	}
 
}