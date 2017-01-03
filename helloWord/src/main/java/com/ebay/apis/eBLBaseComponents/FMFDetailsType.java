package com.ebay.apis.eBLBaseComponents;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Thes are filters that could result in accept/deny/pending
 * action. 
 */
public class FMFDetailsType{


	/**
	 * 	 
	 */ 
	private RiskFilterListType acceptFilters;

	/**
	 * 	 
	 */ 
	private RiskFilterListType pendingFilters;

	/**
	 * 	 
	 */ 
	private RiskFilterListType denyFilters;

	/**
	 * 	 
	 */ 
	private RiskFilterListType reportFilters;

	

	/**
	 * Default Constructor
	 */
	public FMFDetailsType (){
	}	

	/**
	 * Getter for acceptFilters
	 */
	 public RiskFilterListType getAcceptFilters() {
	 	return acceptFilters;
	 }
	 
	/**
	 * Setter for acceptFilters
	 */
	 public void setAcceptFilters(RiskFilterListType acceptFilters) {
	 	this.acceptFilters = acceptFilters;
	 }
	 
	/**
	 * Getter for pendingFilters
	 */
	 public RiskFilterListType getPendingFilters() {
	 	return pendingFilters;
	 }
	 
	/**
	 * Setter for pendingFilters
	 */
	 public void setPendingFilters(RiskFilterListType pendingFilters) {
	 	this.pendingFilters = pendingFilters;
	 }
	 
	/**
	 * Getter for denyFilters
	 */
	 public RiskFilterListType getDenyFilters() {
	 	return denyFilters;
	 }
	 
	/**
	 * Setter for denyFilters
	 */
	 public void setDenyFilters(RiskFilterListType denyFilters) {
	 	this.denyFilters = denyFilters;
	 }
	 
	/**
	 * Getter for reportFilters
	 */
	 public RiskFilterListType getReportFilters() {
	 	return reportFilters;
	 }
	 
	/**
	 * Setter for reportFilters
	 */
	 public void setReportFilters(RiskFilterListType reportFilters) {
	 	this.reportFilters = reportFilters;
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
	
	public FMFDetailsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("AcceptFilters", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.acceptFilters =  new RiskFilterListType(childNode);
		}
		childNode = (Node) xpath.evaluate("PendingFilters", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.pendingFilters =  new RiskFilterListType(childNode);
		}
		childNode = (Node) xpath.evaluate("DenyFilters", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.denyFilters =  new RiskFilterListType(childNode);
		}
		childNode = (Node) xpath.evaluate("ReportFilters", node, XPathConstants.NODE);
        if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.reportFilters =  new RiskFilterListType(childNode);
		}
	}
 
}