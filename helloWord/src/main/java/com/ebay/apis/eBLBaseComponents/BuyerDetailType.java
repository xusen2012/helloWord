package com.ebay.apis.eBLBaseComponents;
import com.paypal.core.SDKUtil;

/**
 * Information that is used to indentify the Buyer. This is
 * used for auto authorization. Mandatory if Authorization is
 * requested. 
 */
public class BuyerDetailType{

	private static final String nameSpace="urn:ebay:apis:eBLBaseComponents";
	private static final String preferredPrefix="ebl";

	/**
	 * Information that is used to indentify the Buyer. This is
	 * used for auto authorization. Mandatory if Authorization is
	 * requested.	 
	 */ 
	private IdentificationInfoType identificationInfo;

	/**
	 * Correlation id related to risk process done for the device.
	 * Max length is 36 Chars.	 
	 */ 
	private String riskSessionCorrelationID;

	/**
	 * Buyer's IP Address	 
	 */ 
	private String buyerIPAddress;

	

	/**
	 * Default Constructor
	 */
	public BuyerDetailType (){
	}	

	/**
	 * Getter for identificationInfo
	 */
	 public IdentificationInfoType getIdentificationInfo() {
	 	return identificationInfo;
	 }
	 
	/**
	 * Setter for identificationInfo
	 */
	 public void setIdentificationInfo(IdentificationInfoType identificationInfo) {
	 	this.identificationInfo = identificationInfo;
	 }
	 
	/**
	 * Getter for riskSessionCorrelationID
	 */
	 public String getRiskSessionCorrelationID() {
	 	return riskSessionCorrelationID;
	 }
	 
	/**
	 * Setter for riskSessionCorrelationID
	 */
	 public void setRiskSessionCorrelationID(String riskSessionCorrelationID) {
	 	this.riskSessionCorrelationID = riskSessionCorrelationID;
	 }
	 
	/**
	 * Getter for buyerIPAddress
	 */
	 public String getBuyerIPAddress() {
	 	return buyerIPAddress;
	 }
	 
	/**
	 * Setter for buyerIPAddress
	 */
	 public void setBuyerIPAddress(String buyerIPAddress) {
	 	this.buyerIPAddress = buyerIPAddress;
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
		if(identificationInfo != null) {
			sb.append(identificationInfo.toXMLString(preferredPrefix,"IdentificationInfo"));
		}
		if(riskSessionCorrelationID != null) {
			sb.append("<").append(preferredPrefix).append(":RiskSessionCorrelationID>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.riskSessionCorrelationID));
			sb.append("</").append(preferredPrefix).append(":RiskSessionCorrelationID>");
		}
		if(buyerIPAddress != null) {
			sb.append("<").append(preferredPrefix).append(":BuyerIPAddress>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.buyerIPAddress));
			sb.append("</").append(preferredPrefix).append(":BuyerIPAddress>");
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


}
