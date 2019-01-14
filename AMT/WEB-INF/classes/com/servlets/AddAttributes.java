package com.servlets;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

public class AddAttributes {
	
	public static void addAttributes(HttpServletRequest request) throws SQLException {
		
		Connection c = DBconnection.getConnection();
		PreparedStatement s = null;
		ResultSet r = null;
		
		if(c != null) {
			
			try {
				s = c.prepareStatement("SELECT plate FROM cars");
				r = s.executeQuery();
				ArrayList<String> plates = new ArrayList<String>();
				
				while(r.next()) {
					plates.add(r.getString("plate"));
				}
				Collections.sort(plates);
				request.setAttribute("plates", plates);
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
