package com.admin.userManagement.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import oracle.sql.DATE;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Map;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.bean.LeaveRequestBean;
import com.admin.userManagement.dao.EmployeeDao;
import com.admin.userManagement.dao.HolidayDao;
import com.admin.userManagement.dao.LeaveRequestDao;

/**
 * Servlet implementation class ApplyLeave
 */
public class ApplyLeave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApplyLeave() {
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
		try {
		

	        
	        String email = EmployeeDao.getCookieValue(request.getCookies(), "email");
	        
	        emp =EmployeeDao.getEmpByemail(email);
	        
	        
	        if(emp == null) {
	        	throw new Exception("Emp Can Not Be Found");
	        }
	        
	        int emp_id = emp._id;
	        String leaveType = request.getParameter("leaveType");
	        String startDate = request.getParameter("startDate");
	        String endDate = request.getParameter("endDate");
	        
	        System.out.println(startDate);
	        // Get current date and time
	        Date currentDateTime = new Date();
	        // Format current date and time as string in 'yyyy-MM-dd HH:mm:ss' format
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	        String applyDate = dateFormat.format(currentDateTime);

	        
	        String reason = request.getParameter("reason");
	        String status = "PENDING";
	        
	        
	        LeaveRequestBean lreq = new LeaveRequestBean(emp_id,leaveType,startDate,endDate,applyDate,reason,status);
			
	        
	        Map<String, Long> hm  = LeaveRequestDao.inserLeaveRequest2(lreq,emp.getLeaveBalance(),email);
	        
			 
	        long row_count = hm.get("rowsAffected");
	        long noOfDays = hm.get("duration");
			long removed = hm.get("excludedCount");
			 
			 
	        System.out.println("-----------------------------------------------");
	        System.out.println(LeaveRequestDao.getLeaveHistoryByEmployeeId(emp._id));
	        
	        if(row_count==1) {
	        	out.println(EmployeeDao.sendAlert("Alpplied For Leave Total Days("+ noOfDays +") " + " Exluding HoliDays ( " + removed + ")","/LMS_V3/EmpLeaveRequest.jsp"));
	        	//out.println(EmployeeDao.givAlertBox("Employepe Added","/LMS_V3/add_employ.html"));
	        }	
	        
	        System.out.println(row_count);
		
		}catch(SQLIntegrityConstraintViolationException ee) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EmployeeDao.errorPage(ee,request,response,"Invalid Dates");
		}catch(SQLException ee) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (ee.toString().contains("SYS.CHECK_DATES_APPLY_DATE_YEAR")) {
		        // Handle the specific error related to the check constraint violation
				EmployeeDao.errorPage(ee,request,response,"Start date or end date does not have the same year as the current year ");
				return;
		    }
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
		
		
	}

}
