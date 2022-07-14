package com.revature;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.initialize.Configuration;
import com.revature.runtime.Session;

public class SessionTests {


	public class ConfigurationTests {
		// Declaring the config class instance to be tested
		private Configuration config;
		private Session<?> ses;
		
		@Before
		public void setup() {
			config = new Configuration();
		}
		
		@After
		public void teardown() {
			if(ses.deleteAll()) System.out.println("Truncate");
			else System.out.println("TruncError");
			config = null;
			
		}
		
		@Test
		public void testConfigureMethodFindsFile() {
			config.configure("magicbox.cfg.xml");
		}
		@Test
		public void testSessionAdd() {
			ses = config.configure("magicbox.cfg.xml").createSession("DummyClass");
		}
	}

}