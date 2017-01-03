package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.SetAccessPermissionsRequestDetailsType;
import com.ebay.apis.eBLBaseComponents.AbstractRequestType;

/**
 * 
 */
public class SetAccessPermissionsRequestType extends AbstractRequestType {

	private static final String nameSpace="urn:ebay:api:PayPalAPI";
	private static final String preferredPrefix="ns";

	/**
	 * 	  
	 *@Required	 
	 */ 
	private SetAccessPermissionsRequestDetailsType setAccessPermissionsRequestDetails;

	

	/**
	 * Constructor with arguments
	 */
	public SetAccessPermissionsRequestType (SetAccessPermissionsRequestDetailsType setAccessPermissionsRequestDetails){
		this.setAccessPermissionsRequestDetails = setAccessPermissionsRequestDetails;
	}	

	/**
	 * Default Constructor
	 */
	public SetAccessPermissionsRequestType (){
	}	

	/**
	 * Getter for setAccessPermissionsRequestDetails
	 */
	 public SetAccessPermissionsRequestDetailsType getSetAccessPermissionsRequestDetails() {
	 	return setAccessPermissionsRequestDetails;
	 }
	 
	/**
	 * Setter for setAccessPermissionsRequestDetails
	 */
	 public void setSetAccessPermissionsRequestDetails(SetAccessPermissionsRequestDetailsType setAccessPermissionsRequestDetails) {
	 	this.setAccessPermissionsRequestDetails = setAccessPermissionsRequestDetails;
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
		if(setAccessPermissionsRequestDetails != null) {
			sb.append(setAccessPermissionsRequestDetails.toXMLString(null,"SetAccessPermissionsRequestDetails"));
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