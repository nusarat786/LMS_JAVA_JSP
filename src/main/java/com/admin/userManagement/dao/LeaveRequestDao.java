package com.admin.userManagement.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Timestamp;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.admin.userManagement.bean.Employee;
import com.admin.userManagement.bean.HoidayBean;
import com.admin.userManagement.bean.LeaveRequestBean;
import com.connection.Connection_;
import com.employee.auth.Email;

public class LeaveRequestDao {
	
	
	public static int  removed = 0;
	// Assuming you are using PreparedStatement
	private static final String INSERT_LEAVE_SQL = "INSERT INTO leave_history (employee_id, leave_type, start_date, end_date, apply_date, reason, status) " +
	            "VALUES (?, ?, TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), TO_DATE(?, 'YYYY-MM-DD'), ?, ?)";

	private static final String INSERT_LEAVE_SQL2 = "INSERT INTO leave_history (employee_id, leave_type, start_date, end_date, reason, status,duration) " +
            "VALUES (?, ?, ?, ?, ?, ?,?)";

	// Beetween Date
	private static final String BY_EMP_ID_SQL = "select * from leave_history where employee_id=?";
	
	// Beetween Date
	private static final String ALL_EMP_REQUEST_SQL = "select * from leave_history";
		
	
	 private static final String SELECT_ALL_EMPLOY  = "SELECT * from Employee";
	 
	 private static final String GET_EMPLOY_ID = "SELECT * from Employee WHERE employeeID = ?";

	 private static final String GET_LEAVE_ID_SQL = "SELECT * from leave_history WHERE LEAVE_ID = ?";

	 private static final String ACCEPT_LEAVE_BY_ID_SQL = "UPDATE leave_history SET status = ?, approval_by = ?, approval_date = ? WHERE LEAVE_ID = ?";

	 private static final String RESET_LEAVE_BALANCE_SQL = "UPDATE Employee SET LEAVEBALANCE = ?";

	 private static final String RESET_LEAVE_BALANCE_ID_SQL = "UPDATE Employee SET LEAVEBALANCE = ? WHERE employeeID = ?";

	 
	 private static final String GET_EMPLOY_EMAIL = "SELECT * from Employee WHERE Email = ?";

	 
	 private static final String UPDATE_EMPLOY = "UPDATE Employee SET FirstName = ? WHERE employeeID = ?";
	 
	 private static final String UPDATE_EMPLOY2 = "UPDATE Employee SET FirstName = ?, LastName = ?, Email = ?, Position = ?, Department = ?, "
             + "DateOfBirth = ?, Gender = ?, PhoneNumber = ?, Address = ?, Status = ?,EMPLOYEEPHOTO=? WHERE employeeID = ?";

	 private static final String UPDATE_LEAVE_BALANCE_SQL = "update Employee set leaveBalance=? WHERE employeeID = ?" ;
	 
	 
	 private static final String SELECT_LEAVE_EMPLOY_JOIN_SQL  = "SELECT * from Employee";
	private static final String SELECT_EMAIL_FROM_EMPLOY = "SELECT Email from Employee";
	 
	 
	 // apply for leave
	 public static int inserLeaveRequest(LeaveRequestBean leaveRequest) throws SQLIntegrityConstraintViolationException,SQLException  {
		 int rowsAffected = 0;
		 String Error = " ";
		
		 Connection_ con =  new Connection_();
		 try(Connection connection = con.getc()){
		 PreparedStatement statement = connection.prepareStatement(INSERT_LEAVE_SQL);
		 
		 
		 statement.setInt(1, leaveRequest.getEmployeeId());
         statement.setString(2, leaveRequest.getLeaveType());
         statement.setString(3, leaveRequest.getStartDate());
         statement.setString(4, leaveRequest.getEndDate());
         statement.setString(5, leaveRequest.getApplyDate());
         statement.setString(6, leaveRequest.getReason());
         statement.setString(7, leaveRequest.getStatus());
         
    
         rowsAffected = statement.executeUpdate();
         
		return rowsAffected;
		
		 }
		
	 }
	 
	 
	 
