package com.revature.runtime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.annotations.Table;
import com.revature.initialize.ColumnField;
import com.revature.initialize.MetaModel;


//gonna have to use reflections api
public class Session<T> {
	Connection connection;
	private List<Object> cache = null;
	private MetaModel<T> model;
	
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
			List<ColumnField> fields = model.getColumns();
			String sql = "INSERT INTO " + o.getClass().getAnnotation(Table.class).name() + " (";
			for(ColumnField field : fields) {
				sql += field.getColumnName();
			}					
			sql += ") VALUES (?";
			for(int i = 1; i < fields.size(); i++) {
				sql += ",?";
			}
			sql += ");";
			
			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				for(int i = 0; i < fields.size(); i++) {
					stmt.setObject(i+1, fields.get(i).getValue(o));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
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
	
	public Session(Connection conn, MetaModel<T> model) {
		this.connection = conn;
		this.model = model;
		
	}
	
}