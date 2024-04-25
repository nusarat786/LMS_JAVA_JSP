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
import com.admin.userManagement.bean.LeaveRequestBean;
import com.admin.userManagement.dao.EmployeeDao;
import com.admin.userManagement.dao.LeaveRequestDao;

/**
 * Servlet implementation class LeaveReset
 */
public class LeaveReset extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LeaveReset() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GIVE OUTPUT
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        
        int bal = 0 ;
        
        try {
			
			bal = Integer.parseInt(request.getParameter("balance"));
			System.out.println("Balance.... " + bal);
			
			if(bal<5 || bal>50) {
				throw new Exception("Invalid Leave Balance, Should Be in (5,45)");
			}
				       
	        
	        int row_count = LeaveRequestDao.ResetLeaveBalance(bal);
	        
	         
			
	        
	        out.println(EmployeeDao.sendAlert("All Employee Leave Balace Reset ("+ bal +")","/LMS_V3/EmployDeatils2.jsp"));
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

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
