package com.servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
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

import com.carlibrary.*;

/**
 * Servlet implementation class AddVehicle
 */
@WebServlet("/vehicle-added")
public class AddVehicle extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddVehicle() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		if(!request.getMethod().equals("POST")) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		String plate = request.getParameter("plate");
		if (plate == null || plate.equals("") || plate.length() > 16) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		plate = plate.toUpperCase();
		
		try {
			if(CheckPlate.checkPlate(plate)) {
				AddAttributes.addAttributes(request);
				request.setAttribute("msg", "Vehicle " + plate + " already exists!");
				request.getRequestDispatcher("/WEB-INF/listVehicles.jsp").forward(request, response);
				return;
			}
		}
		catch(SQLException e) {
			request.setAttribute("msg", "Database Error. Please try again later.");
			request.getRequestDispatcher("/WEB-INF/msg.jsp").forward(request, response);
		}
		
		plate = plate.toUpperCase();
		String type = request.getParameter("type").trim();
		String make = request.getParameter("make").trim();
		String model = request.getParameter("model").trim();
		int year = -1;
		int odometer = -1;
		try {
			year = Integer.parseInt(request.getParameter("year").trim());
			odometer = Integer.parseInt(request.getParameter("odometer").trim());
		}
		catch(Exception e) {
			
		}
				
		Object oilChange = request.getParameter("oilChange");
		Object tireRotation = request.getParameter("tireRotation");
		Object washerFluid = request.getParameter("washerFluid");
		
		Car car = null;
		
		if(type.equals("Electric")) {
			car = new ElectricCar(plate, make, model, year, odometer);
		}
		else if(type.equals("Gas")) {
			car = new GasCar(plate, make, model, year, odometer);
		}
		else {
			car = new DieselCar(plate, make, model, year, odometer);
		}
		
		if(oilChange != null) {
			try {
				car.addTask("Oil Change");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(tireRotation != null) {
			try {
				car.addTask("Tire Rotation");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if(washerFluid != null) {
			try {
				car.addTask("Top up Washer Fluid");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		Connection c = DBconnection.getConnection();
		PreparedStatement s = null;
		PreparedStatement t = null;
		ResultSet r = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream on = null;
		ByteArrayInputStream is = null;
		
		if(c != null) {
			try {
				on = new ObjectOutputStream(os);
				on.writeObject(car);
				byte[] b = os.toByteArray();
				is = new ByteArrayInputStream(b);
				s = c.prepareStatement("INSERT INTO cars (plate, car) VALUES (?,?)");
				s.setString(1, plate);
				s.setBlob(2, is);
				s.executeUpdate();
				
				t = c.prepareStatement("SELECT plate FROM cars");
				r = t.executeQuery();
				ArrayList<String> plates = new ArrayList<String>();
				
				while(r.next()) {
					plates.add(r.getString("plate"));
				}
				Collections.sort(plates);
				request.setAttribute("plates", plates);
				
				request.setAttribute("msg", "Vehicle " + plate + " added");
				request.getRequestDispatcher("/WEB-INF/listVehicles.jsp").forward(request, response);
			}
			catch(SQLException e) {
				request.setAttribute("msg", "Database Error. Please try again later.");
				request.getRequestDispatcher("/WEB-INF/msg.jsp").forward(request, response);
				return;
			}
			catch(IOException e) {
				
			}
			finally {
				try {
					r.close();
				}
				catch (Exception e) {
					
				}
				try {
					t.close();
				}
				catch (Exception e) {
					
				}
				try {
					is.close();
				}
				catch (Exception e) {
					
				}
				try {
					on.close();
				}
				catch (Exception e) {
					
				}
				try {
					os.close();
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
