package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.BillingPeriodType;
import com.paypal.core.SDKUtil;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Installment Period. Optional 
 */
public class InstallmentDetailsType{

	private static final String nameSpace="urn:ebay:api:PayPalAPI";
	private static final String preferredPrefix="ns";

	/**
	 * Installment Period. Optional 	 
	 */ 
	private BillingPeriodType billingPeriod;

	/**
	 * Installment Frequency. Optional 	 
	 */ 
	private Integer billingFrequency;

	/**
	 * Installment Cycles. Optional 	 
	 */ 
	private Integer totalBillingCycles;

	/**
	 * Installment Amount. Optional 	 
	 */ 
	private String amount;

	/**
	 * Installment Amount. Optional 	 
	 */ 
	private String shippingAmount;

	/**
	 * Installment Amount. Optional 	 
	 */ 
	private String taxAmount;

	

	/**
	 * Default Constructor
	 */
	public InstallmentDetailsType (){
	}	

	/**
	 * Getter for billingPeriod
	 */
	 public BillingPeriodType getBillingPeriod() {
	 	return billingPeriod;
	 }
	 
	/**
	 * Setter for billingPeriod
	 */
	 public void setBillingPeriod(BillingPeriodType billingPeriod) {
	 	this.billingPeriod = billingPeriod;
	 }
	 
	/**
	 * Getter for billingFrequency
	 */
	 public Integer getBillingFrequency() {
	 	return billingFrequency;
	 }
	 
	/**
	 * Setter for billingFrequency
	 */
	 public void setBillingFrequency(Integer billingFrequency) {
	 	this.billingFrequency = billingFrequency;
	 }
	 
	/**
	 * Getter for totalBillingCycles
	 */
	 public Integer getTotalBillingCycles() {
	 	return totalBillingCycles;
	 }
	 
	/**
	 * Setter for totalBillingCycles
	 */
	 public void setTotalBillingCycles(Integer totalBillingCycles) {
	 	this.totalBillingCycles = totalBillingCycles;
	 }
	 
	/**
	 * Getter for amount
	 */
	 public String getAmount() {
	 	return amount;
	 }
	 
	/**
	 * Setter for amount
	 */
	 public void setAmount(String amount) {
	 	this.amount = amount;
	 }
	 
	/**
	 * Getter for shippingAmount
	 */
	 public String getShippingAmount() {
	 	return shippingAmount;
	 }
	 
	/**
	 * Setter for shippingAmount
	 */
	 public void setShippingAmount(String shippingAmount) {
	 	this.shippingAmount = shippingAmount;
	 }
	 
	/**
	 * Getter for taxAmount
	 */
	 public String getTaxAmount() {
	 	return taxAmount;
	 }
	 
	/**
	 * Setter for taxAmount
	 */
	 public void setTaxAmount(String taxAmount) {
	 	this.taxAmount = taxAmount;
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
		if(billingPeriod != null) {
			sb.append("<").append(preferredPrefix).append(":BillingPeriod>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.billingPeriod.getValue()));
			sb.append("</").append(preferredPrefix).append(":BillingPeriod>");
		}
		if(billingFrequency != null) {
			sb.append("<").append(preferredPrefix).append(":BillingFrequency>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.billingFrequency));
			sb.append("</").append(preferredPrefix).append(":BillingFrequency>");
		}
		if(totalBillingCycles != null) {
			sb.append("<").append(preferredPrefix).append(":TotalBillingCycles>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.totalBillingCycles));
			sb.append("</").append(preferredPrefix).append(":TotalBillingCycles>");
		}
		if(amount != null) {
			sb.append("<").append(preferredPrefix).append(":Amount>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.amount));
			sb.append("</").append(preferredPrefix).append(":Amount>");
		}
		if(shippingAmount != null) {
			sb.append("<").append(preferredPrefix).append(":ShippingAmount>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.shippingAmount));
			sb.append("</").append(preferredPrefix).append(":ShippingAmount>");
		}
		if(taxAmount != null) {
			sb.append("<").append(preferredPrefix).append(":TaxAmount>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.taxAmount));
			sb.append("</").append(preferredPrefix).append(":TaxAmount>");
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
	
	public InstallmentDetailsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("BillingPeriod", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.billingPeriod = BillingPeriodType.fromValue(childNode.getTextContent());
		}
		childNode = (Node) xpath.evaluate("BillingFrequency", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			this.billingFrequency = Integer.valueOf(childNode.getTextContent());
		}
	
		childNode = (Node) xpath.evaluate("TotalBillingCycles", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			this.totalBillingCycles = Integer.valueOf(childNode.getTextContent());
		}
	
		childNode = (Node) xpath.evaluate("Amount", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.amount = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("ShippingAmount", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.shippingAmount = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("TaxAmount", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.taxAmount = childNode.getTextContent();
		}
	
	}
 
}