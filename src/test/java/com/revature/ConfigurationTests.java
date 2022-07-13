package com.revature;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.initialize.Configuration;
import com.revature.initialize.SessionFactory;
import com.revature.models.DummyClass;
import com.revature.runtime.Session;

public class ConfigurationTests {
	// Declaring the config class instance to be tested
	private Configuration config;
	private SessionFactory sesFac;
	String className;
	
	@Before
	public void setup() {
		config = new Configuration();
		sesFac = config.configure("magicbox.cfg.xml");
		className = new DummyClass().getClass().getName();
	}
	
	@After
	public void teardown() {
		config = null;
		sesFac = null;
		className = null;
	}
	
	@Test
	public void testSessionFactoryCreateSessionReturnsNotNull() {
		System.out.println("1");
		System.out.println(className);
		Session<?> session = sesFac.createSession(className);
		assertNotNull(session);
	}
	
	@Test
	public void testSessionFactoryCreateSessionReturnsNull() {
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
