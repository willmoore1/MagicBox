package com.revature.runtime;

public class Session {

	
	//this should save the input to the database used to create the session
	public Object save(Object input) { 
		
		return null;
	}
	
	//this should be used for making savepoints and either rolling back or committing
	public Transaction beginTransaction() {
		
		return null;
	}
	
	//this should return a row from the database
	public Object get(Object input) {
		
		return null;
	}
	
	//this should close the current session object
	public void close() {
		
	}
	
	//this should run string s as a sql command and return the result 
	public Object createNativeQuery(String s, Class<?> c) {
		return null;
	}
	
}
