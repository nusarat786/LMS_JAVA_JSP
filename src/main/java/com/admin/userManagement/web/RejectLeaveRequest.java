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
 * Servlet implementation class RejectLeaveRequest
 */
public class RejectLeaveRequest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RejectLeaveRequest() {
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
        
        Employee emp = null ;
        LeaveRequestBean lreq = null;
		try {
			
			lreq = LeaveRequestDao.getLeaveByID(request.getParameter("leave_id"));
			System.out.println("Leave" + lreq);
			
			emp =EmployeeDao.getEmpById(Integer.parseInt(request.getParameter("emp_id")));
			System.out.println("Emp" + emp);
		

			
	        if(emp == null || lreq==null ) {
	        	throw new Exception("Leave Id or Employ Id Error");
	        }
	        
	        int empID =Integer.parseInt(EmployeeDao.getCookieValue(request.getCookies(), "empID"));
	       
	        int row_count = LeaveRequestDao.RejectLeaveRequest(lreq,emp,empID);
	        
	         
			
	        if(row_count==1) {
	        	out.println(EmployeeDao.sendAlert("Leave Request Rejected !! ","AcceptRequest.jsp?emp_id="+emp._id+"&leave_id="+lreq.getLeaveId()));
	        	//out.println(EmployeeDao.givAlertBox("Employepe Added","/LMS_V3/add_employ.html"));
	        }	
	        
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
