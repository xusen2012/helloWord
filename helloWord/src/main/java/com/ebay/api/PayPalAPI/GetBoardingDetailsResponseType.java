package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;
import com.ebay.apis.eBLBaseComponents.GetBoardingDetailsResponseDetailsType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class GetBoardingDetailsResponseType extends AbstractResponseType {


	/**
	 * 	  
	 *@Required	 
	 */ 
	private GetBoardingDetailsResponseDetailsType getBoardingDetailsResponseDetails;

	

	/**
	 * Default Constructor
	 */
	public GetBoardingDetailsResponseType (){
	}	

	/**
	 * Getter for getBoardingDetailsResponseDetails
	 */
	 public GetBoardingDetailsResponseDetailsType getGetBoardingDetailsResponseDetails() {
	 	return getBoardingDetailsResponseDetails;
	 }
	 
	/**
	 * Setter for getBoardingDetailsResponseDetails
	 */
	 public void setGetBoardingDetailsResponseDetails(GetBoardingDetailsResponseDetailsType getBoardingDetailsResponseDetails) {
	 	this.getBoardingDetailsResponseDetails = getBoardingDetailsResponseDetails;
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
	
	public GetBoardingDetailsResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("GetBoardingDetailsResponseDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.getBoardingDetailsResponseDetails =  new GetBoardingDetailsResponseDetailsType(childNode);
		}
	}
 
}