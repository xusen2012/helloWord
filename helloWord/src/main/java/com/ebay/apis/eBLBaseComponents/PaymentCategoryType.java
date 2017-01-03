package com.ebay.apis.eBLBaseComponents;

/**
 * Category of payment like international shipping
 *  
 */
public enum  PaymentCategoryType {

	INTERNATIONALSHIPPING("InternationalShipping"),

	LOCALDELIVERY("LocalDelivery"),

	BOPIS("BOPIS"),

	PUDO("PUDO");

	private String value;

	private PaymentCategoryType (String value) {
		this.value = value;
	}

	public String getValue(){
		return value;
	}
	
	public static PaymentCategoryType fromValue(String v) {
		for (PaymentCategoryType c : values()) {
			if (c.value.equals(v)) {
				return c;
			}
		}
		throw new IllegalArgumentException(v);
	}

}