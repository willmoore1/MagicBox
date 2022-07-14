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
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

public class Configuration {
	
	private static ConnectionUtil connUtil = null;
	private Map<String, MetaModel<Class<?>>> metaModelMap = null;
	
	// This should return a new sessionfactory object based on a config file name as the input
	public SessionFactory configure(String s) {
	      // Instantiate the Factory and linked list
	      DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
	      metaModelMap = new HashMap<>();

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
	          
	          // Read credentials
	          for (int temp = 0; temp < list.getLength(); temp++) {

	              Node node = list.item(temp);

	              if (node.getNodeType() == Node.ELEMENT_NODE) {

	                  Element element = (Element) node;
	                  String name = element.getAttribute("name");	                 
	                  
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
	          
	          // Set up DB credentials and pool size. Note that this does not actually create a connection; the credentials are set up without any attempt to verify them
	          connUtil.properties(url, username, password, poolSize);	         
	          
	          list = doc.getElementsByTagName("mapping");
	         
	          // Read classes
	          for (int temp = 0; temp < list.getLength(); temp++) {

	              Node node = list.item(temp);

	              if (node.getNodeType() == Node.ELEMENT_NODE) {

	                  Element element = (Element) node;
	                  String className = element.getAttribute("class");	   
	                  //System.out.println(className);
	                  
	                  metaModelMap.put(className, MetaModel.of(Class.forName(className)));
	                  
	             }
	              
	          }
      	      System.out.println(metaModelMap.toString());
	          return new SessionFactory(connUtil, metaModelMap);

	      } catch (ParserConfigurationException | SAXException | IOException e) {
	          e.printStackTrace();
	      } catch(ClassNotFoundException e) {
	    	  e.printStackTrace();
	      }
		return null;
	}
	
}
