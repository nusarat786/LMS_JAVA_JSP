package com.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.dao.EmployeeDao;
import com.admin.userManagement.dao.EmployeeDao.*;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFilterOTP2 implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
        var email_pm = request.getParameter("email");
        
        // used to send alert response
        PrintWriter out = response.getWriter();
        
      
        // getting all the cookies
    	Cookie[] cookies = httpRequest.getCookies();
    	
    	// getting details from cookies
    	
    	//emp object
    	Employee emp = null;
    	
    	// emp id
    	int emp_id = 0;
    	
    	// emp email
    	String email = null;
    	
    	try{
    		
    		// flag to check otp sent or not from cookies
    		boolean otpSent = false;
    		
    		// flag to check wetaher user is alredy login or not
    		boolean auth = false;
    		
    		
    		if (cookies != null) {
    		    for (Cookie cookie : cookies) {
    		    	System.out.println(cookie.getName());
    		            		    
    		        if (cookie.getName().equals("auth")) {
    		        	auth = Boolean.parseBoolean(cookie.getValue());
    		        	break;
    		        } 
    		            		        
    		    }
    		}
    		
    		if(auth) {
    			throw new AlredyLoginException();
    		}
    		
    		
    		request.setAttribute("email", email_pm);

    		
    	
    	
     	}catch(AlredyLoginException ee) {
    		ee.printStackTrace();
    		out.println(EmployeeDao.sendAlert("Alredy Logined!! Taking To Employee Portal", "/LMS_V3/EmployPortal.jsp"));
    	
     	}catch(Exception ee) {
    		
    		System.out.println();
    		ee.printStackTrace();
    		EmployeeDao.errorPage(ee,httpRequest,httpResponse,"Server Error");
    	
    	}finally {
	        // Continue with the filter chain
	        chain.doFilter(request, response);
    	}
    }

    @Override
    public void destroy() {
        // Cleanup code if needed
    }
    
    
}







