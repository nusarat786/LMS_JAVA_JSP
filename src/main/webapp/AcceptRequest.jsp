<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.admin.userManagement.bean.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.admin.userManagement.bean.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.admin.userManagement.bean.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>
<%@ page import="com.connection.*" %>
<%@ page import="java.sql.*" %>





<%

	//Step 1: Establishing a Connection
	Connection_ con = new Connection_();
	Connection connection = con.getc();
	LeaveRequestBean leave =null;
	Employee emp =null;	
	System.out.println(request.getParameterMap().size()<=0);
	
	if(request.getParameterMap().size()<=0){
		
		System.out.println("Test.......................");
		out.println(EmployeeDao.sendAlert("Invalid Operation", "/LMS_V3/AllLeaveRequest.jsp"));
	
	}else{
		
		leave = LeaveRequestDao.getLeaveByID(connection,request.getParameter("leave_id"));
		System.out.println("Leave" + leave);
		
		emp =EmployeeDao.getEmpById(connection,Integer.parseInt(request.getParameter("emp_id")));
		System.out.println("Emp" + emp);
		
	}
	
	
%>    


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User Profile</title>
    <!-- Bootstrap CSS -->
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
    <!-- Font Awesome CSS -->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.1/css/all.min.css" rel="stylesheet">
    <!-- Custom CSS -->
    <style>
        .profile-image {
            max-width: 15rem;
            border-radius: 50%;
        }
        
        .card {
            margin-bottom: 20px;
        }
        
        .card-header h3 {
            margin-bottom: 0;
        }
        
        .emp{
        border-radius: 10px; /* Rounded borders */
       	box-shadow: rgba(0, 0, 0, 0.16) 0px 10px 36px 0px, rgba(0, 0, 0, 0.06) 0px 0px 0px 1px;transition: all 0.3s ease-in-out; /* Add smooth hover effect */
      
        }
        
        .leave{
	        border-radius: 10px; /* Rounded borders */
	        	box-shadow: 
	        box-shadow: 
	        0px 6px 10px rgba(0,0,0,0), /* Main shadow */
	        0px 1px 18px rgba(1, 1, 1, 0.6), /* Inset shadow */
	        0px 3px 5px rgba(0, 255, 1, 0.4); /* Subtle bottom shadow */
	    	transition: all 0.3s ease-in-out; /* Add smooth hover effect */
      		box-shadow: rgba(0, 0, 0, 0.25) 0px 54px 55px, rgba(0, 0, 0, 0.12) 0px -12px 30px, rgba(0, 0, 0, 0.12) 0px 4px 6px, rgba(0, 0, 0, 0.17) 0px 12px 13px, rgba(0, 0, 0, 0.09) 0px -3px 5px;
        	box-shadow: rgba(0, 0, 0, 0.16) 0px 10px 36px 0px, rgba(0, 0, 0, 0.06) 0px 0px 0px 1px;transition: all 0.3s ease-in-out; /* Add smooth hover effect */
      
        }
        
        
        
        
	.border-con{
		border: 2px solid #ccc;
      	border-radius: 10px;      	
	}
	
	body {
		  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif !important;
		}
		
	.bg_main {
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  
		}
		
	.list-group-item{
		border:none !important;
	}
	
	
	
	
	
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<%
    if (emp != null && leave != null) {
        // Display employee and leave details
%>


<body>

<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>

<jsp:include page="./FilterLeave/FormSpinner.jsp"></jsp:include>


<div class="container  border-con mt-2 mb-3">
<div class="row">
    <div class="container mt-4">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <!-- Employee Details Card -->
                <div class="card emp">
                    <div class="card-header bg_main">
                        <h3 class="mb-0">Employee Details</h3>
                    </div>
                    <div class="card-body">
                        <!-- Display employee photo -->
                        <div class="text-center mb-4">
                            <% Blob blob = emp.employeePhotoo; %>
                            <% if (blob != null) { %>
                                <% InputStream inputStream = blob.getBinaryStream(); %>
                                <% ByteArrayOutputStream outputStream = new ByteArrayOutputStream(); %>
                                <% int bytesRead = -1; %>
                                <% byte[] buffer = new byte[4096]; %>
                                <% while ((bytesRead = inputStream.read(buffer)) != -1) { %>
                                    <% outputStream.write(buffer, 0, bytesRead); %>
                                <% } %>
                                <% byte[] imageBytes = outputStream.toByteArray(); %>
                                <% String base64Image = Base64.getEncoder().encodeToString(imageBytes); %>
                                <img src="data:image/jpeg;base64, <%= base64Image %>" alt="Profile Picture" class="profile-image">
                            <% } else { %>
                                <!-- Default image if no photo is available -->
                                <img src="path/to/default/photo.jpg" alt="Profile Picture" class="profile-image">
                            <% } %>
                        </div>
                        <!-- Display other employee details -->
                        <!-- You can use JSP to populate these values -->
                        <!-- Example: -->
                        <ul class="list-group">
                            <li class="list-group-item"><strong>ID:</strong> <%= emp._id %></li>
                            <li class="list-group-item"><strong>First Name:</strong> <%= emp.getFirstName() %></li>
                            <li class="list-group-item"><strong>Last Name:</strong> <%= emp.getLastName() %></li>
                            <li class="list-group-item"><strong>Email:</strong> <%= emp.getEmail() %></li>
                            <li class="list-group-item"><strong>Position:</strong> <%= emp.getPosition() %></li>
                            <li class="list-group-item"><strong>Department:</strong> <%= emp.getDepartment() %></li>
                            <li class="list-group-item"><strong>Date of Birth:</strong> <%= emp.getDateOfBirth() %></li>
                            <li class="list-group-item"><strong>Gender:</strong> <%= emp.getGender() %></li>
                            <li class="list-group-item"><strong>Phone Number:</strong> <%= emp.getPhoneNumber() %></li>
                            <li class="list-group-item"><strong>Address:</strong> <%= emp.getAddress() %></li>
                            <li class="list-group-item"><strong>Status:</strong> <%= emp.getStatus() %></li>
                            <li class="list-group-item"><strong>Leave Balance:</strong> <%= emp.getLeaveBalance() %></li>
                        </ul>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <!-- Leave Details Card -->
                <div class="card leave">
                    <div class="card-header bg_main">
                        <h3 class="mb-0">Leave Details</h3>
                    </div>
                    <div class="card-body">
                        <!-- Display leave details here -->
                        <!-- You can use JSP to populate these values -->
                        <!-- Example: -->
                        <ul class="list-group">
                            <li class="list-group-item"><strong>Leave ID:</strong> <%= leave.getLeaveId() %></li>
                            <li class="list-group-item"><strong>Employee ID:</strong> <%= leave.getEmployeeId() %></li>
                            <li class="list-group-item"><strong>Leave Type:</strong> <%= leave.getLeaveType() %></li>
                            <li class="list-group-item"><strong>Start Date:</strong> <%= leave.getStartDate() %></li>
                            <li class="list-group-item"><strong>End Date:</strong> <%= leave.getEndDate() %></li>
                            <li class="list-group-item"><strong>Apply Date:</strong> <%= leave.getApplyDate() %></li>
                            <li class="list-group-item"><strong>Reason:</strong> <%= leave.getReason() %></li>
                            <li class="list-group-item"><strong>Status:</strong> <%= leave.getStatus() %></li>
                            
                       
                            <li class="list-group-item"><strong>Process By:</strong> <%= leave.getApprovalBy().equals("0") ? "--" : leave.getApprovalBy() %></li>
                            
                            <li class="list-group-item"><strong>Process Date:</strong> <%= leave.getApprovalDate()==null? "--" : leave.getApprovalDate() %> </li>
                            <li class="list-group-item"><strong>Duration:</strong> <%= leave.getDuration() %></li>
                        </ul>
                        
                    </div>
                    <div class="card-footer bg-transparent">
                        <!-- Buttons for accept and reject actions -->
                        <div class="text-center mt-4">
                                <% if (leave.getStatus().equalsIgnoreCase("Pending") && emp.getLeaveBalance() >= leave.getDuration()) { %>
							        <a id="acceptLeaveBtn" href="AcceptLeave?emp_id=<%= emp._id %>&leave_id=<%= leave.getLeaveId() %>" class="btn btn-success mr-3 bg_main accept-leave-btn"> Accept <i class="fa fa-check-circle" ></i></a>
							        <a id="rejectLeaveBtn" href="RejectLeaveRequest?emp_id=<%= emp._id %>&leave_id=<%= leave.getLeaveId() %>" class="btn btn-danger bg_main">Reject <i class="fa fa-times-circle"></i></a>
							        
							         <% } else if (!leave.getStatus().equalsIgnoreCase("Pending")) { %>
							        <!-- Display a message if leave status is not pending -->
							        <p class="text-danger">Leave request has already been processed.</p>
							    <% } else { %>
							        <!-- Display a message if leave balance is insufficient -->
							        <p class="text-danger">Insufficient leave balance to accept this leave request.</p>
							    <% } %>   
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

</div>
</div>

<script >

//Get the height of the employee card
var empCardHeight = document.querySelector('.card.emp').offsetHeight;

// Set the height of the leave card to match the height of the employee card
document.querySelector('.card.leave').style.height = empCardHeight + 'px';


</script>

</body>
<%
    }
%>
</html>


<%
	
    connection.close();
%>

