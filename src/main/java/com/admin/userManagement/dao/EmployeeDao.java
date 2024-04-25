package com.admin.userManagement.dao;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Part;

import com.admin.userManagement.bean.*;
import com.connection.*;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


public class EmployeeDao {

	 private static final String INSERT_EMPLOYEE_SQL = "INSERT INTO Employee (FirstName, LastName, Email, Position, Department, DateOfBirth, Gender, PhoneNumber, Address, Status, EmployeePhoto,LeaveBalance) " +
            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)";

	 private static final String SELECT_ALL_EMPLOY  = "SELECT * from Employee";
	 
	 private static final String GET_EMPLOY_ID = "SELECT * from Employee WHERE employeeID = ?";

	 private static final String GET_EMPLOY_EMAIL = "SELECT * from Employee WHERE Email = ?";

	 
	 private static final String UPDATE_EMPLOY = "UPDATE Employee SET FirstName = ?, LastName = ?, Email = ?, Position = ?, Department = ?, "
             + "DateOfBirth = ?, Gender = ?, PhoneNumber = ?, Address = ?, Status = ? WHERE employeeID = ?";
	 
	 private static final String UPDATE_EMPLOY2 = "UPDATE Employee SET FirstName = ?, LastName = ?, Email = ?, Position = ?, Department = ?, "
             + "DateOfBirth = ?, Gender = ?, PhoneNumber = ?, Address = ?, Status = ?,EMPLOYEEPHOTO=? WHERE employeeID = ?";

	 private static final String UPDATE_EMPLOY3 = "UPDATE Employee SET FirstName = ?, LastName = ?, Email = ?, Position = ?, Department = ?, "
             + "DateOfBirth = ?, Gender = ?, PhoneNumber = ?, Address = ?, Status = ?,EMPLOYEEPHOTO=? , ISADMIN=? WHERE employeeID = ?";

	 
	 private static final String ADD_OTP = "update Employee set OTP=? WHERE employeeID = ?" ;
	 
	 
	 private static final String EMP_LEAVE_JOIN_SQL = "SELECT e.*,l.* FROM employee e JOIN leave_history l ON e.employeeid = l.employee_id";
	 
	 // insert employ
	 
	 public static String insertEmployee(Employee emp,PrintWriter out ) throws Exception  {
		 int rowsAffected = 0;
		 String Error = " ";
		 Connection_ con =  new Connection_();
		 Connection connection = con.getc();
		 
		 try {
		 
		 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
		 
		 
		 preparedStatement.setString(1, emp.getFirstName());
         preparedStatement.setString(2, emp.getLastName());
         preparedStatement.setString(3, emp.getEmail());
         preparedStatement.setString(4, emp.getPosition());
         preparedStatement.setString(5, emp.getDepartment());
         
         java.sql.Date dateOfBirth = java.sql.Date.valueOf(emp.getDateOfBirth());
         preparedStatement.setDate(6,dateOfBirth);
         preparedStatement.setString(7, emp.getGender());
         preparedStatement.setString(8, emp.getPhoneNumber());
         preparedStatement.setString(9, emp.getAddress());
         preparedStatement.setString(10, emp.getStatus());
         
         preparedStatement.setBlob(11, emp.getEmployeePhoto());
             
         preparedStatement.setDouble(12, emp.getLeaveBalance());
         //java.sql.SQLException: ORA-17003: Invalid column index
         rowsAffected = preparedStatement.executeUpdate();
         
         
		 
		 }catch(Exception e){
			 Error = e.toString();
			 e.printStackTrace();
			 
		 }finally {
			 connection.close();
			 return rowsAffected+" ";
		 
		 }
	 }
	 
	 // insert an employee  
	 public static int insertEmployeev2(Employee emp) throws SQLIntegrityConstraintViolationException,SQLException  {
		 int rowsAffected = 0;
		 String Error = " ";
		
		 Connection_ con =  new Connection_();
		 try(Connection connection = con.getc()){
		 PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EMPLOYEE_SQL);
		 
		 preparedStatement.setString(1, emp.getFirstName());
         preparedStatement.setString(2, emp.getLastName());
         preparedStatement.setString(3, emp.getEmail());
         preparedStatement.setString(4, emp.getPosition());
         preparedStatement.setString(5, emp.getDepartment());
         
         java.sql.Date dateOfBirth = java.sql.Date.valueOf(emp.getDateOfBirth());
         preparedStatement.setDate(6,dateOfBirth);
         preparedStatement.setString(7, emp.getGender());
         preparedStatement.setString(8, emp.getPhoneNumber());
         preparedStatement.setString(9, emp.getAddress());
         preparedStatement.setString(10, emp.getStatus());
         
         preparedStatement.setBlob(11, emp.getEmployeePhoto());
             
         preparedStatement.setDouble(12, emp.getLeaveBalance());
         //java.sql.SQLException: ORA-17003: Invalid column index
         rowsAffected = preparedStatement.executeUpdate();
         
         
		 
		return rowsAffected;
		 }
	 }
	 
	 
	 
	 
	 
	 
	 // get empoy
	 
	 public static Employee getEmpById(int id) throws SQLException{

			// using try-with-resources to avoid closing resources (boiler plate code)
			Employee employee =null;
			// Step 1: Establishing a Connection
			Connection_ con =  new Connection_();
			try(Connection connection = con.getc()){

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOY_ID);
			System.out.println(preparedStatement);
			
			//
			preparedStatement.setInt(1, id);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				id = rs.getInt("EMPLOYEEID");
				int otp = rs.getInt("OTP");
				String firstName = rs.getString("firstName");
		        String lastName = rs.getString("lastName");
		        String email = rs.getString("email");
		        String position = rs.getString("position");
		        String department = rs.getString("department");
		        String dateOfBirth = rs.getString("dateOfBirth");
		        String gender = rs.getString("gender");
		        String phoneNumber = rs.getString("phoneNumber");
		        String address = rs.getString("address");
		        String status = rs.getString("status");
		        Blob photo = rs.getBlob("employeePhoto");
		        double leaveBalance = rs.getInt("leaveBalance");
		        
				employee = new Employee(id,otp,firstName,lastName,email,position,department,dateOfBirth,gender,phoneNumber,address,status,leaveBalance,photo);
				employee.setIsAdmin(rs.getInt("ISADMIN"));
			}
			
			
			 
			return employee;
			}
		}
	 
	 
	 public static Employee getEmpById(Connection connection,int id) throws SQLException{

			// using try-with-resources to avoid closing resources (boiler plate code)
			Employee employee =null;
			
			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOY_ID);
			System.out.println(preparedStatement);
			
			//
			preparedStatement.setInt(1, id);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				id = rs.getInt("EMPLOYEEID");
				int otp = rs.getInt("OTP");
				String firstName = rs.getString("firstName");
		        String lastName = rs.getString("lastName");
		        String email = rs.getString("email");
		        String position = rs.getString("position");
		        String department = rs.getString("department");
		        String dateOfBirth = rs.getString("dateOfBirth");
		        String gender = rs.getString("gender");
		        String phoneNumber = rs.getString("phoneNumber");
		        String address = rs.getString("address");
		        String status = rs.getString("status");
		        Blob photo = rs.getBlob("employeePhoto");
		        double leaveBalance = rs.getInt("leaveBalance");
				employee = new Employee(id,otp,firstName,lastName,email,position,department,dateOfBirth,gender,phoneNumber,address,status,leaveBalance,photo);
			}
			
			
			 
			return employee;
			
		}
	 
	 
	 // get by email
	 
	
	 
		 public static Employee getEmpByemail(String email) throws SQLException{

				// using try-with-resources to avoid closing resources (boiler plate code)
				Employee employee =null;
				// Step 1: Establishing a Connection
				Connection_ con =  new Connection_();
				Connection connection = con.getc();

				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = connection.prepareStatement(GET_EMPLOY_EMAIL);
				System.out.println(preparedStatement);
				
				//
				preparedStatement.setString(1, email);
				
				// Step 3: Execute the query or update query
				ResultSet rs = preparedStatement.executeQuery();

				// Step 4: Process the ResultSet object.
				while (rs.next()) {
					int id = rs.getInt("EMPLOYEEID");
					int otp = rs.getInt("OTP");
					String firstName = rs.getString("firstName");
			        String lastName = rs.getString("lastName");
			        email = rs.getString("email");
			        String position = rs.getString("position");
			        String department = rs.getString("department");
			        String dateOfBirth = rs.getString("dateOfBirth");
			        String gender = rs.getString("gender");
			        String phoneNumber = rs.getString("phoneNumber");
			        String address = rs.getString("address");
			        String status = rs.getString("status");
			        Blob photo = rs.getBlob("employeePhoto");
			        double leaveBalance = rs.getInt("leaveBalance");
			        int isAdmin = rs.getInt("ISADMIN"); 
			        employee = new Employee(id,otp,firstName,lastName,email,position,department,dateOfBirth,gender,phoneNumber,address,status,leaveBalance,photo,isAdmin);
				}
				
				return employee;
				
			}
		 
		 public static Employee getEmpByemailv2(Connection connection, String email) throws SQLException {
			    Employee employee = null;
			    PreparedStatement preparedStatement = null;
			    ResultSet rs = null;
			    
			    try {
			        preparedStatement = connection.prepareStatement(GET_EMPLOY_EMAIL);
			        preparedStatement.setString(1, email);
			        
			        rs = preparedStatement.executeQuery();
			        
			        if (rs.next()) {
			            int id = rs.getInt("EMPLOYEEID");
			            int otp = rs.getInt("OTP");
			            String firstName = rs.getString("firstName");
			            String lastName = rs.getString("lastName");
			            String retrievedEmail = rs.getString("email");
			            String position = rs.getString("position");
			            String department = rs.getString("department");
			            String dateOfBirth = rs.getString("dateOfBirth");
			            String gender = rs.getString("gender");
			            String phoneNumber = rs.getString("phoneNumber");
			            String address = rs.getString("address");
			            String status = rs.getString("status");
			            Blob photo = rs.getBlob("employeePhoto");
			            double leaveBalance = rs.getDouble("leaveBalance");
			            int isAdmin = rs.getInt("ISADMIN");
			            
			            employee = new Employee(id, otp, firstName, lastName, retrievedEmail, position, department, dateOfBirth,
			                    gender, phoneNumber, address, status, leaveBalance, photo, isAdmin);
			        }
			    } finally {
			        // Close ResultSet, PreparedStatement, and Connection in reverse order
			        if (rs != null) {
			            try {
			                rs.close();
			            } catch (SQLException e) {
			                // Log or handle the exception
			                e.printStackTrace();
			            }
			        }
			        if (preparedStatement != null) {
			            try {
			                preparedStatement.close();
			            } catch (SQLException e) {
			                // Log or handle the exception
			                e.printStackTrace();
			            }
			        }
			        // Note: It's generally better to keep the connection open for reuse at a higher level.
			        // So, we don't close it here, but rather let the calling code handle the connection.
			    }
			    
			    return employee;
			}

		 
	 
	 public static List<Employee> selectAllUsers() throws SQLException{

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<Employee> employee = new ArrayList<>();
			// Step 1: Establishing a Connection
			Connection_ con =  new Connection_();
			try(Connection connection = con.getc()){

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_EMPLOY);
			System.out.println(preparedStatement);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				int id = rs.getInt("EMPLOYEEID");
				String firstName = rs.getString("firstName");
		        String lastName = rs.getString("lastName");
		        String email = rs.getString("email");
		        String position = rs.getString("position");
		        String department = rs.getString("department");
		        String dateOfBirth = rs.getString("dateOfBirth");
		        String gender = rs.getString("gender");
		        String phoneNumber = rs.getString("phoneNumber");
		        String address = rs.getString("address");
		        String status = rs.getString("status");
		        Blob photo = rs.getBlob("employeePhoto");
		        double leaveBalance = rs.getInt("leaveBalance");
				employee.add(new Employee(id,firstName,lastName,email,position,department,dateOfBirth,gender,phoneNumber,address,status,leaveBalance,photo));
			}
			 
			return employee;
			}
		}
	 
	 
	 // get employee and leave join
	 
	 public static List<EmpLeave> selectEmpReqJoin1() throws SQLException{

			// using try-with-resources to avoid closing resources (boiler plate code)
			List<EmpLeave> employee = new ArrayList<>();
			// Step 1: Establishing a Connection
			Connection_ con =  new Connection_();
			try(Connection connection = con.getc()){

			// Step 2:Create a statement using connection object
			PreparedStatement preparedStatement = connection.prepareStatement(EMP_LEAVE_JOIN_SQL);
			System.out.println(preparedStatement);
			
			// Step 3: Execute the query or update query
			ResultSet rs = preparedStatement.executeQuery();

			// Step 4: Process the ResultSet object.
			while (rs.next()) {
				while (rs.next()) {
					
					/*
					 * ResultSetMetaData metaData = rs.getMetaData(); int columnCount =
					 * metaData.getColumnCount();
					 * 
					 * for (int i = 1; i <= columnCount; i++) { String columnName =
					 * metaData.getColumnName(i); System.out.println("Column " + i + ": " +
					 * columnName); }
					 */
					
		            EmpLeave empLeave = new EmpLeave();
		            empLeave.setEmployeeId(rs.getInt("EMPLOYEEID"));
		            empLeave.setFirstName(rs.getString("FIRSTNAME"));
		            empLeave.setLastName(rs.getString("LASTNAME"));
		            empLeave.setEmail(rs.getString("EMAIL"));
		            empLeave.setPosition(rs.getString("POSITION"));
		            empLeave.setDepartment(rs.getString("DEPARTMENT"));
		            empLeave.setDateOfBirth(rs.getString("DATEOFBIRTH"));
		            empLeave.setGender(rs.getString("GENDER"));
		            empLeave.setPhoneNumber(rs.getString("PHONENUMBER"));
		            empLeave.setAddress(rs.getString("ADDRESS"));
		            empLeave.setEstatus(rs.getString("STATUS"));
		            empLeave.setLeaveBalance(rs.getDouble("LEAVEBALANCE"));
		            empLeave.setIsAdmin(rs.getInt("ISADMIN"));		            		            
		            empLeave.setLeaveId(rs.getInt("LEAVE_ID"));
		            empLeave.setLeaveType(rs.getString("LEAVE_TYPE"));
		            empLeave.setStartDate(rs.getString("START_DATE"));
		            empLeave.setEndDate(rs.getString("END_DATE"));
		            empLeave.setApplyDate(rs.getString("APPLY_DATE"));
		            empLeave.setReason(rs.getString("REASON"));
		            empLeave.setLstatus(rs.getString("STATUS"));
		            empLeave.setApprovalBy(rs.getInt("approval_by")+"");
		            empLeave.setApprovalDate(rs.getDate("approval_date") == null ? null : rs.getDate("approval_date").toLocaleString());
				  	    
		            empLeave.setDuration(rs.getInt("DURATION"));

		            employee.add(empLeave);
		        }
			}
			
		
			return employee;
			}
		}
	 
	 
	 public static List<EmpLeave> selectEmpReqJoin() throws SQLException {
		    List<EmpLeave> employee = new ArrayList<>();
		    
		    // Step 1: Establishing a Connection
		    Connection_ con = new Connection_();
		    try (Connection connection = con.getc()) {

		        // Step 2: Create a statement using connection object
		        PreparedStatement preparedStatement = connection.prepareStatement(EMP_LEAVE_JOIN_SQL);
		        System.out.println(preparedStatement);

		        // Step 3: Execute the query
		        ResultSet rs = preparedStatement.executeQuery();

		        // Step 4: Process the ResultSet object
		        while (rs.next()) {
		            EmpLeave empLeave = new EmpLeave();
		            empLeave.setEmployeeId(rs.getInt("EMPLOYEEID"));
		            empLeave.setFirstName(rs.getString("FIRSTNAME"));
		            empLeave.setLastName(rs.getString("LASTNAME"));
		            empLeave.setEmail(rs.getString("EMAIL"));
		            empLeave.setPosition(rs.getString("POSITION"));
		            empLeave.setDepartment(rs.getString("DEPARTMENT"));
		            empLeave.setDateOfBirth(rs.getString("DATEOFBIRTH"));
		            empLeave.setGender(rs.getString("GENDER"));
		            empLeave.setPhoneNumber(rs.getString("PHONENUMBER"));
		            empLeave.setAddress(rs.getString("ADDRESS"));
		            empLeave.setEstatus(rs.getString("STATUS"));
		            empLeave.setLeaveBalance(rs.getDouble("LEAVEBALANCE"));
		            empLeave.setIsAdmin(rs.getInt("ISADMIN"));
		            empLeave.setLeaveId(rs.getInt("LEAVE_ID"));
		            empLeave.setLeaveType(rs.getString("LEAVE_TYPE"));
		            empLeave.setStartDate(rs.getString("START_DATE"));
		            empLeave.setEndDate(rs.getString("END_DATE"));
		            empLeave.setApplyDate(rs.getString("APPLY_DATE"));
		            empLeave.setReason(rs.getString("REASON"));
		            empLeave.setLstatus(rs.getString("STATUS"));
		            empLeave.setApprovalBy(rs.getInt("approval_by") + "");
		            empLeave.setApprovalDate(rs.getDate("approval_date") == null ? null : rs.getDate("approval_date").toLocaleString());
		            empLeave.setDuration(rs.getInt("DURATION"));

		            employee.add(empLeave);
		        }
		    }

		    return employee;
		}

	 static String JOIN_DATE = "SELECT e.*, l.* " +
             "FROM employee e " +
             "JOIN leave_history l ON e.employeeid = l.employee_id " +
             "WHERE TO_DATE(?, 'DD-MM-YYYY') BETWEEN l.start_date AND l.end_date";
	 public static List<EmpLeave> selectEmpReqJoinDate(String dateString) throws SQLException {
		    List<EmpLeave> employee = new ArrayList<>();
		    
		    // Step 1: Establishing a Connectiodate
		    Connection_ con = new Connection_();
		    try (Connection connection = con.getc()) {

		        // Step 2: Create a statement using connection object
		        PreparedStatement preparedStatement = connection.prepareStatement(JOIN_DATE);
		        System.out.println(preparedStatement);

		        // Set the date parameter
		        //String dateString = "04-07-2024"; // your date string
		        preparedStatement.setString(1, dateString);
		        
		        // Step 3: Execute the query
		        ResultSet rs = preparedStatement.executeQuery();

		        // Step 4: Process the ResultSet object
		        while (rs.next()) {
		            EmpLeave empLeave = new EmpLeave();
		            empLeave.setEmployeeId(rs.getInt("EMPLOYEEID"));
		            empLeave.setFirstName(rs.getString("FIRSTNAME"));
		            empLeave.setLastName(rs.getString("LASTNAME"));
		            empLeave.setEmail(rs.getString("EMAIL"));
		            empLeave.setPosition(rs.getString("POSITION"));
		            empLeave.setDepartment(rs.getString("DEPARTMENT"));
		            empLeave.setDateOfBirth(rs.getString("DATEOFBIRTH"));
		            empLeave.setGender(rs.getString("GENDER"));
		            empLeave.setPhoneNumber(rs.getString("PHONENUMBER"));
		            empLeave.setAddress(rs.getString("ADDRESS"));
		            empLeave.setEstatus(rs.getString("STATUS"));
		            empLeave.setLeaveBalance(rs.getDouble("LEAVEBALANCE"));
		            empLeave.setIsAdmin(rs.getInt("ISADMIN"));
		            empLeave.setLeaveId(rs.getInt("LEAVE_ID"));
		            empLeave.setLeaveType(rs.getString("LEAVE_TYPE"));
		            empLeave.setStartDate(rs.getString("START_DATE"));
		            empLeave.setEndDate(rs.getString("END_DATE"));
		            empLeave.setApplyDate(rs.getString("APPLY_DATE"));
		            empLeave.setReason(rs.getString("REASON"));
		            empLeave.setLstatus(rs.getString("STATUS"));
		            empLeave.setApprovalBy(rs.getInt("approval_by") + "");
		            empLeave.setApprovalDate(rs.getDate("approval_date") == null ? null : rs.getDate("approval_date").toLocaleString());
		            empLeave.setDuration(rs.getInt("DURATION"));

		            employee.add(empLeave);
		        }
		    }

		    return employee;
		}
	 
	 
	 public static String convertDateFormat(String inputDate) {
		    String[] parts = inputDate.split("-");
		    if (parts.length != 3) {
		        return null; // Invalid date format, handle accordingly
		    }
		    return parts[2] + "-" + parts[1] + "-" + parts[0];
		}

	 
	 // Update Employ
	 
	 public static int UpdateEmploy(Employee emp) throws SQLIntegrityConstraintViolationException,SQLException  {
		 int rowsAffected = 0;
		 String Error = " ";
		
		 Connection_ con =  new Connection_();
		 try(Connection connection = con.getc()){
		 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOY);
		 
		 preparedStatement.setString(1, emp.getFirstName());
         preparedStatement.setString(2, emp.getLastName());
         preparedStatement.setString(3, emp.getEmail());
         preparedStatement.setString(4, emp.getPosition());
         preparedStatement.setString(5, emp.getDepartment());
         
         java.sql.Date dateOfBirth = java.sql.Date.valueOf(emp.getDateOfBirth());
         preparedStatement.setDate(6,dateOfBirth);
         preparedStatement.setString(7, emp.getGender());
         preparedStatement.setString(8, emp.getPhoneNumber());
         preparedStatement.setString(9, emp.getAddress());
         preparedStatement.setString(10, emp.getStatus());
          
         preparedStatement.setInt(11, emp._id);
         
         //java.sql.SQLException: ORA-17003: Invalid column index
         rowsAffected = preparedStatement.executeUpdate();
         System.out.println("Row " + rowsAffected);
		return rowsAffected;
		 }
	 }
	 
	 
	// Update Employ 2
	 
		 public static int UpdateEmploy2(Employee emp) throws SQLIntegrityConstraintViolationException,SQLException  {
			 int rowsAffected = 0;
			 String Error = " ";
			
			 Connection_ con =  new Connection_();
			 try(Connection connection = con.getc()){
			 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOY2);
			 
			 preparedStatement.setString(1, emp.getFirstName());
	         preparedStatement.setString(2, emp.getLastName());
	         preparedStatement.setString(3, emp.getEmail());
	         preparedStatement.setString(4, emp.getPosition());
	         preparedStatement.setString(5, emp.getDepartment());
	         
	         java.sql.Date dateOfBirth = java.sql.Date.valueOf(emp.getDateOfBirth());
	         preparedStatement.setDate(6,dateOfBirth);
	         preparedStatement.setString(7, emp.getGender());
	         preparedStatement.setString(8, emp.getPhoneNumber());
	         preparedStatement.setString(9, emp.getAddress());
	         preparedStatement.setString(10, emp.getStatus());
	         
	         if(emp.employeePhotoo != null) {
	        	 preparedStatement.setBlob(11, emp.employeePhotoo); 
	         }else {
		         preparedStatement.setBlob(11, emp.getEmployeePhoto()); 
	         }
	         
	         preparedStatement.setInt(12, emp._id);
	         
	         //java.sql.SQLException: ORA-17003: Invalid column index
	         rowsAffected = preparedStatement.executeUpdate();
	         System.out.println("Row " + rowsAffected);
			return rowsAffected;
			 }
		 }
		 
		 
		 
		 
		// Update Employ 2
		 
			 public static int UpdateEmploy3(Employee emp) throws SQLIntegrityConstraintViolationException,SQLException  {
				 int rowsAffected = 0;
				 String Error = " ";
				
				 Connection_ con =  new Connection_();
				 try(Connection connection = con.getc()){
				 PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_EMPLOY3);
				 
				 preparedStatement.setString(1, emp.getFirstName());
		         preparedStatement.setString(2, emp.getLastName());
		         preparedStatement.setString(3, emp.getEmail());
		         preparedStatement.setString(4, emp.getPosition());
		         preparedStatement.setString(5, emp.getDepartment());
		         
		         java.sql.Date dateOfBirth = java.sql.Date.valueOf(emp.getDateOfBirth());
		         preparedStatement.setDate(6,dateOfBirth);
		         preparedStatement.setString(7, emp.getGender());
		         preparedStatement.setString(8, emp.getPhoneNumber());
		         preparedStatement.setString(9, emp.getAddress());
		         preparedStatement.setString(10, emp.getStatus());
		         
		         if(emp.employeePhotoo != null) {
		        	 preparedStatement.setBlob(11, emp.employeePhotoo); 
		         }else {
			         preparedStatement.setBlob(11, emp.getEmployeePhoto()); 
		         }
		         
		         preparedStatement.setInt(12, emp.getIsAdmin());
		         
		         
		         preparedStatement.setInt(13, emp._id);
		         
		         //java.sql.SQLException: ORA-17003: Invalid column index
		         rowsAffected = preparedStatement.executeUpdate();
		         System.out.println("Row " + rowsAffected);
				return rowsAffected;
				 }
			 }
			 
		 
		 
		 
		 // update
		 
		 public static int sendOtp(int id,int otp) throws SQLIntegrityConstraintViolationException,SQLException  {
			 int rowsAffected = 0;
			 String Error = " ";
			
			 Connection_ con =  new Connection_();
			 try(Connection connection = con.getc()){
			 PreparedStatement preparedStatement = connection.prepareStatement(ADD_OTP);
			 
			 preparedStatement.setInt(1, otp);
	         preparedStatement.setInt(2, id);
	         
	         System.out.println("OTP ADDED TO DATABASE ");
	         //java.sql.SQLException: ORA-17003: Invalid column index
	         rowsAffected = preparedStatement.executeUpdate();
	         System.out.println("Row " + rowsAffected);
			return rowsAffected;
			 }
		 }
		 
		 
		 
	 
	 
	// Method to generate JavaScript code for displaying an alert and redirecting
	   public static String sendAlert(String message, String redirectURL) {
	        return "<script>alert('" + message + "'); window.location.href = '" + redirectURL + "';</script>";
	    }
	  
	   public static String givAlertBox(String message, String redirectURL) {
		    return "<!DOCTYPE html>" +
		           "<html lang=\"en\">" +
		           "<head>" +
		               "<meta charset=\"UTF-8\">" +
		               "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
		               "<title>Bootstrap Alert Box</title>" +
		               "<!-- Bootstrap CSS -->" +
		               "<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\">" +
		               "<!-- jQuery -->" +
		               "<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js\"></script>" +
		               "<!-- Bootstrap JS -->" +
		               "<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>" +
		           "</head>" +
		           "<body>" +
		               "<div class=\"container mt-5\">" +
		                   "<div class=\"alert alert-primary\" role=\"alert\">" +
		                       message +
		                       "<button type=\"button\" class=\"btn btn-primary\" onclick=\"redirectToURL('" + redirectURL + "')\">OK</button>" +
		                   "</div>" +
		               "</div>" +
		               "<!-- JavaScript function to redirect to a specific URL -->" +
		               "<script>" +
		                   "function redirectToURL(url) {" +
		                       "window.location.href = url;" +
		                   "}" +
		               "</script>" +
		           "</body>" +
		           "</html>";
		}
	   
	   public static void errorPage(Exception ee,HttpServletRequest request, HttpServletResponse response,String err) {
			ee.printStackTrace();
	    	// Redirect to an error page with a specific message
	        String errorMessage = "An error occurred: " + ee.getMessage();
	        request.setAttribute("errorMessage", errorMessage);
	        request.setAttribute("err", err);

	        RequestDispatcher dispatcher = request.getRequestDispatcher("/error.jsp");
	        try {
				dispatcher.forward(request, response);
			} catch (ServletException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        
		}
	   
	   public static String getCookieValue(Cookie[] cookies, String cookieName) {
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if (cookie.getName().equals(cookieName)) {
	                    return cookie.getValue();
	                }
	            }
	        }
	        return null; // Return null if the cookie with the specified name is not found
	    }
	   
	   
	   public static void giveSpinner(HttpServletResponse response) throws IOException {
		   // Set content type
	        response.setContentType("text/html");

	        // Set cache control headers to prevent caching
	        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
	        response.setHeader("Pragma", "no-cache"); // HTTP 1.0
	        response.setHeader("Expires", "0"); // Proxies

	        // Write HTML response directly to the HttpServletResponse object
	        response.getWriter().println("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><title>Loader Spinner Example</title><link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css\" rel=\"stylesheet\"><style>.loader-container { display: flex; justify-content: center; align-items: center; position: fixed; top: 0; left: 0; width: 100%; height: 100%; background-color: rgba(0, 0, 0, 0.5); z-index: 9999; } .loader { width: 3rem; height: 3rem; border-width: 0.3em; border-color: rgba(255, 255, 255, 0.3); border-top-color: #ffffff; border-radius: 50%; animation: spin 1s linear infinite; } @keyframes spin { to { transform: rotate(360deg); } } .loaded #loader { display: none; }</style></head><body><div id=\"loader\" class=\"loader-container\"><div class=\"spinner-border text-light\" role=\"status\"><span class=\"visually-hidden\">Loading...</span></div></div><script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js\"></script><script>window.onload = function() { document.getElementById('loader').classList.add('loaded'); };</script></body></html>");
	    
	   }
	   
	   public static void removeSpinner(HttpServletResponse response) throws IOException {
	        // Send JavaScript code to remove loader spinner
	        response.getWriter().println("<script>document.getElementById('loader').style.display = 'none';</script>");
	    }
	     
}
