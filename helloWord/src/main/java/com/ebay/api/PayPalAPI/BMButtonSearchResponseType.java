package com.ebay.api.PayPalAPI;
import java.util.List;
import java.util.ArrayList;
import com.ebay.apis.eBLBaseComponents.ButtonSearchResultType;
import com.ebay.apis.eBLBaseComponents.AbstractResponseType;

import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * 
 */
public class BMButtonSearchResponseType extends AbstractResponseType {


	/**
	 * 	 
	 */ 
	private List<ButtonSearchResultType> buttonSearchResult = new ArrayList<ButtonSearchResultType>();

	

	/**
	 * Default Constructor
	 */
	public BMButtonSearchResponseType (){
	}	

	/**
	 * Getter for buttonSearchResult
	 */
	 public List<ButtonSearchResultType> getButtonSearchResult() {
	 	return buttonSearchResult;
	 }
	 
	/**
	 * Setter for buttonSearchResult
	 */
	 public void setButtonSearchResult(List<ButtonSearchResultType> buttonSearchResult) {
	 	this.buttonSearchResult = buttonSearchResult;
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
	
	public BMButtonSearchResponseType(Node node) throws XPathExpressionException {
		super(node);
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
        nodeList = (NodeList) xpath.evaluate("ButtonSearchResult", node, XPathConstants.NODESET);
		if (nodeList != null && nodeList.getLength() > 0) {
			for(int i=0; i < nodeList.getLength(); i++) {
				Node subNode = nodeList.item(i);
				this.buttonSearchResult.add(new ButtonSearchResultType(subNode));
			}
		}
	}
 
}