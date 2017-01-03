package com.ebay.api.PayPalAPI;
import java.util.List;
import java.util.ArrayList;
import com.ebay.apis.eBLBaseComponents.PaymentTransactionSearchResultType;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Results of a Transaction Search.
 */
public class TransactionSearchResponseType extends AbstractResponseType {


	/**
	 * Results of a Transaction Search.	 
	 */ 
	private List<PaymentTransactionSearchResultType> paymentTransactions = new ArrayList<PaymentTransactionSearchResultType>();

	

	/**
	 * Default Constructor
	 */
	public TransactionSearchResponseType (){
	}	

	/**
	 * Getter for paymentTransactions
	 */
	 public List<PaymentTransactionSearchResultType> getPaymentTransactions() {
	 	return paymentTransactions;
	 }
	 
	/**
	 * Setter for paymentTransactions
	 */
	 public void setPaymentTransactions(List<PaymentTransactionSearchResultType> paymentTransactions) {
	 	this.paymentTransactions = paymentTransactions;
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
	
	public TransactionSearchResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
        nodeList = (NodeList) xpath.evaluate("PaymentTransactions", node, XPathConstants.NODESET);
		if (nodeList != null && nodeList.getLength() > 0) {
			for(int i=0; i < nodeList.getLength(); i++) {
				Node subNode = nodeList.item(i);
				this.paymentTransactions.add(new PaymentTransactionSearchResultType(subNode));
			}
		}
	}
 
}