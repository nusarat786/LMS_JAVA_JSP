package com.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.*;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.dao.EmployeeDao;
import com.admin.userManagement.dao.EmployeeDao.*;

import java.io.IOException;
import java.io.PrintWriter;

public class AuthenticationFilterAdmin implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization code if needed
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        
     // GIVE OUTPUT
        PrintWriter out = response.getWriter();
        
      //Employee 
    	//emp = null;
    	
    	Cookie[] cookies = httpRequest.getCookies();
    	Employee emp = null;
    	int emp_id = 0;
    	String email = null;
    	Boolean isAdmin = false;
    	try{
    		
    		boolean isAuth = false;

    		if (cookies != null) {
    		    for (Cookie cookie : cookies) {
    		    	System.out.println(cookie.getName());
    		        
    		    	if (cookie.getName().equals("auth")) {
    		    		isAuth = Boolean.parseBoolean(cookie.getValue());
    		        }
    		        
    		        if (cookie.getName().equals("empID")) {
    		        	emp_id = Integer.parseInt(cookie.getValue());
    		        } 
    		        
    		        if (cookie.getName().equals("email")) {
    		        	email = cookie.getValue();
    		        }
    		        
    		        if (cookie.getName().equals("isAdmin")) {
    		        	isAdmin = Boolean.parseBoolean(cookie.getValue());
    		        }
    		        
    		        
    		    }
    		}

    		System.out.println("Test.........." + isAuth + "   " + isAdmin);
    		if (!isAuth || !isAdmin) {
    			throw new AdminAuthenticationFailure();	
    		}
    		
    		System.out.println("EMP ID " + emp_id);
    		System.out.println("EMail ID " + email);
    		emp = EmployeeDao.getEmpByemail(email.trim());
    		HttpSession session = httpRequest.getSession(); // Get the session associated with the request
    	    session.setAttribute("employee", emp); // Store the emp object as a session attribute with the name "employee"

    		httpRequest.setAttribute("employee", emp);
    		
     	}catch(AdminAuthenticationFailure ee) {
    		
     		System.out.println();
    		ee.printStackTrace();
    		out.println(EmployeeDao.sendAlert("Auth Failed or Not a Admin", "/LMS_V3/GetOtp.jsp"));
    	
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


class AdminAuthenticationFailure extends Exception{
	
	@Override
	public String toString() {
		return "not a admin user";
	}
}