package com.revature.initialize;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

// Let's use HikariCP to do connection pooling
// https://www.baeldung.com/hikaricp
public class ConnectionUtil {
	private HikariConfig config = new HikariConfig();
	private HikariDataSource ds;
	private String url = "jdbc:postgresql://database-2.cxoqvipxbnxl.us-east-2.rds.amazonaws.com:5432/postgres";
	private String poolSize = "10";
	private String username = "postgres";
	private String password = "masterpassword";
	
//	protected ConnectionUtil(String url, String username, String password) {
//		ConnectionUtil.username = username;
//		ConnectionUtil.password = password;
//		ConnectionUtil.url = url;
//	}
	
	protected void properties(String url, String username, String password) {
		this.username = username;
		this.password = password;
		this.url = url;
		ds = new HikariDataSource(config);
	}
	protected void properties(String url, String username, String password, String poolSize) {
		this.username = username;
		this.password = password;
		this.url = url;
		this.poolSize = poolSize;
		ds = new HikariDataSource(config);
	}
	
	
	public String getPoolSize() {
		return poolSize;
	}

	public void setPoolSize(String poolSize) {
		this.poolSize = poolSize;
	}

	{
		config.setJdbcUrl(url);
		config.setUsername(username);
		config.setPassword(password);
		config.addDataSourceProperty("CachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.addDataSourceProperty("maximumPoolSize", poolSize);
	}
	
//	public static List<Connection> getAllConnections() throws SQLException {
//		List<Connection> conList = new ArrayList<Connection>(Integer.valueOf(poolSize));
//		for(int i = 0; i < Integer.valueOf(poolSize); i++) {
//			conList.add(ds.getConnection());
//		}
//		return conList;
//	}
//	
	public Connection getConnection() throws SQLException {
		if(ds != null) {
			return ds.getConnection();
		} else {
			ds = new HikariDataSource(config);
			return ds.getConnection();
		}
		
	}
	
	
}