	 public static Map<String, Long> inserLeaveRequest2(LeaveRequestBean leaveRequest,double leaveBalnace,String email) throws Exception  {
		 int rowsAffected = 0;
		 String Error = " ";
		 
		 Map<String, Long> hm  = calculateDurationAndExcludedCount(leaveRequest.getStartDate(),leaveRequest.getEndDate(),HolidayDao.getHolidays());
	      
		 hm.put("rowsAffected", (long) 0);
		 long noOfDays = hm.get("duration");
		 long removed = hm.get("excludedCount");
		 
		 if(noOfDays<=0) {
			 throw new Exception("All Date In The List Are HoliDays Alredy");
		 }
				 //calculateDurationExcludingWeekends(leaveRequest.getStartDate(),leaveRequest.getEndDate(),HolidayDao.getHolidays());
         System.out.println("Test........ " + noOfDays);
		 
         if(leaveBalnace<noOfDays) {
        	 throw new Exception("Leave Balance" + "(" +leaveBalnace +")" +" < " + "applied days" +"(" + noOfDays +")");
         }
         
         checkLeaveOverlap(getLeaveHistoryByEmployeeId(leaveRequest.getEmployeeId()),leaveRequest.getStartDate(),leaveRequest.getEndDate() );
		 Connection_ con =  new Connection_();
		 try(Connection connection = con.getc()){
		 PreparedStatement statement = connection.prepareStatement(INSERT_LEAVE_SQL2);
		 
		 
		 statement.setInt(1, leaveRequest.getEmployeeId());
         statement.setString(2, leaveRequest.getLeaveType());
         statement.setDate(3, java.sql.Date.valueOf(leaveRequest.getStartDate()));
         statement.setDate(4, java.sql.Date.valueOf(leaveRequest.getEndDate()));
         statement.setString(5, leaveRequest.getReason());
         //System.out.println("Test........ 0" + leaveRequest.getStatus());
         statement.setString(6, leaveRequest.getStatus());
         statement.setInt(7, (int)noOfDays);
         
         
         
         rowsAffected = statement.executeUpdate();
         
         hm.put("rowsAffected", (long) rowsAffected);
		 
         Date date = new Date();
         String[] to = {"selfielectronic@gmail.com",email};
         Email.sendEmail(to, leaveRequest.getStartDate(), leaveRequest.getEndDate(),date.toLocaleString() );
         
		return hm;
		 }
		
	 }
	 
	 
	 public static int ResetLeaveBalance(Employee emp ,int lvb) throws Exception  {
		 int rowsAffected = 0;
		 String Error = " ";
		 
		 // connection created
         Connection_ con =  new Connection_();
         try(Connection connection = con.getc()){
		 
		 // prepared statement
		 PreparedStatement statement = connection.prepareStatement(RESET_LEAVE_BALANCE_ID_SQL);
		 
		 
		 //set leave is
		 statement.setInt(1,lvb );
		 statement.setInt(2,emp._id );
	     			 		          
         // execude update
         rowsAffected = statement.executeUpdate();
                                    
         connection.close();
         
         String[] to = {emp.getEmail()};
         Email.sendEmail(to, lvb);
         
		return rowsAffected;
        }
	 }
	 
	 public static int ResetLeaveBalance(int lvb) throws Exception  {
		 int rowsAffected = 0;
		 String Error = " ";
		 
		 // connection created
         Connection_ con =  new Connection_();
         try(Connection connection = con.getc()){
		 
		 // prepared statement
		 PreparedStatement statement = connection.prepareStatement(RESET_LEAVE_BALANCE_SQL);
		 
		 
		 //set leave is
		 statement.setInt(1,lvb );
	     		 		          
         // execude update
         rowsAffected = statement.executeUpdate();
                                    
         connection.close();
         Date date = new Date();
         String[] to = getAllEmails();
         Email.sendEmail(to, lvb);
         
		return rowsAffected;
         }
	 }
	 
	// Method to fetch all emails from Employee table and store them in an array
	    public static String[] getAllEmails() throws SQLException {
	        List<String> emails = new ArrayList<>();
	        emails.add("nusarathaveliwala@gmail.com");
	        Connection_ con =  new Connection_();
	        
	        try (Connection connection = con.getc();){
	             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EMAIL_FROM_EMPLOY);
	             ResultSet resultSet = preparedStatement.executeQuery();

	            // Iterate through the result set and add emails to the list
	            while (resultSet.next()) {
	                String email = resultSet.getString("Email");
	                emails.add(email);
	            }
	        }

