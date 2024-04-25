package com.employee.auth;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.dao.EmployeeDao;

import javax.mail.internet.MimeMessage;
import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  


/**
 * Servlet implementation class GetOtp
 */
public class GetOtp extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetOtp() {
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
        int empId = 0;
        String email = null;
		try {
			
			Cookie[] cookies = request.getCookies();
			
			if (cookies != null) {
			    for (Cookie cookie : cookies) {
			    	System.out.println(cookie.getName() + " " + cookie.getValue() );
			        
			        if (cookie.getName().equals("emp_id")) {
			        	empId = Integer.parseInt(cookie.getValue());
			  
			        }  
			        
			        if (cookie.getName().equals("email")) {
			        	email = cookie.getValue();
			        }  
			         
			    }
			}
			
			int otp = Integer.parseInt(request.getParameter("otp"));
			System.out.println("Test......... " + empId );
			Employee emp = EmployeeDao.getEmpByemail(email);
			
			if(emp ==null) {
				throw new Exception("No Employ Found With Id");
			}
			
			System.out.println(emp._otp);
			System.out.println(otp);
			
			System.out.println("Is OK");
			System.out.println(emp._otp==otp);
			
			
			
			if(emp._otp==otp) {
				
				cookies = request.getCookies();
				
				if (cookies != null) {
				    for (Cookie cookie : cookies) {
				    	System.out.println(cookie.getName());
				        
				    	if (cookie.getName().equals("otpSent")) {
				            cookie.setMaxAge(0);
				            response.addCookie(cookie);
				        }
				        
				        if (cookie.getName().equals("emp_id")) {
				        	cookie.setMaxAge(0);
				        	response.addCookie(cookie);
				        }  
				    }
				}
				
				Cookie authCookie = new Cookie("auth", "true");
				authCookie.setMaxAge(10 * 600000);// 10 minutes in seconds
				authCookie.setHttpOnly(true); // Set the cookie as read-only
	            response.addCookie(authCookie);
	            
	            
	            String emp_id = emp._id+" ";
	            Cookie empIdCookie = new Cookie("empID",emp_id.trim());
	            empIdCookie.setMaxAge(10 * 600000);// 10 minutes in seconds
	            response.addCookie(empIdCookie);
	            
	            System.out.println("Verified");
				
	            out.println(EmployeeDao.sendAlert("Authenticated", "/LMS_V3/EmployPortal.jsp"));
				
			}else {
				
				Cookie authCookie = new Cookie("auth", "false");
				authCookie.setMaxAge(10 * 600);// 10 minutes in seconds
				authCookie.setHttpOnly(true); // Set the cookie as read-only
	            response.addCookie(authCookie);
	            
	            Thread.sleep(2000);
				out.println(EmployeeDao.sendAlert("Wrong Otp !! Otp Valis Only For 10 Minutes", "/LMS_V3/verify_otp.jsp"));
			}
			
			
			System.out.println("Otp Sent");
		}catch(SQLIntegrityConstraintViolationException ee) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ee.printStackTrace();
			EmployeeDao.errorPage(ee,request,response,"Email / Mobile Alredy Taken");
		}catch(SQLException ee) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ee.printStackTrace();
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ee.printStackTrace();
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
		
		
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// GIVE OUTPUT
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
     
		try {
			
			String cemail = EmployeeDao.getCookieValue(request.getCookies(), "email");
			
			
			
			
			// geting email from login page
			String email =  request.getParameter("email").trim();
			
			//geting employee by email id 
			Employee emp = EmployeeDao.getEmpByemail(email);
			
			//if emp is null exception will be thrown
			if(emp ==null) {
				throw new Exception("No Employ Found With Id");
			}
			
			//System.out.println("emp " + emp._id);
			
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
	            
	            
	            
	            // cookies added to response
	            response.addCookie(otpSentCookie);
	            response.addCookie(empId);
	            response.addCookie(emCookie);
	            response.addCookie(isAdmin);
	            response.addCookie(new Cookie("email",email));
	            
	            
	            
	            
	            // Forward the request to the verify JSP
	            RequestDispatcher dispatcher = request.getRequestDispatcher("/verify_otp.jsp?email");
	            dispatcher.forward(request, response);
	            
	            
	        }
			
			
			
		}catch(SQLIntegrityConstraintViolationException ee) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EmployeeDao.errorPage(ee,request,response,"Email / Mobile Alredy Taken");
		}catch(SQLException ee) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
		
	}
	
	
	private int generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // Generate a 6-digit OTP
        return otp;
    }
	
	
}
