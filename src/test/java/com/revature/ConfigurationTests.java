package com.revature;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.initialize.Configuration;
import com.revature.initialize.SessionFactory;
import com.revature.models.DummyClass;
import com.revature.models.DummyClass2;
import com.revature.runtime.Session;

public class ConfigurationTests {
	// Declaring the config class instance to be tested
	private Configuration config;
	private String filePath = "src/main/java/resource/magicbox.cfg.xml";
	private SessionFactory sesFac;
	String className;
	
	@Before
	public void setup() {
		config = new Configuration();
		sesFac = config.configure(filePath);
		className = new DummyClass().getClass().getName();
	}
	
	@After
	public void teardown() {
		config = null;
		sesFac = null;
		className = null;
	}
	
	@Test
	public void testConfigureMethodFindsFile() {
		config.configure(filePath);
	}
	
	@Test
	public void testSessionFactoryCreateSessionReturnsNotNull() {
		String name = new DummyClass().getClass().getName();
		
		SessionFactory sessionFactory = config.configure(filePath);
		
		System.out.println("1");
		System.out.println(className);
		
		Session<?> session = sesFac.createSession(className);
		
		assertNotNull(session);
	}
	
	@Test
	public void testConfigAddClass() {
		String name = new DummyClass().getClass().getName();
		config.addClass(name);
		
		SessionFactory sessionFactory = config.configure(filePath);
		
		Session<?> session = sessionFactory.createSession(name);
		
		assertNotNull(session);
	}
	
	@Test
	public void testSessionFactoryCreateSessionReturnsNull() {
		SessionFactory sessionFactory = config.configure(filePath);
		Session<?> session = sesFac.createSession(new String());
		System.out.println("2");
		assertNull(session);
	}
	
	@Test
	public void testSessionFactorySave() {
		@SuppressWarnings("unchecked")
		Session<DummyClass> ses = (Session<DummyClass>) sesFac.createSession(className);
		DummyClass dumObj = new DummyClass();
		ses.save(dumObj);
		ses.commit();
		ses.get("test_id", dumObj.getTestId());
	}
}
