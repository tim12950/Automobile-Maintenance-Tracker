package com.servlets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnection {
	
	public static Connection getConnection() {
		
		Connection c = null;
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection("jdbc:mysql://host:port/db", "username", "password");
		}
		catch (ClassNotFoundException e) {
			
		}
		catch (SQLException e) {
			
		}
		
		return c;
	}

}
