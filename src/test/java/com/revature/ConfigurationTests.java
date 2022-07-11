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
	
	@Before
	public void setup() {
		config = new Configuration();
	}
	
	@After
	public void teardown() {
		config = null;
	}
	
	@Test
	public void testConfigureMethodFindsFile() {
		config.configure("magicbox.cfg.xml");
	}
	
	@Test
	public void testSessionFactoryCreateSessionReturnsNotNull() {
		SessionFactory sessionFactory = config.configure("magicbox.cfg.xml");
		Session session = sessionFactory.createSession(new DummyClass().getClass().getName());
		assertNotNull(session);
	}
	
	@Test
	public void testSessionFactoryCreateSessionReturnsNull() {
		SessionFactory sessionFactory = config.configure("magicbox.cfg.xml");
		Session session = sessionFactory.createSession(new String());
		assertNull(session);
	}
}
