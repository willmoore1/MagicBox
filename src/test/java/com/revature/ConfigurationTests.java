package com.revature;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.revature.initialize.Configuration;

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
}
