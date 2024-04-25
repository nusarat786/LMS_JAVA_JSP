package com.admin.userManagement.web;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import jakarta.servlet.RequestDispatcher;

import jakarta.servlet.http.Part;
import com.admin.userManagement.bean.*;
import com.admin.userManagement.dao.EmployeeDao;
/**
 * Servlet implementation class EmployeeServlet
 */

public class EmployeeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	public void init() throws ServletException {
		
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GIVE OUTPUT
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
     
		try {
			
			
			
	        String firstName = request.getParameter("firstName");
	        String lastName = request.getParameter("lastName");
	        String email = request.getParameter("email");
	        String position = request.getParameter("position");
	        String department = request.getParameter("department");
	        String dateOfBirth = request.getParameter("dateOfBirth");
	        String gender = request.getParameter("gender");
	        String phoneNumber = request.getParameter("phoneNumber");
	        String address = request.getParameter("address");
	        String status = request.getParameter("status");

	        System.out.println("Status Is :" +status);
	        // Retrieve employee photo
	        Part filePart = request.getPart("employeePhoto");
	        InputStream photoInputStream = filePart.getInputStream();
			
	        Employee emp = new Employee(firstName,lastName,email,position,department,dateOfBirth,gender,phoneNumber,address,status,22,photoInputStream);
			
	        System.out.println(EmployeeDao.selectAllUsers());
	        
	        int row_count = EmployeeDao.insertEmployeev2(emp);
	        
	        
	        
	        if(row_count==1) {
	        	out.println(EmployeeDao.sendAlert("Employee Added","/LMS_V3/EmployDeatils2.jsp"));
	        	//out.println(EmployeeDao.givAlertBox("Employepe Added","/LMS_V3/add_employ.html"));
	        }	
	        
	        System.out.println(row_count);
		
		}catch(SQLIntegrityConstraintViolationException ee) {
			errorPage(ee,request,response,"Email / Mobile Alredy Taken");
		}catch(SQLException ee) {
			errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			errorPage(ee,request,response,"Server Error");
		}
	}
	
	
	public static String sendAlert(String message, String redirectURL) {
        return "<script>alert('" + message + "'); window.location.href = '" + redirectURL + "';</script>";
    }
	
	public void errorPage(Exception ee,HttpServletRequest request, HttpServletResponse response,String err) {
		ee.printStackTrace();
    	// Redirect to an error page with a specific message
        String errorMessage = "An error occurred: " + ee.getMessage();
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("err", err);

        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
        try {
        	
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ee1) {
				// TODO Auto-generated catch block
				ee1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException ee1) {
				// TODO Auto-generated catch block
				ee1.printStackTrace();
			}
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
	}
}
