package com.revature;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.util.List;

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
		DummyClass dumObj = new DummyClass(1, "className", 54);
		//ses.save(dumObj);
		//ses.commit();
		List<DummyClass> getObj = ses.get("testid", 1);
		System.out.println(getObj.toString());
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testSessionFactorySave2() {
		String className2 = new DummyClass2().getClass().getName();
		Session<DummyClass2> ses = (Session<DummyClass2>) sesFac.createSession(className2);
		DummyClass2 dumObj = new DummyClass2(1, "className", 54,43.2);
		DummyClass2 dumObj2 = new DummyClass2(2,"wat",34,23.1);
		DummyClass2 dumObj3 = new DummyClass2("rel",23,12.5);
		/*ses.deleteAll();
		ses.save(dumObj);
		ses.save(dumObj2);
		ses.save(dumObj3);
		ses.commit();*/
		List<DummyClass2> getObj = ses.get("testid", 0);
		System.out.println(getObj.toString());
	}
}
