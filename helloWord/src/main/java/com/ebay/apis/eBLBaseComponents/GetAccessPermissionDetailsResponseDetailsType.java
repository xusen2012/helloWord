package com.ebay.apis.eBLBaseComponents;
import java.util.List;
import java.util.ArrayList;
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
 * The first name of the User. Character length and
 * limitations: 127 single-byte alphanumeric characters 
 */
public class GetAccessPermissionDetailsResponseDetailsType{


	/**
	 * The first name of the User. Character length and
	 * limitations: 127 single-byte alphanumeric characters	 
	 */ 
	private String firstName;

	/**
	 * The Last name of the user. Character length and limitations:
	 * 127 single-byte alphanumeric characters 	 
	 */ 
	private String lastName;

	/**
	 * The email address of the user. Character length and
	 * limitations: 256 single-byte alphanumeric characters. 	 
	 */ 
	private String email;

	/**
	 * contains information about API Services 	 
	 */ 
	private List<String> accessPermissionName = new ArrayList<String>();

	/**
	 * contains information about API Services 	 
	 */ 
	private List<String> accessPermissionStatus = new ArrayList<String>();

	/**
	 * Encrypted PayPal customer account identification number.
	 * Required Character length and limitations: 127 single-byte
	 * characters. 	 
	 */ 
	private String payerID;

	

	/**
	 * Default Constructor
	 */
	public GetAccessPermissionDetailsResponseDetailsType (){
	}	

	/**
	 * Getter for firstName
	 */
	 public String getFirstName() {
	 	return firstName;
	 }
	 
	/**
	 * Setter for firstName
	 */
	 public void setFirstName(String firstName) {
	 	this.firstName = firstName;
	 }
	 
	/**
	 * Getter for lastName
	 */
	 public String getLastName() {
	 	return lastName;
	 }
	 
	/**
	 * Setter for lastName
	 */
	 public void setLastName(String lastName) {
	 	this.lastName = lastName;
	 }
	 
	/**
	 * Getter for email
	 */
	 public String getEmail() {
	 	return email;
	 }
	 
	/**
	 * Setter for email
	 */
	 public void setEmail(String email) {
	 	this.email = email;
	 }
	 
	/**
	 * Getter for accessPermissionName
	 */
	 public List<String> getAccessPermissionName() {
	 	return accessPermissionName;
	 }
	 
	/**
	 * Setter for accessPermissionName
	 */
	 public void setAccessPermissionName(List<String> accessPermissionName) {
	 	this.accessPermissionName = accessPermissionName;
	 }
	 
	/**
	 * Getter for accessPermissionStatus
	 */
	 public List<String> getAccessPermissionStatus() {
	 	return accessPermissionStatus;
	 }
	 
	/**
	 * Setter for accessPermissionStatus
	 */
	 public void setAccessPermissionStatus(List<String> accessPermissionStatus) {
	 	this.accessPermissionStatus = accessPermissionStatus;
	 }
	 
	/**
	 * Getter for payerID
	 */
	 public String getPayerID() {
	 	return payerID;
	 }
	 
	/**
	 * Setter for payerID
	 */
	 public void setPayerID(String payerID) {
	 	this.payerID = payerID;
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
	
	public GetAccessPermissionDetailsResponseDetailsType(Node node) throws XPathExpressionException {
		XPathFactory factory = XPathFactory.newInstance();
		XPath xpath = factory.newXPath();
		Node childNode = null;
		NodeList nodeList = null;
		childNode = (Node) xpath.evaluate("FirstName", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.firstName = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("LastName", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.lastName = childNode.getTextContent();
		}
	
		childNode = (Node) xpath.evaluate("Email", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.email = childNode.getTextContent();
		}
	
        nodeList = (NodeList) xpath.evaluate("AccessPermissionName", node, XPathConstants.NODESET);
		if (nodeList != null && nodeList.getLength() > 0) {
			for(int i=0; i < nodeList.getLength(); i++) {
			    Node subNode = nodeList.item(i);
			    String value = subNode.getTextContent();
			    this.accessPermissionName.add(value);
					
			}
		}
        nodeList = (NodeList) xpath.evaluate("AccessPermissionStatus", node, XPathConstants.NODESET);
		if (nodeList != null && nodeList.getLength() > 0) {
			for(int i=0; i < nodeList.getLength(); i++) {
			    Node subNode = nodeList.item(i);
			    String value = subNode.getTextContent();
			    this.accessPermissionStatus.add(value);
					
			}
		}
		childNode = (Node) xpath.evaluate("PayerID", node, XPathConstants.NODE);
		if (childNode != null && !isWhitespaceNode(childNode)) {
		    this.payerID = childNode.getTextContent();
		}
	
	}
 
}