package com.revature.initialize;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.imageio.spi.ServiceRegistry;

import com.revature.runtime.Session;

public class SessionFactory {
	
	private ConnectionUtil connUtil;
	private Map<String, MetaModel<Class<?>>> metaModelMap;

	protected SessionFactory(ConnectionUtil connUtil, Map<String, MetaModel<Class<?>>> metaModelMap) {
		this.connUtil = connUtil;
		this.metaModelMap = metaModelMap;
	}
	
	public Session createSession(Class<?> clazz) {
		try {
		String name = clazz.getClass().getSimpleName();
		
		if(!metaModelMap.containsKey(name)) {
			System.out.println("Class not found!");
			return null;
			
		}
		return new Session(connUtil.getConnection(), metaModelMap.get(name));
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}
