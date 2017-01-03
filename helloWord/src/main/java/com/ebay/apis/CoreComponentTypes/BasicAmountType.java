package com.ebay.apis.CoreComponentTypes;
import com.ebay.apis.eBLBaseComponents.CurrencyCodeType;
import com.paypal.core.SDKUtil;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * On requests, you must set the currencyID attribute to one of
 * the three-character currency codes for any of the supported
 * PayPal currencies. Limitations: Must not exceed $10,000 USD
 * in any currency. No currency symbol. Decimal separator must
 * be a period (.), and the thousands separator must be a comma
 * (,).
 */
public class BasicAmountType{

	private static final String nameSpace="urn:ebay:apis:CoreComponentTypes";
	private static final String preferredPrefix="cc";

	/**
	 * 	  
	 *@Required	 
	 */ 
	private CurrencyCodeType currencyID;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private String value;

	

	/**
	 * Constructor with arguments
	 */
	public BasicAmountType (CurrencyCodeType currencyID, String value){
		this.currencyID = currencyID;
		this.value = value;
	}	

	/**
	 * Default Constructor
	 */
	public BasicAmountType (){
	}	

	/**
	 * Getter for currencyID
	 */
	 public CurrencyCodeType getCurrencyID() {
	 	return currencyID;
	 }
	 
	/**
	 * Setter for currencyID
	 */
	 public void setCurrencyID(CurrencyCodeType currencyID) {
	 	this.currencyID = currencyID;
	 }
	 
	/**
	 * Getter for value
	 */
	 public String getValue() {
	 	return value;
	 }
	 
	/**
	 * Setter for value
	 */
	 public void setValue(String value) {
	 	this.value = value;
	 }
	 


	public String toXMLString(String prefix, String name) {
		StringBuilder sb = new StringBuilder();
		if(name!=null){
			if(prefix!=null){
				sb.append("<").append(prefix).append(":").append(name);
			}
			else{
				sb.append("<").append(preferredPrefix).append(":").append(name);
			}
		sb.append(getAttributeAsXml());
		sb.append(">");
		}
		if(value != null) {
			sb.append(SDKUtil.escapeInvalidXmlCharsRegex(this.value));
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

	
	private String getAttributeAsXml() {
		StringBuilder sb = new StringBuilder();
		if(currencyID != null) {
			sb.append(" currencyID=\"").append(SDKUtil.escapeInvalidXmlCharsRegex(this.currencyID.getValue())).append("\"");	
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
	
	public BasicAmountType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("@currencyID", node, XPathConstants.NODE);
		if (childNode != null) {
		    this.currencyID = CurrencyCodeType.fromValue(childNode.getNodeValue());
		}
		this.value = node.getTextContent();
	
	
	}
 
}