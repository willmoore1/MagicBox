package com.revature.initialize;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

public class Configuration {
	
	private static ConnectionUtil connUtil = null;
	
	// This should return a new sessionfactory object based on a config file name as the input
	public SessionFactory configure(String s) {
	      // Instantiate the Factory
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

	      try {

	          // Optional, but recommended
	          // Process XML securely, avoid attacks like XML External Entities (XXE)
	          dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

	          // parse XML file
	          DocumentBuilder db = dbf.newDocumentBuilder();

	          Document doc = db.parse(new File("src/main/java/resource/" + s));

	          doc.getDocumentElement().normalize();	       
	          
	          NodeList list = doc.getElementsByTagName("property");
	          
	          connUtil = new ConnectionUtil();
	          
	          // User credentials
	          String url = null;
	          String username = null;
	          String password = null;
	          String poolSize = null;
	          
	          // Read credentials and connect to DB
	          for (int temp = 0; temp < list.getLength(); temp++) {

	              Node node = list.item(temp);

	              if (node.getNodeType() == Node.ELEMENT_NODE) {

	                  Element element = (Element) node;
	                  String name = element.getAttribute("name");	                 

	                  System.out.println("Current Element :" + node.getNodeName());
	                  System.out.println("Property : " + name);
	                  System.out.println(element.getTextContent());
	                  
	                  if(name.equals("magicbox.connection.url")) {
	                	  url = element.getTextContent();
	                  }
	                  else if(name.equals("magicbox.connection.username")) {
	                	  username = element.getTextContent();
	                  }
	                  else if(name.equals("magicbox.connection.password")) {
	                	  password = element.getTextContent();
	                  }
	                  else if(name.equals("magicbox.connection.pool_size")) {
	                	  poolSize = element.getTextContent();
	                  }
	             }
	              
	          }
	          
	          connUtil.properties(url, username, password, poolSize);	          

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      } 
		return null;
	}
	
}
