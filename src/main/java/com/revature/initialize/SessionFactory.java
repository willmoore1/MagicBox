package com.revature.initialize;

import javax.imageio.spi.ServiceRegistry;

import com.revature.runtime.Session;
import hibernate.cfg.xml;

public class SessionFactory {

	private static SessionFactory sessionFactory = null;
	//manufacture sessions for the postgre
	//business logic for making transaction
	//this should return a new session object based on the configuration used when creating the session factory
	static {
		try {
			LoadSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void loadSessionFactory() {
		
		Configuration configuration = new Configuration();
				configuration.configure("hibernate.cfg.xml");
		        configuration.addResource("magic.hbm.xml");
		        ServiceRegistry srvcReg = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties())
		        		.build();
		        sessionFactory = configuration.buildSessionFactory(srvcReg);
		
	}
	
	public static Session getSession() throws HibernateException {
		
		Session session = null;
		try {
			session = sessionFactory.openSession();
		} catch (Exception e) {
			System.out.println("Exception while opening a session");
			e.printStackTrace();
			// TODO: handle exception
		}
	}
	private static void LoadSessionFactory() {
		// TODO Auto-generated method stub
		
	}

}
