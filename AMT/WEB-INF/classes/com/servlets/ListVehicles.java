package com.servlets;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class list
 */
@WebServlet("/vehicles")
public class ListVehicles extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ListVehicles() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		Connection c = DBconnection.getConnection();
		PreparedStatement s = null;
		ResultSet r = null;
		if (c != null) {
			try {
				s = c.prepareStatement("SELECT plate FROM cars");
				r = s.executeQuery();
				ArrayList<String> plates = new ArrayList<String>();
				
				while(r.next()) {
					plates.add(r.getString("plate"));
				}
				Collections.sort(plates);
				request.setAttribute("plates", plates);
				request.getRequestDispatcher("/WEB-INF/listVehicles.jsp").forward(request, response);
			} 
			catch (SQLException e) {
				request.setAttribute("msg", "Database Error. Please try again later.");
				request.getRequestDispatcher("/WEB-INF/msg.jsp").forward(request, response);
			}
			finally {
				try {
					r.close();
				}
				catch (Exception e) {
					
				}
				try {
					s.close();
				}
				catch (Exception e) {
					
				}
				try {
					c.close();
				}
				catch (Exception e) {
					
				}
			}
		}
		else {
			request.setAttribute("msg", "Database Error. Please try again later.");
			request.getRequestDispatcher("/WEB-INF/msg.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
