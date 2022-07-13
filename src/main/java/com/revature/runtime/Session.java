package com.revature.runtime;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.initialize.ColumnField;
import com.revature.initialize.MetaModel;

//gonna have to use reflections api
public class Session<T> {
	Connection connection;

	private MetaModel<T> model;
	private List<T> cache = null;
	private String table;

	// this should save the input to a local cache
	public void save(T input) {
		if (this.cache == null)
			cache = new ArrayList<T>();
		cache.add(input);
	}

	// this should return a row from the database
	public List<T> get(String colName, Object value) {

		String sql = "SELECT * FROM " + table + " WHERE " + colName + " = ?;";
		PreparedStatement stmt;
		List<T> allVals = new ArrayList<>();
		Object[] fieldVals = new Object[model.getColumns().size()];
		try {
			stmt = connection.prepareStatement(sql);
			stmt.setObject(1, value);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				for (int i = 0; i < fieldVals.length; i++) {
					fieldVals[i] = rs.getObject(i);
				}
				allVals.add(model.getObject(fieldVals));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return allVals;
	}

	// this should run string s as a sql command and return the result
	public T createNativeQuery(String s, Class<?> c) {
		return null;
	}

	// this should commit changes that have been made to the database since the last
	// commit
	public void commit() {

		List<ColumnField> fields = model.getColumns();
		for (T o : this.cache) {

			String sql = "INSERT INTO " + table + " (";
			for (ColumnField field : fields) {
				sql += field.getColumnName();
			}
			sql += ") VALUES (?";
			for (int i = 1; i < fields.size(); i++) {
				sql += ",?";
			}
			sql += ");";

			try {
				PreparedStatement stmt = connection.prepareStatement(sql);
				for (int i = 0; i < fields.size(); i++) {
					stmt.setObject(i + 1, fields.get(i).getValue(o));
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
		//System.out.println("model" + model.toString());
		this.connection = conn;
		this.model = model;
		table = model.getTableName();
		int temp = 0;
		String sql = "CREATE TABLE IF NOT EXISTS " + table + " (";
		for(ColumnField f : model.getColumns()) {
			if(temp++ > 0) {
				sql += ", ";
			}
			sql += f.getColumnName();
			System.out.println(f.getColumnName());
		}
		sql += ");";
		System.out.println(sql);
		try {
			Statement stmt = connection.createStatement();
			stmt.execute(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

	public boolean deleteAll() {
		String sql = "TRUNCATE " + table + " CASCADE;";
		Statement stmt;
		try {
			stmt = connection.createStatement();
			stmt.execute(sql);
			return true;
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return false;
	}

}