package com.ebay.apis.eBLBaseComponents;
import com.ebay.apis.CoreComponentTypes.BasicAmountType;
import com.paypal.core.SDKUtil;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Describes discount information 
 */
public class DiscountType{

	private static final String nameSpace="urn:ebay:apis:eBLBaseComponents";
	private static final String preferredPrefix="ebl";

	/**
	 * Item nameOptional Character length and limits: 127
	 * single-byte characters 	 
	 */ 
	private String name;

	/**
	 * description of the discountOptional Character length and
	 * limits: 127 single-byte characters 	 
	 */ 
	private String description;

	/**
	 * amount discountedOptional 	  
	 *@Required	 
	 */ 
	private BasicAmountType amount;

	/**
	 * offer typeOptional 	 
	 */ 
	private RedeemedOfferType redeemedOfferType;

	/**
	 * offer IDOptional Character length and limits: 64 single-byte
	 * characters 	 
	 */ 
	private String redeemedOfferID;

	

	/**
	 * Constructor with arguments
	 */
	public DiscountType (BasicAmountType amount){
		this.amount = amount;
	}	

	/**
	 * Default Constructor
	 */
	public DiscountType (){
	}	

	/**
	 * Getter for name
	 */
	 public String getName() {
	 	return name;
	 }
	 
	/**
	 * Setter for name
	 */
	 public void setName(String name) {
	 	this.name = name;
	 }
	 
	/**
	 * Getter for description
	 */
	 public String getDescription() {
	 	return description;
	 }
	 
	/**
	 * Setter for description
	 */
	 public void setDescription(String description) {
	 	this.description = description;
	 }
	 
	/**
	 * Getter for amount
	 */
	 public BasicAmountType getAmount() {
	 	return amount;
	 }
	 
	/**
	 * Setter for amount
	 */
	 public void setAmount(BasicAmountType amount) {
	 	this.amount = amount;
	 }
	 
	/**
	 * Getter for redeemedOfferType
	 */
	 public RedeemedOfferType getRedeemedOfferType() {
	 	return redeemedOfferType;
	 }
	 
	/**
	 * Setter for redeemedOfferType
	 */
	 public void setRedeemedOfferType(RedeemedOfferType redeemedOfferType) {
	 	this.redeemedOfferType = redeemedOfferType;
	 }
	 
	/**
	 * Getter for redeemedOfferID
	 */
	 public String getRedeemedOfferID() {
	 	return redeemedOfferID;
	 }
	 
	/**
	 * Setter for redeemedOfferID
	 */
	 public void setRedeemedOfferID(String redeemedOfferID) {
	 	this.redeemedOfferID = redeemedOfferID;
	 }
	 


	public String toXMLString(String prefix, String name) {
		StringBuilder sb = new StringBuilder();
		if(name!=null){
			if(prefix!=null){
				sb.append("<").append(prefix).append(":").append(name).append(">");
			}
			else{
				sb.append("<").append(preferredPrefix).append(":").append(name).append(">");
			}
		}
		if(name != null) {
			sb.append("<").append(preferredPrefix).append(":Name>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.name));
			sb.append("</").append(preferredPrefix).append(":Name>");
		}
		if(description != null) {
			sb.append("<").append(preferredPrefix).append(":Description>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.description));
			sb.append("</").append(preferredPrefix).append(":Description>");
		}
		if(amount != null) {
			sb.append(amount.toXMLString(preferredPrefix,"Amount"));
		}
		if(redeemedOfferType != null) {
			sb.append("<").append(preferredPrefix).append(":RedeemedOfferType>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.redeemedOfferType.getValue()));
			sb.append("</").append(preferredPrefix).append(":RedeemedOfferType>");
		}
		if(redeemedOfferID != null) {
			sb.append("<").append(preferredPrefix).append(":RedeemedOfferID>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.redeemedOfferID));
			sb.append("</").append(preferredPrefix).append(":RedeemedOfferID>");
		}
		if(name!=null){
			if(prefix!=null){
				sb.append("</").append(prefix).append(":").append(name).append(">");
			}
			else{
				sb.append("</").append(preferredPrefix).append(":").append(name).append(">");
			}
		}
		return sb.toString();
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
	
	public DiscountType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("Name", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.name = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("Description", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.description = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("Amount", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.amount =  new BasicAmountType(childNode);
		}
		childNode = (Node) xpath.evaluate("RedeemedOfferType", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.redeemedOfferType = RedeemedOfferType.fromValue(childNode.getTextContent());
		}
		childNode = (Node) xpath.evaluate("RedeemedOfferID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.redeemedOfferID = childNode.getTextContent();
		}
	
	}
 
}