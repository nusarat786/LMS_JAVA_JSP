package com.admin.userManagement.web;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.Blob;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.dao.EmployeeDao;

/**
 * Servlet implementation class EditEmployServlet
 */
public class EditEmployServlet2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EditEmployServlet2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
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
			
			int id = Integer.parseInt(request.getParameter("emp_id"));
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
			
	        
	        // Get the value of the 'updatePhoto' parameter (checkbox)
	        String updatePhotoValue = request.getParameter("updatePhoto");

	        Employee emp = null;
	        // Check if the checkbox was checked
	        if ("on".equals(updatePhotoValue)) {
	        	System.out.println("Servlet 2 this is");
		        emp = new Employee(id,firstName,lastName,email,position,department,dateOfBirth,gender,phoneNumber,address,status,22,photoInputStream);
	        } else {
	        	
		        Blob photoBlob = EmployeeDao.getEmpById(id).employeePhotoo;
		        System.out.println("name: " + EmployeeDao.getEmpById(id).getFirstName());
		        System.out.println("photo: " + photoBlob);
	        	
		        emp = new Employee(id,firstName,lastName,email,position,department,dateOfBirth,gender,phoneNumber,address,status,22,photoBlob);
	        }
	        
	        
	        // grant admin acsess
	        String grantAdmin = request.getParameter("grantAdmin");
	        
	        
	        // Check if the checkbox was checked
	        if ("on".equals(grantAdmin)) {
	        	emp.setIsAdmin(1);
	        } else {	  
	        	emp.setIsAdmin(0);
	        }
	        
	        
	        int row_count = EmployeeDao.UpdateEmploy3(emp);
	        
	 
	        
	        if(row_count==1) {
	        	out.println(EmployeeDao.sendAlert("Employee With ID: " + id + " Edited","/LMS_V3/EmployDeatils2.jsp"));
	        	//out.println(EmployeeDao.givAlertBox("Employepe Added","/LMS_V3/add_employ.html"));
	        }	
	        
	        System.out.println(row_count);
		}catch(SQLIntegrityConstraintViolationException ee) {
			EmployeeDao.errorPage(ee,request,response,"Email / Mobile Alredy Taken");
		}catch(SQLException ee) {
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
	}

}
