package com.servlets;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.carlibrary.*;

/**
 * Servlet implementation class SpecificVehicle
 */
@WebServlet("/vehicles/*")
public class SpecificVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SpecificVehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uri = request.getRequestURI();
		String contextPath = request.getContextPath();
		int uriLength = uri.length();
		String st = contextPath + "/vehicles/";
		int length = st.length();
		
		if(uriLength > length) {
			
			Connection c = DBconnection.getConnection();
			PreparedStatement s = null;
			ResultSet r = null;
			InputStream is = null;
			ObjectInputStream in = null;
			String plate = uri.substring(length);
			
			if(c != null) {
				try {
					s = c.prepareStatement("SELECT car FROM cars WHERE plate = ?");
					s.setString(1, plate);
					r = s.executeQuery();
					
					if(r.next()) {
						//retrieve car blob and deserialize:
						Blob carBlob = r.getBlob("car");
						is = carBlob.getBinaryStream();
						in = new ObjectInputStream(is);
						Car car = (Car) in.readObject();
						
						//set request attributes:
						request.setAttribute("plate", plate);
						request.setAttribute("type", car.getType());
						request.setAttribute("make", car.getMake());
						request.setAttribute("model", car.getModel());
						request.setAttribute("year", car.getYear());
						request.setAttribute("odometer", car.getOdometer());
						request.setAttribute("scheduledTasks", car.getScheduledTasks());
						request.setAttribute("statuses", car.getStatuses());
						request.getRequestDispatcher("/WEB-INF/specificVehicle.jsp").forward(request, response);
					}
					else {
						request.setAttribute("msg", "The vehicle with plate " + plate + " doesn't exist!");
						request.getRequestDispatcher("/WEB-INF/msg.jsp").forward(request, response);
					}
				}
				catch(SQLException e) {
					
				}
				catch(IOException e) {
					
				}
				catch(ClassNotFoundException e) {
					
				}
				finally {
					try {
						in.close();
					}
					catch (Exception e) {
						
					}
					try {
						is.close();
					}
					catch (Exception e) {
						
					}
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
		else {
			response.sendRedirect(contextPath + "/vehicles");
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
