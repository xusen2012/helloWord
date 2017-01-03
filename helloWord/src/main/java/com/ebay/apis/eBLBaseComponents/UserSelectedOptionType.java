package com.ebay.apis.eBLBaseComponents;
import com.paypal.core.SDKUtil;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import com.ebay.apis.CoreComponentTypes.BasicAmountType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

/**
 * Information on user selected options 
 */
public class UserSelectedOptionType{

	private static final String nameSpace="urn:ebay:apis:eBLBaseComponents";
	private static final String preferredPrefix="ebl";

	/**
	 * 	 
	 */ 
	private String shippingCalculationMode;

	/**
	 * 	 
	 */ 
	private String insuranceOptionSelected;

	/**
	 * 	 
	 */ 
	private String shippingOptionIsDefault;

	/**
	 * 	 
	 */ 
	private BasicAmountType shippingOptionAmount;

	/**
	 * 	 
	 */ 
	private String shippingOptionName;

	/**
	 * 	 
	 */ 
	private String scheduledShippingDate;

	/**
	 * 	 
	 */ 
	private String scheduledShippingPeriod;

	

	/**
	 * Default Constructor
	 */
	public UserSelectedOptionType (){
	}	

	/**
	 * Getter for shippingCalculationMode
	 */
	 public String getShippingCalculationMode() {
	 	return shippingCalculationMode;
	 }
	 
	/**
	 * Setter for shippingCalculationMode
	 */
	 public void setShippingCalculationMode(String shippingCalculationMode) {
	 	this.shippingCalculationMode = shippingCalculationMode;
	 }
	 
	/**
	 * Getter for insuranceOptionSelected
	 */
	 public String getInsuranceOptionSelected() {
	 	return insuranceOptionSelected;
	 }
	 
	/**
	 * Setter for insuranceOptionSelected
	 */
	 public void setInsuranceOptionSelected(String insuranceOptionSelected) {
	 	this.insuranceOptionSelected = insuranceOptionSelected;
	 }
	 
	/**
	 * Getter for shippingOptionIsDefault
	 */
	 public String getShippingOptionIsDefault() {
	 	return shippingOptionIsDefault;
	 }
	 
	/**
	 * Setter for shippingOptionIsDefault
	 */
	 public void setShippingOptionIsDefault(String shippingOptionIsDefault) {
	 	this.shippingOptionIsDefault = shippingOptionIsDefault;
	 }
	 
	/**
	 * Getter for shippingOptionAmount
	 */
	 public BasicAmountType getShippingOptionAmount() {
	 	return shippingOptionAmount;
	 }
	 
	/**
	 * Setter for shippingOptionAmount
	 */
	 public void setShippingOptionAmount(BasicAmountType shippingOptionAmount) {
	 	this.shippingOptionAmount = shippingOptionAmount;
	 }
	 
	/**
	 * Getter for shippingOptionName
	 */
	 public String getShippingOptionName() {
	 	return shippingOptionName;
	 }
	 
	/**
	 * Setter for shippingOptionName
	 */
	 public void setShippingOptionName(String shippingOptionName) {
	 	this.shippingOptionName = shippingOptionName;
	 }
	 
	/**
	 * Getter for scheduledShippingDate
	 */
	 public String getScheduledShippingDate() {
	 	return scheduledShippingDate;
	 }
	 
	/**
	 * Setter for scheduledShippingDate
	 */
	 public void setScheduledShippingDate(String scheduledShippingDate) {
	 	this.scheduledShippingDate = scheduledShippingDate;
	 }
	 
	/**
	 * Getter for scheduledShippingPeriod
	 */
	 public String getScheduledShippingPeriod() {
	 	return scheduledShippingPeriod;
	 }
	 
	/**
	 * Setter for scheduledShippingPeriod
	 */
	 public void setScheduledShippingPeriod(String scheduledShippingPeriod) {
	 	this.scheduledShippingPeriod = scheduledShippingPeriod;
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
		if(shippingCalculationMode != null) {
			sb.append("<").append(preferredPrefix).append(":ShippingCalculationMode>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.shippingCalculationMode));
			sb.append("</").append(preferredPrefix).append(":ShippingCalculationMode>");
		}
		if(insuranceOptionSelected != null) {
			sb.append("<").append(preferredPrefix).append(":InsuranceOptionSelected>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.insuranceOptionSelected));
			sb.append("</").append(preferredPrefix).append(":InsuranceOptionSelected>");
		}
		if(shippingOptionIsDefault != null) {
			sb.append("<").append(preferredPrefix).append(":ShippingOptionIsDefault>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.shippingOptionIsDefault));
			sb.append("</").append(preferredPrefix).append(":ShippingOptionIsDefault>");
		}
		if(shippingOptionAmount != null) {
			sb.append(shippingOptionAmount.toXMLString(preferredPrefix,"ShippingOptionAmount"));
		}
		if(shippingOptionName != null) {
			sb.append("<").append(preferredPrefix).append(":ShippingOptionName>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.shippingOptionName));
			sb.append("</").append(preferredPrefix).append(":ShippingOptionName>");
		}
		if(scheduledShippingDate != null) {
			sb.append("<").append(preferredPrefix).append(":ScheduledShippingDate>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.scheduledShippingDate));
			sb.append("</").append(preferredPrefix).append(":ScheduledShippingDate>");
		}
		if(scheduledShippingPeriod != null) {
			sb.append("<").append(preferredPrefix).append(":ScheduledShippingPeriod>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.scheduledShippingPeriod));
			sb.append("</").append(preferredPrefix).append(":ScheduledShippingPeriod>");
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
	
	public UserSelectedOptionType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("ShippingCalculationMode", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.shippingCalculationMode = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("InsuranceOptionSelected", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.insuranceOptionSelected = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("ShippingOptionIsDefault", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.shippingOptionIsDefault = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("ShippingOptionAmount", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.shippingOptionAmount =  new BasicAmountType(childNode);
		}
		childNode = (Node) xpath.evaluate("ShippingOptionName", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.shippingOptionName = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("ScheduledShippingDate", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.scheduledShippingDate = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("ScheduledShippingPeriod", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.scheduledShippingPeriod = childNode.getTextContent();
		}
	
	}
 
}
