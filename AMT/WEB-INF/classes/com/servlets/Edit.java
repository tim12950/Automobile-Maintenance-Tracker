package com.servlets;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
 * Servlet implementation class Edit
 */
@WebServlet("/vehicle-edited")
public class Edit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Edit() {
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
		if (plate == null || plate.equals("")) {
			response.sendRedirect(request.getContextPath() + "/home");
			return;
		}
		
		Connection c = DBconnection.getConnection();
		PreparedStatement s = null;
		PreparedStatement t = null;
		ResultSet r = null;
		InputStream is = null;
		ObjectInputStream in = null;
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		ObjectOutputStream on = null;
		ByteArrayInputStream ins = null;
		
		if(c != null) {
			
			String[] actions = {"toggle", "add", "remove"};
			String[] tasks = {"Oil Change", "Tire Rotation", "Top up Washer Fluid"};
			int n = actions.length;
			int m = tasks.length;
			
			try {
				s = c.prepareStatement("SELECT car FROM cars WHERE plate = ?");
				s.setString(1, plate);
				r = s.executeQuery();
				
				if(r.next()) {
					Blob carBlob = r.getBlob("car");
					is = carBlob.getBinaryStream();
					in = new ObjectInputStream(is);
					Car car = (Car) in.readObject();
				
					for(int i = 0; i < n; i++) {
						for(int j = 0; j < m; j++) {
							if(request.getParameter(actions[i] + tasks[j]) != null) {
								try {
									if(actions[i].equals("toggle")) {
										car.updateTask(tasks[j]);
									}
									else if(actions[i].equals("add")) {
										car.addTask(tasks[j]);
									}
									else {
										car.removeTask(tasks[j]);
									}
								} catch (Exception e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
						}
					}
					
					request.setAttribute("plate", plate);
					request.setAttribute("type", car.getType());
					request.setAttribute("make", car.getMake());
					request.setAttribute("model", car.getModel());
					request.setAttribute("year", car.getYear());
					request.setAttribute("odometer", car.getOdometer());
					request.setAttribute("scheduledTasks", car.getScheduledTasks());
					request.setAttribute("statuses", car.getStatuses());
					request.setAttribute("msg", "Vehicle tasks edited.");
					
					on = new ObjectOutputStream(os);
					on.writeObject(car);
					byte[] b = os.toByteArray();
					ins = new ByteArrayInputStream(b);
					
					t = c.prepareStatement("UPDATE cars SET car = ? WHERE plate = ?");
					t.setBlob(1, ins);
					t.setString(2, plate);
					t.executeUpdate();
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
					ins.close();
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
					t.close();
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
