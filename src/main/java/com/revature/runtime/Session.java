package com.revature.runtime;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


//gonna have to use reflections api
public class Session {
	Connection connection;
	private List<Object> cache = null;
	private List<Field> fields = null;
	
	//this should save the input to a local cache
	public void save(Object input) { 
		if(this.cache == null) cache = new ArrayList<Object>();
		cache.add(input);
	}
	
	//this should return a row from the database
	public Object get(String input) {
		return null;
	}
	
	//this should run string s as a sql command and return the result 
	public Object createNativeQuery(String s, Class<?> c) {
		return null;
	}
	
	// this should commit changes that have been made to the database since the last
	// commit
	public void commit() {

		
		for(Object o : this.cache) {
			Field[] fields = o.getClass().getFields();
			String sql = "INSERT INTO " + o.getClass().getAnnotation(Table.class).name() + " (";
			for(Field field : fields) {
				if(field.isAnnotationPresent(Column.class)) {
					sql += field.getAnnotation(Column.class).name()+", ";
				} else {
					sql += field.getName()+", ";
				}
			}					
			sql += ") VALUES (?";
			for(int i = 1; i < fields.length; i++) {
				sql += ",?";
			}
			sql += ");";
			
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				for(int i = 0; i < fields.length; i++) {
					stmt.setObject(i+1, fields[i].get(o));
				}
				
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.cache = null;
	}

	// this should return to the previous save point without committing
	public void rollback() {
		this.cache = null;
	}
	
	public void close() {
		this.cache = null;
		this.connection = null;
	}
	
	public Session(Connection conn) {
		this.connection = conn;
		
	}
	
}
