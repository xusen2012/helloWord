package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.GetIncentiveEvaluationResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetIncentiveEvaluationResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private GetIncentiveEvaluationResponseDetailsType getIncentiveEvaluationResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public GetIncentiveEvaluationResponseType (){
	}	

	/**
	 * Getter for getIncentiveEvaluationResponseDetails
	 */
	 public GetIncentiveEvaluationResponseDetailsType getGetIncentiveEvaluationResponseDetails() {
	 	return getIncentiveEvaluationResponseDetails;
	 }
	 
	/**
	 * Setter for getIncentiveEvaluationResponseDetails
	 */
	 public void setGetIncentiveEvaluationResponseDetails(GetIncentiveEvaluationResponseDetailsType getIncentiveEvaluationResponseDetails) {
	 	this.getIncentiveEvaluationResponseDetails = getIncentiveEvaluationResponseDetails;
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
	
	public GetIncentiveEvaluationResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("GetIncentiveEvaluationResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.getIncentiveEvaluationResponseDetails =  new GetIncentiveEvaluationResponseDetailsType(childNode);
		}
	}
 
}