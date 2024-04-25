package com.admin.userManagement.dao;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.admin.userManagement.bean.HoidayBean;
import com.connection.Connection_;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HolidayDao {

	private  static String GET_ALL_HOLIDAY_SQL = "SELECT * FROM Holiday";
	private  static String GET_HOLIDAY_BY_DATE_SQL = "SELECT * FROM Holiday where holiday_date = ?";
	
	private static Connection getConnection() throws SQLException {
        return Connection_.getc();
    }
	public static List<HoidayBean> getHolidays() throws ServletException, IOException,SQLException,Exception {
        	
			List<HoidayBean> holidays = new ArrayList<>();
			try(Connection connection = getConnection()){
        	PreparedStatement ps = connection.prepareStatement(GET_ALL_HOLIDAY_SQL);
            
        	
        	ResultSet rs = ps.executeQuery();
            
        	while (rs.next()) {
        		HoidayBean holiday = new HoidayBean();
                holiday.setHolidayName(rs.getString("holiday_name"));
                holiday.setHolidayDate(rs.getDate("holiday_date").toString());
                holiday.setHolidayDescription(rs.getString("holiday_description"));
                holiday.setCreatedAt(rs.getTimestamp("created_at").toLocaleString());
                holidays.add(holiday);
            }
        	
        	return holidays; 
			}
        }
	
	
	public static HoidayBean getHolidayByDate(String Date) throws ServletException, IOException,SQLException,Exception {
    	
		HoidayBean holiday = new HoidayBean();
		try(Connection connection = getConnection()){
    	PreparedStatement ps = connection.prepareStatement(GET_HOLIDAY_BY_DATE_SQL);
        
    	ps.setDate(1, java.sql.Date.valueOf(Date));
    	
    	ResultSet rs = ps.executeQuery();
        
    	while (rs.next()) {
            holiday.setHolidayName(rs.getString("holiday_name"));
            holiday.setHolidayDate(rs.getDate("holiday_date").toString());
            holiday.setHolidayDescription(rs.getString("holiday_description"));
            holiday.setCreatedAt(rs.getTimestamp("created_at").toLocaleString());
        }
    	
    	return holiday;   
		}
    }
	
   }

