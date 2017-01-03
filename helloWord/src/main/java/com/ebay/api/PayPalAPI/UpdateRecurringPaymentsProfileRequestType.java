package com.ebay.api.PayPalAPI;
import com.ebay.apis.eBLBaseComponents.UpdateRecurringPaymentsProfileRequestDetailsType;
import com.ebay.apis.eBLBaseComponents.AbstractRequestType;

/**
 * 
 */
public class UpdateRecurringPaymentsProfileRequestType extends AbstractRequestType {

	private static final String nameSpace="urn:ebay:api:PayPalAPI";
	private static final String preferredPrefix="ns";

	/**
	 * 	 
	 */ 
	private UpdateRecurringPaymentsProfileRequestDetailsType updateRecurringPaymentsProfileRequestDetails;

	

	/**
	 * Default Constructor
	 */
	public UpdateRecurringPaymentsProfileRequestType (){
	}	

	/**
	 * Getter for updateRecurringPaymentsProfileRequestDetails
	 */
	 public UpdateRecurringPaymentsProfileRequestDetailsType getUpdateRecurringPaymentsProfileRequestDetails() {
	 	return updateRecurringPaymentsProfileRequestDetails;
	 }
	 
	/**
	 * Setter for updateRecurringPaymentsProfileRequestDetails
	 */
	 public void setUpdateRecurringPaymentsProfileRequestDetails(UpdateRecurringPaymentsProfileRequestDetailsType updateRecurringPaymentsProfileRequestDetails) {
	 	this.updateRecurringPaymentsProfileRequestDetails = updateRecurringPaymentsProfileRequestDetails;
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
		if(updateRecurringPaymentsProfileRequestDetails != null) {
			sb.append(updateRecurringPaymentsProfileRequestDetails.toXMLString(null,"UpdateRecurringPaymentsProfileRequestDetails"));
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