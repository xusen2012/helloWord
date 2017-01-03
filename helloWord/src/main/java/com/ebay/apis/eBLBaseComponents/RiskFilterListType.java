package com.ebay.apis.eBLBaseComponents;
import java.util.List;
import java.util.ArrayList;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Details of Risk Filter. 
 */
public class RiskFilterListType{


	/**
	 * 	  
	 *@Required	 
	 */ 
	private List<RiskFilterDetailsType> filters = new ArrayList<RiskFilterDetailsType>();

	

	/**
	 * Default Constructor
	 */
	public RiskFilterListType (){
	}	

	/**
	 * Getter for filters
	 */
	 public List<RiskFilterDetailsType> getFilters() {
	 	return filters;
	 }
	 
	/**
	 * Setter for filters
	 */
	 public void setFilters(List<RiskFilterDetailsType> filters) {
	 	this.filters = filters;
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
	
	public RiskFilterListType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
        nodeList = (NodeList) xpath.evaluate("Filters", node, XPathConstants.NODESET);
		if (nodeList != null && nodeList.getLength() > 0) {
			for(int i=0; i < nodeList.getLength(); i++) {
				Node subNode = nodeList.item(i);
				this.filters.add(new RiskFilterDetailsType(subNode));
			}
		}
	}
 
}