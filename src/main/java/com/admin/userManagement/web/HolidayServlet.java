package com.admin.userManagement.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;

import com.admin.userManagement.bean.HoidayBean;
import com.admin.userManagement.dao.EmployeeDao;
import com.admin.userManagement.dao.HolidayDao;
import com.connection.Connection_;


public class HolidayServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "create":
                    createHoliday(request, response);
                    break;
                case "read":
                    readHolidays(request, response);
                    break;
                case "update":
                    updateHoliday(request, response);
                    break;
                case "delete":
                    deleteHoliday(request, response);
                    break;
                default:
                    response.getWriter().println("Invalid action");
            }
        } else {
            response.getWriter().println("No action specified");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    private Connection getConnection() throws SQLException {
        return Connection_.getc();
    }

    private void createHoliday(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out =response.getWriter();
    	
    	try (Connection connection = getConnection()) {
        	
            String holidayName = request.getParameter("holiday_name");
            String holidayDate = request.getParameter("holiday_date");
            String holidayDescription = request.getParameter("holiday_description");
            
            String sql = "INSERT INTO Holiday (holiday_name, holiday_date, holiday_description) VALUES (?, ?, ?)";
            PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, holidayName);
                pstmt.setDate(2,Date.valueOf(holidayDate));
                
                System.out.println(Date.valueOf(holidayDate));
                pstmt.setString(3, holidayDescription);
                int rowsAffected = pstmt.executeUpdate();
                
                //System.out.println(HolidayDao.getHolidays());
                if (rowsAffected > 0) {
                	 out.println(EmployeeDao.sendAlert("Holiday created successfully", "/LMS_V3/HolidayJsp/holidays.jsp"));
                } else {
                	out.println(EmployeeDao.sendAlert("Holiday can not be creted please try again", "/LMS_V3/create_holidays.jsp")); 
                }
            
        }catch(SQLIntegrityConstraintViolationException ee) {
			EmployeeDao.errorPage(ee,request,response,"Holiday With Date Is Alredy Exist");
		}catch(SQLException ee) {
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
    }

    private void readHolidays(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try (Connection connection = getConnection()) {
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Holiday");

            PrintWriter out = response.getWriter();
            out.println("<html><body>");
            out.println("<h2>Holidays:</h2>");
            out.println("<ul>");
            while (rs.next()) {
                out.println("<li>" + rs.getString("holiday_name") + " - " + rs.getString("holiday_date") + "</li>");
            }
            out.println("</ul>");
            out.println("</body></html>");
        } catch (SQLException e) {
            e.printStackTrace();
            response.getWriter().println("An error occurred: " + e.getMessage());
        }
    }

    private void updateHoliday(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out =response.getWriter();
    	
    	try (Connection connection = getConnection()) {
            String holidayName = request.getParameter("holiday_name");
            String holidayDate = request.getParameter("holiday_date");
            String holidayDescription = request.getParameter("holiday_description");
            String holiday_date_to_up = request.getParameter("holiday_date_to_up");
            String sql = "UPDATE Holiday SET holiday_name=?, holiday_date=?, holiday_description=? WHERE holiday_date=?";
            PreparedStatement pstmt = connection.prepareStatement(sql);
                pstmt.setString(1, holidayName);
                pstmt.setDate(2, java.sql.Date.valueOf(holidayDate));
                pstmt.setString(3, holidayDescription);
                pstmt.setDate(4, java.sql.Date.valueOf(holiday_date_to_up));

                int rowsAffected = pstmt.executeUpdate();
                
              //System.out.println(HolidayDao.getHolidays());
                if (rowsAffected > 0) {
                	 out.println(EmployeeDao.sendAlert("Holiday update successfully", "/LMS_V3/HolidayJsp/holidays.jsp"));
                } else {
                	out.println(EmployeeDao.sendAlert("Holiday can not be update please try again", "/LMS_V3/HolidayJsp/edit_holiday.jsp")); 
                }
            
        }catch(SQLIntegrityConstraintViolationException ee) {
			EmployeeDao.errorPage(ee,request,response,"Holiday With Date Is Alredy Exist");
		}catch(SQLException ee) {
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
    }

    private void deleteHoliday(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	PrintWriter out =response.getWriter();
    	try (Connection connection = getConnection()) {
            String holiday_date_to_up = request.getParameter("holiday_date_to_up");

            String sql = "DELETE FROM Holiday WHERE holiday_date=?";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setDate(1, java.sql.Date.valueOf(holiday_date_to_up));

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
               	 out.println(EmployeeDao.sendAlert("Holiday Deleted", "/LMS_V3/HolidayJsp/holidays.jsp"));
                } else {
               	out.println(EmployeeDao.sendAlert("Holiday Could Not Be Deleted ", "/LMS_V3/HolidayJsp/holidays.jsp")); 
                }
            }
        } catch(SQLIntegrityConstraintViolationException ee) {
			EmployeeDao.errorPage(ee,request,response,"Holiday With Date Is Alredy Exist");
		}catch(SQLException ee) {
			EmployeeDao.errorPage(ee,request,response,"Database Error");
		}catch(Exception ee) {
			EmployeeDao.errorPage(ee,request,response,"Server Error");
		}
    }

}
