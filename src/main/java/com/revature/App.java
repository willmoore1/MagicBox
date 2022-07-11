package com.revature;

import com.revature.runtime.Column;
import com.revature.runtime.Session;
import com.revature.runtime.Table;

public class App {
	
	
	public static void main(String[] args) {
		
		testObject tya = new testObject();
		
		Session session = new Session();
		session.save(tya);
		
		
	}
	
	
}
