package com.admin.userManagement.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.dao.EmployeeDao;
import com.admin.userManagement.dao.LeaveRequestDao;

/**
 * Servlet implementation class ResetLeaveRequestById
 */
public class ResetLeaveRequestById extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ResetLeaveRequestById() {
        super();
        // TODO Auto-generated constructor stub
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
        Employee emp = null ;
        int bal = 0;
        int id = 0;
        try {
			
			bal = Integer.parseInt(request.getParameter("balance"));
			System.out.println("Balance.... " + bal);
			
			if(bal<5 || bal>50) {
				throw new Exception("Invalid Leave Balance, Should Be in (5,45)");
			}
												
			id = Integer.parseInt(request.getParameter("employeeId"));
			
			emp = EmployeeDao.getEmpById(id);
				      
			if(emp==null) {
				new Exception("No Emp Found With Id: " +id );
			}
	        
	        int row_count = LeaveRequestDao.ResetLeaveBalance(emp,bal);
	        
	         
			if(row_count>0) {
				out.println(EmployeeDao.sendAlert("Employee (" + emp._id +") Leave Balace Reset ("+ bal +")","/LMS_V3/EmployDeatils2.jsp"));
		        	
			}else {
				out.println(EmployeeDao.sendAlert("Please Try Again....... ","/LMS_V3/ResetLeave_By_Id.jsp"));	        
			}
	        
	        	//out.println(EmployeeDao.givAlertBox("Employepe Added","/LMS_V3/add_employ.html"));
	        
	        
	        System.out.println(row_count);
		
		}catch(SQLIntegrityConstraintViolationException ee) {
			EmployeeDao.errorPage(ee,request,response,"Invalid Dates");
		}catch(SQLException ee) {
			if (ee.toString().contains("SYS.CHECK_DATES_APPLY_DATE_YEAR")) {
		        // Handle the specific error related to the check constraint violation
				EmployeeDao.errorPage(ee,request,response,"Start date or end date does not have the same year as the current year ");
				return;
		    }
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
	
	}

}
