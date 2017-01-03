package com.ebay.apis.eBLBaseComponents;
import com.ebay.apis.CoreComponentTypes.BasicAmountType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Recurring Billing Profile ID 
 */
public class GetRecurringPaymentsProfileDetailsResponseDetailsType{


	/**
	 * Recurring Billing Profile ID 	  
	 *@Required	 
	 */ 
	private String profileID;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private RecurringPaymentsProfileStatusType profileStatus;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private String description;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private AutoBillType autoBillOutstandingAmount;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private Integer maxFailedPayments;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private RecurringPaymentsProfileDetailsType recurringPaymentsProfileDetails;

	/**
	 * 	 
	 */ 
	private BillingPeriodDetailsType currentRecurringPaymentsPeriod;

	/**
	 * 	  
	 *@Required	 
	 */ 
	private RecurringPaymentsSummaryType recurringPaymentsSummary;

	/**
	 * 	 
	 */ 
	private CreditCardDetailsType creditCard;

	/**
	 * 	 
	 */ 
	private BillingPeriodDetailsType trialRecurringPaymentsPeriod;

	/**
	 * 	 
	 */ 
	private BillingPeriodDetailsType regularRecurringPaymentsPeriod;

	/**
	 * 	 
	 */ 
	private BasicAmountType trialAmountPaid;

	/**
	 * 	 
	 */ 
	private BasicAmountType regularAmountPaid;

	/**
	 * 	 
	 */ 
	private BasicAmountType aggregateAmount;

	/**
	 * 	 
	 */ 
	private BasicAmountType aggregateOptionalAmount;

	/**
	 * 	 
	 */ 
	private String finalPaymentDueDate;

	/**
	 * Use this optional parameter to pass in your business name
	 * and other data describing the transaction. Optional This
	 * information is usually displayed in the account holder's
	 * statement. Example: RedCross Haiti, RedCross Uganda,
	 * Realtor.com dues, Realtor.com list fee Length 25 characters.
	 * Alphanumeric characters and dash(-), dot(.), asterisk(*),
	 * space( ) On the customer's statement, an asterisk is used to
	 * separate the DBA name and product name. The asterisk
	 * delimiter can appear in position 4, 8, or 13. 	 
	 */ 
	private String softDescriptor;

	/**
	 * Use this optional parameter to pass information about how
	 * consumer should contact the merchant. Optional This
	 * information is usually displayed in the account holder's
	 * statement. For Ecom trx: phone, email or URL is allowed For
	 * Retail trx: only the actual city is allowed For details on
	 * allowed characters in Soft Descriptor City refer to the API
	 * documentation. 	 
	 */ 
	private String softDescriptorCity;

	

	/**
	 * Default Constructor
	 */
	public GetRecurringPaymentsProfileDetailsResponseDetailsType (){
	}	

	/**
	 * Getter for profileID
	 */
	 public String getProfileID() {
	 	return profileID;
	 }
	 
	/**
	 * Setter for profileID
	 */
	 public void setProfileID(String profileID) {
	 	this.profileID = profileID;
	 }
	 
	/**
	 * Getter for profileStatus
	 */
	 public RecurringPaymentsProfileStatusType getProfileStatus() {
	 	return profileStatus;
	 }
	 