	        // Convert list to array
	        return emails.toArray(new String[0]);
	    }
	 
	 
	
	 public static int AcceptLeaveRequest(LeaveRequestBean leaveRequest,Employee e ) throws Exception  {
		 int rowsAffected = 0;
		 String Error = " ";
		 
		 // connection created
         Connection_ con =  new Connection_();
         try(Connection connection = con.getc()){
		 
		 // prepared statement
		 PreparedStatement statement = connection.prepareStatement(ACCEPT_LEAVE_BY_ID_SQL);
		 
		 
		 //set leave is
		 statement.setString(1, "ACCEPTED");
	     
		 
		 statement.setInt(2, leaveRequest.getLeaveId());
        
         
         // execude update
         rowsAffected = statement.executeUpdate();
         
         if(rowsAffected==0) {
        	throw new Exception("Request can not be full filled please try again");
         }
         
         rowsAffected =0;
         PreparedStatement statement2 = connection.prepareStatement(UPDATE_LEAVE_BALANCE_SQL);
		 
         int leaveb = (int)e.getLeaveBalance()- leaveRequest.getDuration();
         statement2.setInt(1,leaveb);
         statement2.setInt(2,e._id);
         System.out.println("Test...." + leaveb);
         
         rowsAffected = statement2.executeUpdate();
         
         
         connection.close();
         Date date = new Date();
         String[] to = {"selfielectronic@gmail.com",e.getEmail()};
         Email.sendEmail(to, leaveRequest.getStartDate(), leaveRequest.getEndDate(),date.toLocaleString(),"Accepted and Remaining Leave Balance Is:  " + leaveb ,leaveRequest.getLeaveId(),"Leave Applcation Is Accepted" );
         
		return rowsAffected;
         }
	 }
	 
	 
	 public static int AcceptLeaveRequest(LeaveRequestBean leaveRequest,Employee e,int acceptBy ) throws Exception  {
		 int rowsAffected = 0;
		 String Error = " ";
		 Date date = new Date();
		 
		 if(!leaveRequest.getStatus().contains("PENDING")) {
			 throw new Exception("Leave IS Alredy Accepted/Rejected");
		 }
		 
		 if(e.getLeaveBalance() - leaveRequest.getDuration() <0) {
			 throw new Exception("Leave Balance Exhusted: " + e.getLeaveBalance() + "<" + leaveRequest.getDuration());
		 }
		 
		 
		 
		 // connection created
         Connection_ con =  new Connection_();
         try(Connection connection = con.getc()){
		 
		 // prepared statement
		 PreparedStatement statement = connection.prepareStatement(ACCEPT_LEAVE_BY_ID_SQL);
		 
		 
		 //set leave is
		 statement.setString(1, "ACCEPTED");
		 statement.setInt(2, acceptBy);
		 // Get the current date and time
         Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
         
		 statement.setTimestamp(3, currentTimestamp);
		 statement.setInt(4, leaveRequest.getLeaveId());
        
         
         // execude update
         rowsAffected = statement.executeUpdate();
         
         if(rowsAffected==0) {
        	throw new Exception("Request can not be full filled please try again");
         }
         
         rowsAffected =0;
         PreparedStatement statement2 = connection.prepareStatement(UPDATE_LEAVE_BALANCE_SQL);
		 
         int leaveb = (int)e.getLeaveBalance()- leaveRequest.getDuration();
         statement2.setInt(1,leaveb);
         statement2.setInt(2,e._id);
         System.out.println("Test...." + leaveb);
         
         
         rowsAffected = statement2.executeUpdate();
         
         
         connection.close();
         
         String[] to = {"selfielectronic@gmail.com",e.getEmail()};
         Email.sendEmail(to, leaveRequest.getStartDate(), leaveRequest.getEndDate(),date.toLocaleString(),"Accepted and Remaining Leave Balance Is:  " + leaveb ,leaveRequest.getLeaveId(),"Leave Applcation Is Accepted" );
         
		return rowsAffected;
         }
		
	 }
	 
	 
	 
	 public static int RejectLeaveRequest(LeaveRequestBean leaveRequest,Employee e,int acceptBy ) throws Exception  {
		 int rowsAffected = 0;
		 String Error = " ";
		 Date date = new Date();
		 
		 if(!leaveRequest.getStatus().contains("PENDING")) {
			 throw new Exception("Leave IS Alredy Accepted/Rejected");
		 }
		 
		 if(e.getLeaveBalance() - leaveRequest.getDuration() <0) {
			 throw new Exception("Leave Balance Exhusted: " + e.getLeaveBalance() + "<" + leaveRequest.getDuration());
		 }
		 
		 // connection created
         Connection_ con =  new Connection_();
         try(Connection connection = con.getc()){
		 // prepared statement
		 PreparedStatement statement = connection.prepareStatement(ACCEPT_LEAVE_BY_ID_SQL);
		 
		 
		 //set leave is
		 statement.setString(1, "REJECTED");
		 statement.setInt(2, acceptBy);
		 // Get the current date and time
         Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
         
		 statement.setTimestamp(3, currentTimestamp);
		 statement.setInt(4, leaveRequest.getLeaveId());
        
         
         // execude update
         rowsAffected = statement.executeUpdate();
         
         if(rowsAffected==0) {
        	throw new Exception("Request can not be full filled please try again");
         }
         
         double leaveb = e.getLeaveBalance();
         connection.close();
         
         String[] to = {"selfielectronic@gmail.com",e.getEmail()};
         Email.sendEmail(to, leaveRequest.getStartDate(), leaveRequest.getEndDate(),date.toLocaleString(),"REJECTED Because Of Tight Dead Line For Project and Your Leave Balance Is:  " + leaveb ,leaveRequest.getLeaveId(),"Leave Applcation Is Accepted" );
         
		return rowsAffected;
         }
	 }
	 
	 
	 
	 
	 public static List<LeaveRequestBean> getLeaveHistoryByEmployeeId(int employeeId) throws SQLException {
		    List<LeaveRequestBean> leaveHistoryList = new ArrayList<>();
		    
		    // Step 1: Establishing a Connection
		 	Connection_ con =  new Connection_();
		 	try(Connection connection = con.getc()){
		 	// Step 2:Create a statement using connection object
		 	PreparedStatement preparedStatement = connection.prepareStatement(BY_EMP_ID_SQL);
		 	System.out.println(preparedStatement);
		 	
		 	preparedStatement.setInt(1, employeeId);
		 	
		 	// Step 3: Execute the query or update query
		 	ResultSet resultSet = preparedStatement.executeQuery();
		 	
		 	while (resultSet.next()) {
		 		LeaveRequestBean leaveHistory = new LeaveRequestBean();
                leaveHistory.setEmployeeId(resultSet.getInt("employee_id"));
                leaveHistory.setLeaveId(resultSet.getInt("LEAVE_ID"));
                leaveHistory.setLeaveType(resultSet.getString("leave_type"));
                leaveHistory.setStartDate(resultSet.getDate("start_date").toString());
                leaveHistory.setEndDate(resultSet.getDate("end_date").toString());
                System.out.println(resultSet.getTimestamp("apply_date").toString());
                leaveHistory.setApplyDate(resultSet.getTimestamp("apply_date").toString());
                leaveHistory.setReason(resultSet.getString("reason"));
                leaveHistory.setStatus(resultSet.getString("status"));
                leaveHistory.setDuration(resultSet.getInt("duration"));
                //System.out.println("Test......."  + (resultSet.getInt("approval_by")+"").trim());
                leaveHistory.setApprovalBy((resultSet.getInt("approval_by")+"").trim());
  	          	leaveHistory.setApprovalDate(resultSet.getDate("approval_date") == null ? null : resultSet.getDate("approval_date").toLocaleString());
  		  	   
                leaveHistoryList.add(leaveHistory);
                
            }
		    
		 	connection.close();
		    return leaveHistoryList;
		 	}
		}

	 
	 public static List<LeaveRequestBean> getLeaveHistory() throws SQLException {
		    List<LeaveRequestBean> leaveHistoryList = new ArrayList<>();
		    
		    // Step 1: Establishing a Connection
		 	Connection_ con =  new Connection_();
		 	try(Connection connection = con.getc()){
		 	// Step 2:Create a statement using connection object
		 	PreparedStatement preparedStatement = connection.prepareStatement(ALL_EMP_REQUEST_SQL);
		 	System.out.println(preparedStatement);
		 	
		 	//preparedStatement.setInt(1, employeeId);
		 	
		 	// Step 3: Execute the query or update query
		 	ResultSet resultSet = preparedStatement.executeQuery();
		 	
		 	while (resultSet.next()) {
		 		LeaveRequestBean leaveHistory = new LeaveRequestBean();
             leaveHistory.setEmployeeId(resultSet.getInt("employee_id"));
             leaveHistory.setLeaveId(resultSet.getInt("LEAVE_ID"));
             leaveHistory.setLeaveType(resultSet.getString("leave_type"));
             leaveHistory.setStartDate(resultSet.getDate("start_date").toString());
             leaveHistory.setEndDate(resultSet.getDate("end_date").toString());
             System.out.println(resultSet.getTimestamp("apply_date").toString());
             leaveHistory.setApplyDate(resultSet.getTimestamp("apply_date").toString());
             leaveHistory.setReason(resultSet.getString("reason"));
             leaveHistory.setStatus(resultSet.getString("status"));
             leaveHistory.setDuration(resultSet.getInt("duration"));
           //System.out.println("Test......."  + (resultSet.getInt("approval_by")+"").trim());
             leaveHistory.setApprovalBy((resultSet.getInt("approval_by")+"").trim());
	         leaveHistory.setApprovalDate(resultSet.getDate("approval_date") == null ? null : resultSet.getDate("approval_date").toLocaleString());
		  	   
             leaveHistoryList.add(leaveHistory);
             
         }
		 	
		 // Sort the list by apply date in descending order
	        Collections.sort(leaveHistoryList, new Comparator<LeaveRequestBean>() {
	            @Override
	            public int compare(LeaveRequestBean o1, LeaveRequestBean o2) {
	                // Assuming applyDate is the field representing the apply date
	                return o2.getApplyDate().compareTo(o1.getApplyDate());
	            }
	        });
		 	
		 
		 
		 	
		 	return leaveHistoryList;
		 	}
		}
	 
	 
	 public static LeaveRequestBean getLeaveByID(String lid) throws SQLException {
		    
		 	LeaveRequestBean leaveHistory = new LeaveRequestBean();
		    // Step 1: Establishing a Connection
		 	Connection_ con =  new Connection_();
		 	try(Connection connection = con.getc()){
		 		
		 	
		 	// Step 2:Create a statement using connection object
		 	PreparedStatement preparedStatement = connection.prepareStatement(GET_LEAVE_ID_SQL);
		 	System.out.println(preparedStatement);
		 	
		 	preparedStatement.setInt(1, Integer.parseInt(lid));
		 	
		 	// Step 3: Execute the query or update query
		 	ResultSet resultSet = preparedStatement.executeQuery();
		 	
		 	while (resultSet.next()) {
		 	
	          leaveHistory.setEmployeeId(resultSet.getInt("employee_id"));
	          leaveHistory.setLeaveId(resultSet.getInt("LEAVE_ID"));
	          leaveHistory.setLeaveType(resultSet.getString("leave_type"));
	          leaveHistory.setStartDate(resultSet.getDate("start_date").toString());
	          leaveHistory.setEndDate(resultSet.getDate("end_date").toLocaleString());
	          //System.out.println(resultSet.getTimestamp("apply_date").toLocaleString());
	          leaveHistory.setApplyDate(resultSet.getTimestamp("apply_date").toLocaleString());
	          leaveHistory.setReason(resultSet.getString("reason"));
	          leaveHistory.setStatus(resultSet.getString("status"));
	          leaveHistory.setDuration(resultSet.getInt("duration"));
	          leaveHistory.setApprovalBy(resultSet.getInt("approval_by")+"");
	          leaveHistory.setApprovalDate(resultSet.getDate("approval_date") == null ? null : resultSet.getDate("approval_date").toLocaleString());
		  	    
		 	}
		 	
		 	return leaveHistory;
		 	}
		}
	 
	 
	 public static LeaveRequestBean getLeaveByID(Connection connection,String lid) throws SQLException {
		    
		 	LeaveRequestBean leaveHistory = new LeaveRequestBean();
		    
		 	
		 	// Step 2:Create a statement using connection object
		 	PreparedStatement preparedStatement = connection.prepareStatement(GET_LEAVE_ID_SQL);
		 	System.out.println(preparedStatement);
		 	
		 	preparedStatement.setInt(1, Integer.parseInt(lid));
		 	
		 	// Step 3: Execute the query or update query
		 	ResultSet resultSet = preparedStatement.executeQuery();
		 	
		 	while (resultSet.next()) {
		 	
	          leaveHistory.setEmployeeId(resultSet.getInt("employee_id"));
	          leaveHistory.setLeaveId(resultSet.getInt("LEAVE_ID"));
	          leaveHistory.setLeaveType(resultSet.getString("leave_type"));
	          leaveHistory.setStartDate(resultSet.getDate("start_date").toString());
	          leaveHistory.setEndDate(resultSet.getDate("end_date").toLocaleString());
	          //System.out.println(resultSet.getTimestamp("apply_date").toLocaleString());
	          leaveHistory.setApplyDate(resultSet.getTimestamp("apply_date").toLocaleString());
	          leaveHistory.setReason(resultSet.getString("reason"));
	          leaveHistory.setStatus(resultSet.getString("status"));
	          leaveHistory.setDuration(resultSet.getInt("duration"));
	          leaveHistory.setApprovalBy(resultSet.getInt("approval_by")+"");
	          leaveHistory.setApprovalDate(resultSet.getDate("approval_date") == null ? null : resultSet.getDate("approval_date").toLocaleString());
		  	    
		 	}
		 	
		 	return leaveHistory;
		 }
		

	 
	 static java.sql.Date getDate(String date){
			return java.sql.Date.valueOf(date);
		}
	 
	 
	 public static long calculateDurationExcludingWeekends(String startDateStr, String endDateStr, List<HoidayBean> holidays) {
		    LocalDate startDate = LocalDate.parse(startDateStr);
		    LocalDate endDate = LocalDate.parse(endDateStr);
		    
		    long duration = 0;
		    LocalDate currentDate = startDate;

		    while (!currentDate.isAfter(endDate)) {
		        if (currentDate.getDayOfWeek() != DayOfWeek.SATURDAY && 
		            currentDate.getDayOfWeek() != DayOfWeek.SUNDAY &&
		            !isHoliday(currentDate, holidays)) {
		            duration++;
		        }
		        currentDate = currentDate.plusDays(1);
		    }

		    return duration;
		}

		private static boolean isHoliday(LocalDate date, List<HoidayBean> holidays) {
		    for (HoidayBean holiday : holidays) {
		        LocalDate holidayDate = LocalDate.parse(holiday.getHolidayDate());
		        if (holidayDate.equals(date)) {
		            return true;
		        }
		    }
		    return false;
		}

	 
	 
	 
	 
	 public static void checkLeaveOverlap(List<LeaveRequestBean> leaveRequests, String startDateStr, String endDateStr) throws Exception {
	        LocalDate startDate = LocalDate.parse(startDateStr);
	        LocalDate endDate = LocalDate.parse(endDateStr);

	        for (LeaveRequestBean leaveRequest : leaveRequests) {
	            LocalDate existingStartDate = LocalDate.parse(leaveRequest.getStartDate());
	            LocalDate existingEndDate = LocalDate.parse(leaveRequest.getEndDate());
	            
	            if ((startDate.isEqual(existingStartDate) || startDate.isAfter(existingStartDate)) && 
	            	    (startDate.isEqual(existingEndDate) || startDate.isBefore(existingEndDate)) ||
	            	    (endDate.isEqual(existingStartDate) || endDate.isAfter(existingStartDate)) && 
	            	    (endDate.isEqual(existingEndDate) || endDate.isBefore(existingEndDate)) ||
	            	    (startDate.isBefore(existingStartDate) && endDate.isAfter(existingEndDate))) {
	            	    // There is an overlap, throw exception
	            	throw new Exception("Leave request overlaps with existing leave for employee ID: " + leaveRequest.getEmployeeId());
		            
	            	}
	            
	        }
	    }

	 public static Map<String, Long> calculateDurationAndExcludedCount(String startDateStr, String endDateStr, List<HoidayBean> holidays) {
		    LocalDate startDate = LocalDate.parse(startDateStr);
		    LocalDate endDate = LocalDate.parse(endDateStr);
		    
		    long duration = 0;
		    long excludedCount = 0;
		    LocalDate currentDate = startDate;

		    while (!currentDate.isAfter(endDate)) {
		        if (currentDate.getDayOfWeek() == DayOfWeek.SATURDAY || 
		            currentDate.getDayOfWeek() == DayOfWeek.SUNDAY ||
		            isHoliday(currentDate, holidays)) {
		            excludedCount++;
		        } else {
		            duration++;
		        }
		        currentDate = currentDate.plusDays(1);
		    }

		    Map<String, Long> result = new HashMap<>();
		    result.put("duration", duration);
		    result.put("excludedCount", excludedCount);
		    return result;
		}
	 
	 
	 public class LeaveOverlapException extends Exception {

		    public LeaveOverlapException(String message) {
		        super(message);
		    }
		}

	 
	 
	 
	 
	 
}
