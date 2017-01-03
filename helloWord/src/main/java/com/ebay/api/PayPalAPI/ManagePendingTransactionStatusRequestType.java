package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.AbstractRequestType;
import com.ebay.apis.eBLBaseComponents.FMFPendingTransactionActionType;
import com.paypal.core.SDKUtil;

/**
 * 
 */
public class ManagePendingTransactionStatusRequestType extends AbstractRequestType {

	private static final String nameSpace="urn:ebay:api:PayPalAPI";
	private static final String preferredPrefix="ns";

	/**
	 * 	  
	 *@Required	 
	 */ 
	private String transactionID;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private FMFPendingTransactionActionType action;

	

	/**
	 * Constructor with arguments
	 */
	public ManagePendingTransactionStatusRequestType (String transactionID, FMFPendingTransactionActionType action){
		this.transactionID = transactionID;
		this.action = action;
	}	

	/**
	 * Default Constructor
	 */
	public ManagePendingTransactionStatusRequestType (){
	}	

	/**
	 * Getter for transactionID
	 */
	 public String getTransactionID() {
	 	return transactionID;
	 }
	 
	/**
	 * Setter for transactionID
	 */
	 public void setTransactionID(String transactionID) {
	 	this.transactionID = transactionID;
	 }
	 
	/**
	 * Getter for action
	 */
	 public FMFPendingTransactionActionType getAction() {
	 	return action;
	 }
	 
	/**
	 * Setter for action
	 */
	 public void setAction(FMFPendingTransactionActionType action) {
	 	this.action = action;
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
		sb.append(super.toXMLString(prefix, null));
		if(transactionID != null) {
			sb.append("<").append(preferredPrefix).append(":TransactionID>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.transactionID));
			sb.append("</").append(preferredPrefix).append(":TransactionID>");
		}
		if(action != null) {
			sb.append("<").append(preferredPrefix).append(":Action>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.action.getValue()));
			sb.append("</").append(preferredPrefix).append(":Action>");
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