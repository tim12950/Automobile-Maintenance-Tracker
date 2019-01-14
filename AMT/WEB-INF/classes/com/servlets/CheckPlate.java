package com.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CheckPlate {
	
	public static boolean checkPlate(String plate) throws SQLException{
		
		Connection c = DBconnection.getConnection();
		PreparedStatement s = null;
		ResultSet r = null;
		
		if(c != null) {
			
			try {
				s = c.prepareStatement("SELECT plate from cars WHERE plate = ?");
				s.setString(1, plate);
				r = s.executeQuery();
				if(r.next()) {
					return true;
				}
				else {
					return false;
				}
			}
			catch(SQLException e) {
				throw e;
			}
			finally {
				try {
					r.close();
				}
				catch(Exception e) {
					
				}
				try {
					s.close();
				}
				catch(Exception e) {
					
				}
				try {
					c.close();
				}
				catch(Exception e) {
					
				}
			}	
		}
		else {
			throw new SQLException();
		}
	}

}
