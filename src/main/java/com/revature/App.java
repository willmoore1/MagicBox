package com.revature;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import com.revature.util.ConnectionUtil;

public class App {
	public static void main(String[] args) {
		try {
			
			Connection conn = ConnectionUtil.getConnection();
			System.out.println(conn.getNetworkTimeout());
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
	
	
}