	/**
	 * Setter for profileStatus
	 */
	 public void setProfileStatus(RecurringPaymentsProfileStatusType profileStatus) {
	 	this.profileStatus = profileStatus;
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
	 * Getter for autoBillOutstandingAmount
	 */
	 public AutoBillType getAutoBillOutstandingAmount() {
	 	return autoBillOutstandingAmount;
	 }
	 
	/**
	 * Setter for autoBillOutstandingAmount
	 */
	 public void setAutoBillOutstandingAmount(AutoBillType autoBillOutstandingAmount) {
	 	this.autoBillOutstandingAmount = autoBillOutstandingAmount;
	 }
	 
	/**
	 * Getter for maxFailedPayments
	 */
	 public Integer getMaxFailedPayments() {
	 	return maxFailedPayments;
	 }
	 
	/**
	 * Setter for maxFailedPayments
	 */
	 public void setMaxFailedPayments(Integer maxFailedPayments) {
	 	this.maxFailedPayments = maxFailedPayments;
	 }
	 
	/**
	 * Getter for recurringPaymentsProfileDetails
	 */
	 public RecurringPaymentsProfileDetailsType getRecurringPaymentsProfileDetails() {
	 	return recurringPaymentsProfileDetails;
	 }
	 
	/**
	 * Setter for recurringPaymentsProfileDetails
	 */
	 public void setRecurringPaymentsProfileDetails(RecurringPaymentsProfileDetailsType recurringPaymentsProfileDetails) {
	 	this.recurringPaymentsProfileDetails = recurringPaymentsProfileDetails;
	 }
	 
	/**
	 * Getter for currentRecurringPaymentsPeriod
	 */
	 public BillingPeriodDetailsType getCurrentRecurringPaymentsPeriod() {
	 	return currentRecurringPaymentsPeriod;
	 }
	 
	/**
	 * Setter for currentRecurringPaymentsPeriod
	 */
	 public void setCurrentRecurringPaymentsPeriod(BillingPeriodDetailsType currentRecurringPaymentsPeriod) {
	 	this.currentRecurringPaymentsPeriod = currentRecurringPaymentsPeriod;
	 }
	 
	/**
	 * Getter for recurringPaymentsSummary
	 */
	 public RecurringPaymentsSummaryType getRecurringPaymentsSummary() {
	 	return recurringPaymentsSummary;
	 }
	 
	/**
	 * Setter for recurringPaymentsSummary
	 */
	 public void setRecurringPaymentsSummary(RecurringPaymentsSummaryType recurringPaymentsSummary) {
	 	this.recurringPaymentsSummary = recurringPaymentsSummary;
	 }
	 
	/**
	 * Getter for creditCard
	 */
	 public CreditCardDetailsType getCreditCard() {
	 	return creditCard;
	 }
	 
	/**
	 * Setter for creditCard
	 */
	 public void setCreditCard(CreditCardDetailsType creditCard) {
	 	this.creditCard = creditCard;
	 }
	 
	/**
	 * Getter for trialRecurringPaymentsPeriod
	 */
	 public BillingPeriodDetailsType getTrialRecurringPaymentsPeriod() {
	 	return trialRecurringPaymentsPeriod;
	 }
	 
	/**
	 * Setter for trialRecurringPaymentsPeriod
	 */
	 public void setTrialRecurringPaymentsPeriod(BillingPeriodDetailsType trialRecurringPaymentsPeriod) {
	 	this.trialRecurringPaymentsPeriod = trialRecurringPaymentsPeriod;
	 }
	 
	/**
	 * Getter for regularRecurringPaymentsPeriod
	 */
	 public BillingPeriodDetailsType getRegularRecurringPaymentsPeriod() {
	 	return regularRecurringPaymentsPeriod;
	 }
	 
	/**
	 * Setter for regularRecurringPaymentsPeriod
	 */
	 public void setRegularRecurringPaymentsPeriod(BillingPeriodDetailsType regularRecurringPaymentsPeriod) {
	 	this.regularRecurringPaymentsPeriod = regularRecurringPaymentsPeriod;
	 }
	 
	/**
	 * Getter for trialAmountPaid
	 */
	 public BasicAmountType getTrialAmountPaid() {
	 	return trialAmountPaid;
	 }
	 
	/**
	 * Setter for trialAmountPaid
	 */
	 public void setTrialAmountPaid(BasicAmountType trialAmountPaid) {
	 	this.trialAmountPaid = trialAmountPaid;
	 }
	 
	/**
	 * Getter for regularAmountPaid
	 */
	 public BasicAmountType getRegularAmountPaid() {
	 	return regularAmountPaid;
	 }
	 
	/**
	 * Setter for regularAmountPaid
	 */
	 public void setRegularAmountPaid(BasicAmountType regularAmountPaid) {
	 	this.regularAmountPaid = regularAmountPaid;
	 }
	 
	/**
	 * Getter for aggregateAmount
	 */
	 public BasicAmountType getAggregateAmount() {
	 	return aggregateAmount;
	 }
	 
	/**
	 * Setter for aggregateAmount
	 */
	 public void setAggregateAmount(BasicAmountType aggregateAmount) {
	 	this.aggregateAmount = aggregateAmount;
	 }
	 
	/**
	 * Getter for aggregateOptionalAmount
	 */
	 public BasicAmountType getAggregateOptionalAmount() {
	 	return aggregateOptionalAmount;
	 }
	 
	/**
	 * Setter for aggregateOptionalAmount
	 */
	 public void setAggregateOptionalAmount(BasicAmountType aggregateOptionalAmount) {
	 	this.aggregateOptionalAmount = aggregateOptionalAmount;
	 }
	 
	/**
	 * Getter for finalPaymentDueDate
	 */
	 public String getFinalPaymentDueDate() {
	 	return finalPaymentDueDate;
	 }
	 
	/**
	 * Setter for finalPaymentDueDate
	 */
	 public void setFinalPaymentDueDate(String finalPaymentDueDate) {
	 	this.finalPaymentDueDate = finalPaymentDueDate;
	 }
	 
	/**
	 * Getter for softDescriptor
	 */
	 public String getSoftDescriptor() {
	 	return softDescriptor;
	 }
	 
	/**
	 * Setter for softDescriptor
	 */
	 public void setSoftDescriptor(String softDescriptor) {
	 	this.softDescriptor = softDescriptor;
	 }
	 
	/**
	 * Getter for softDescriptorCity
	 */
	 public String getSoftDescriptorCity() {
	 	return softDescriptorCity;
	 }
	 
	/**
	 * Setter for softDescriptorCity
	 */
	 public void setSoftDescriptorCity(String softDescriptorCity) {
	 	this.softDescriptorCity = softDescriptorCity;
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
	
	public GetRecurringPaymentsProfileDetailsResponseDetailsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("ProfileID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.profileID = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("ProfileStatus", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.profileStatus = RecurringPaymentsProfileStatusType.fromValue(childNode.getTextContent());
		}
		childNode = (Node) xpath.evaluate("Description", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.description = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("AutoBillOutstandingAmount", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.autoBillOutstandingAmount = AutoBillType.fromValue(childNode.getTextContent());
		}
		childNode = (Node) xpath.evaluate("MaxFailedPayments", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
			this.maxFailedPayments = Integer.valueOf(childNode.getTextContent());
		}
	
		childNode = (Node) xpath.evaluate("RecurringPaymentsProfileDetails", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.recurringPaymentsProfileDetails =  new RecurringPaymentsProfileDetailsType(childNode);
		}
		childNode = (Node) xpath.evaluate("CurrentRecurringPaymentsPeriod", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.currentRecurringPaymentsPeriod =  new BillingPeriodDetailsType(childNode);
		}
		childNode = (Node) xpath.evaluate("RecurringPaymentsSummary", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.recurringPaymentsSummary =  new RecurringPaymentsSummaryType(childNode);
		}
		childNode = (Node) xpath.evaluate("CreditCard", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.creditCard =  new CreditCardDetailsType(childNode);
		}
		childNode = (Node) xpath.evaluate("TrialRecurringPaymentsPeriod", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.trialRecurringPaymentsPeriod =  new BillingPeriodDetailsType(childNode);
		}
		childNode = (Node) xpath.evaluate("RegularRecurringPaymentsPeriod", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.regularRecurringPaymentsPeriod =  new BillingPeriodDetailsType(childNode);
		}
		childNode = (Node) xpath.evaluate("TrialAmountPaid", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.trialAmountPaid =  new BasicAmountType(childNode);
		}
		childNode = (Node) xpath.evaluate("RegularAmountPaid", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.regularAmountPaid =  new BasicAmountType(childNode);
		}
		childNode = (Node) xpath.evaluate("AggregateAmount", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.aggregateAmount =  new BasicAmountType(childNode);
		}
		childNode = (Node) xpath.evaluate("AggregateOptionalAmount", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.aggregateOptionalAmount =  new BasicAmountType(childNode);
		}
		childNode = (Node) xpath.evaluate("FinalPaymentDueDate", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.finalPaymentDueDate = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("SoftDescriptor", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.softDescriptor = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("SoftDescriptorCity", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.softDescriptorCity = childNode.getTextContent();
		}
	
	}
 
}