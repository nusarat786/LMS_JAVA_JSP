package com.admin.userManagement.web;

import jakarta.servlet.RequestDispatcher;
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
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.dao.EmployeeDao;

/**
 * Servlet implementation class GetAllEmpoyee
 */
public class GetAllEmpoyee extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GetAllEmpoyee() {
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
		// GIVE OUTPUT
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
     
		try {
			
			System.out.println(EmployeeDao.getEmpById(1)._id);
	        var ListEmp = EmployeeDao.selectAllUsers();
	        request.setAttribute("employeeList", ListEmp);
	        RequestDispatcher dispatcher = request.getRequestDispatcher("/EmployDeatils.jsp");
	        dispatcher.forward(request, response);
	        
		}catch(SQLIntegrityConstraintViolationException ee) {
			EmployeeDao.errorPage(ee,request,response,"Email / Mobile Alredy Taken");
		}catch(SQLException ee) {
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
	
	}


}
