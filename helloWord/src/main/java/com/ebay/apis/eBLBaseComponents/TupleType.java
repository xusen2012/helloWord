package com.ebay.apis.eBLBaseComponents;
import com.paypal.core.SDKUtil;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.NamedNodeMap;
import java.io.FileInputStream;
import java.io.StringReader;
import java.io.IOException;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

/**
 * This holds single key-value pair. 
 */
public class TupleType{

	private static final String nameSpace="urn:ebay:apis:eBLBaseComponents";
	private static final String preferredPrefix="ebl";

	/**
	 * Key Name.Optional	  
	 *@Required	 
	 */ 
	private String key;

	/**
	 * Value for the above key.Optional	  
	 *@Required	 
	 */ 
	private String value;

	

	/**
	 * Constructor with arguments
	 */
	public TupleType (String key, String value){
		this.key = key;
		this.value = value;
	}	

	/**
	 * Default Constructor
	 */
	public TupleType (){
	}	

	/**
	 * Getter for key
	 */
	 public String getKey() {
	 	return key;
	 }
	 
	/**
	 * Setter for key
	 */
	 public void setKey(String key) {
	 	this.key = key;
	 }
	 
	/**
	 * Getter for value
	 */
	 public String getValue() {
	 	return value;
	 }
	 
	/**
	 * Setter for value
	 */
	 public void setValue(String value) {
	 	this.value = value;
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
		if(key != null) {
			sb.append("<").append(preferredPrefix).append(":Key>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.key));
			sb.append("</").append(preferredPrefix).append(":Key>");
		}
		if(value != null) {
			sb.append("<").append(preferredPrefix).append(":Value>").append(SDKUtil.escapeInvalidXmlCharsRegex(this.value));
			sb.append("</").append(preferredPrefix).append(":Value>");
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
	
	public TupleType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("Key", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.key = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("Value", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.value = childNode.getTextContent();
		}
	
	}
 
}