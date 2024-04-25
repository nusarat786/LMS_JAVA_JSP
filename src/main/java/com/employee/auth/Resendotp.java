package com.employee.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Random;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.dao.EmployeeDao;

/**
 * Servlet implementation class Resendotp
 */
public class Resendotp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Resendotp() {
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
     
		try {
			
			String email = EmployeeDao.getCookieValue(request.getCookies(), "email");
			
			
			//geting employee by email id 
			Employee emp = EmployeeDao.getEmpByemail(email);
			
			//if emp is null exception will be thrown
			if(emp ==null) {
				throw new Exception("No Employ Found With Id");
			}
			
			
			// genrating otp
			int otp = generateOTP();
			
			// setting otp at specified employ record
			int rowAffected =  EmployeeDao.sendOtp(emp._id,otp);
			
			// sending otp via a mail
			boolean flag = Email.sendemail(emp.getEmail(),otp);
			
			
			
			if (rowAffected == 1 && flag) {
	            // setting a cookie to indicate that OTP has been sent
	            Cookie otpSentCookie = new Cookie("otpSent", "true");
	            otpSentCookie.setMaxAge(10 * 60); // 10 minutes in seconds
	            
	            // adding empId as a cookie
	            String emp_id = emp._id+" ";
	            Cookie empId = new Cookie("emp_id",emp_id.trim());
	            empId.setMaxAge(10*60);
	            
	            // setting email cookie
	            Cookie emCookie = new Cookie("email",email);
	            emCookie.setMaxAge(10*600000);
	            
	            // isAdmin
	            
	            
	            String isadmin = emp.getIsAdmin()==1 ? "true": "false" ;
	            
	            System.out.println("is admin..........." + isadmin + "-----" + emp.getIsAdmin() );
	            Cookie isAdmin = new Cookie("isAdmin",isadmin.trim());
	            emCookie.setMaxAge(10*600000);
	            emCookie.isHttpOnly();
	            
	            
	            // isAdmin
	            
	            
	            Cookie isresent = new Cookie("isresent",isadmin.trim());
	            isresent.setMaxAge(10*60000);
	            isresent.isHttpOnly();
	            
	            
	            
	            // cookies added to response
	            response.addCookie(otpSentCookie);
	            response.addCookie(empId);
	            response.addCookie(emCookie);
	            response.addCookie(isresent);
	            
	            
	            //response.addCookie(new Cookie("email",email));
	            
	            
	            
	            
	            response.sendRedirect("/LMS_V3/verify_otp.jsp");

	            
	        }
			
			
			
		}catch(SQLIntegrityConstraintViolationException ee) {
			EmployeeDao.errorPage(ee,request,response,"Email / Mobile Alredy Taken");
		}catch(SQLException ee) {
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
	
	private int generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        return otp;
    }

}
