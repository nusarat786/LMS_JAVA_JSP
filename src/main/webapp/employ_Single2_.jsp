<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.admin.userManagement.bean.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>




<%
	//Employee 
	//emp = null;
	
	Cookie[] cookies = request.getCookies();
	Employee emp = null;
	int emp_id = 0;
	try{
		
	
		if (cookies != null) {
		    for (Cookie cookie : cookies) {
		    	System.out.println(cookie.getName());
		        
		        if (cookie.getName().equals("empID")) {
		        	emp_id = Integer.parseInt(cookie.getValue());
		        }  
		    }
		}
		emp = EmployeeDao.getEmpById(emp_id);
		
 	}catch(Exception ee) {
		System.out.println();
		ee.printStackTrace();
		EmployeeDao.errorPage(ee,request,response,"Server Error");
	}
	
	
	
%>

<!DOCTYPE html>
<html>
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
            max-width: 20rem;
            border-radius: 50%;
        }
        
        .card {
            display: flex;
            flex-direction: column;
            height: 100%;
        }
    </style>
</head>

<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>

<body>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <h3 class="mb-0">User Profile</h3>
                    </div>
                    <div class="card-body">
                        <div class="row">
                            <div class="col-md-4 text-center">
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
                            <div class="col-md-8">
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
                </div>
            </div>
        </div>
    </div>
    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<div>
    <div class="container mt-5">
        <div class="row justify-content-center">
            <div class="col-md-6">
                <div class="card mb-3">
                    <div class="card-header">
                        <h3 class="mb-0">Photo and Name</h3>
                    </div>
                    <div class="card-body text-center">
                        <% Blob blob2 = emp.employeePhotoo; %>
                        <% if (blob2 != null) { %>
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
                        <h4 class="mt-3"><%= emp.getFirstName() %> <%= emp.getLastName() %></h4>
                    </div>
                </div>
            </div>
            <div class="col-md-6">
                <div class="card">
                    <div class="card-header">
                        <h3 class="mb-0">User Details</h3>
                    </div>
                    <div class="card-body">
                        <ul class="list-group">
                            <li class="list-group-item"><strong>ID:</strong> <%= emp._id %></li>
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
        </div>
    </div>
    <!-- Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</div>


</body>
</html>
    