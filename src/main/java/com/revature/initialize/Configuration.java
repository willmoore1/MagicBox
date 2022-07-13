package com.revature.initialize;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class Configuration {

	private ConnectionUtil connUtil = null;
	private Map<String, MetaModel<Class<?>>> metaModelMap;

	public Configuration() {
		connUtil = new ConnectionUtil();
		metaModelMap = new HashMap<>();
	}

	// This should return a new sessionfactory object based on a config file name as
	// the input
	public SessionFactory configure(String fileName) {
		Document doc = parseFile(fileName);
		
		if(doc == null) 
			return null;
		
		setConnectionUtil(doc);

		addClasses(doc);

		return new SessionFactory(connUtil, metaModelMap);
	}

	public void addClass(String className) {
		try {
			metaModelMap.put(className, MetaModel.of(Class.forName(className)));
		} catch (ClassNotFoundException e) {
			System.out.println("Class " + className + " not found!");
		}
	}

	// Attempts to parse file with the given file name. Returns null if unsuccessful
	private Document parseFile(String fileName) {
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		try {
			// Process XML securely, avoid attacks like XML External Entities (XXE)
			dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

			// parse XML file
			DocumentBuilder db = dbf.newDocumentBuilder();

			Document doc = db.parse(new File(fileName));

			doc.getDocumentElement().normalize();

			return doc;
		} catch (Exception e) {
			System.out.println("Could not parse file!");
			return null;
		}
	}

	// Sets connectionUtil up
	private void setConnectionUtil(Document doc) {
		NodeList list = doc.getElementsByTagName("property");

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

				if (name.equals("magicbox.connection.url")) {
					url = element.getTextContent();
				} else if (name.equals("magicbox.connection.username")) {
					username = element.getTextContent();
				} else if (name.equals("magicbox.connection.password")) {
					password = element.getTextContent();
				} else if (name.equals("magicbox.connection.pool_size")) {
					poolSize = element.getTextContent();
				}
			}

		}

		// Set up DB credentials and pool size
		connUtil.properties(url, username, password, poolSize);
	}
	
	// Add classes from given document into metaModelMap
	private void addClasses(Document doc) {
		NodeList list = doc.getElementsByTagName("mapping");

		// Read classes
		for (int temp = 0; temp < list.getLength(); temp++) {

			Node node = list.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {

				Element element = (Element) node;
				String className = element.getAttribute("class");

				addClass(className);
			}
		}
	}

}
