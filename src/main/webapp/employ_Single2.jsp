<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
	// Step 1: Establishing a Connection
	Connection_ con = new Connection_();
	Connection connection = con.getc();
    Employee emp = EmployeeDao.getEmpByemailv2(connection,EmployeeDao.getCookieValue(request.getCookies(), "email"));

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
            max-width: 15rem;
            border-radius: 50%;
        }
        
        .card {
            display: flex;
            flex-direction: column;
            height: 100%;
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
	
	
	@media (max-width: 765px) {
    .m-b {
        
        margin-bottom: 2rem !important;
    }
}



	
	
    </style>
    
    <link rel="stylesheet" type="text/css" href="banner.css">
	
	<style>
     .bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  btn-primary btn-block bg_main
		}
  	</style>
</head>

<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>

<body>
<div class="m-2"> 


<div class="container  border-con mt-2 mb-4">

<div class="row">
	<div class="container" style="margin-top: 1rem !important;">
	  <div class="row">
	    <div class="col-12">
	      <div class="banner-card">
	        <div class="welcome-msg-container">
	          <h2 class="h-4">Profile</h2>
	        </div>
	        <div>
	          <img src="https://i.ibb.co/cXNxJHm/prof-3.png" alt="usher" class="banner-image">
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</div>

<div class="row m-10">
    <div class="container mt-5 mb-4">
        <div class="row justify-content-center ">
            <div class="col-md-6 m-b">
                <div class="card mb-3 leave">
    <div class="card-header bg_main">
        <h3 class="mb-0">Photo and Name</h3>
    </div>
    <div class="card-body d-flex flex-column align-items-center justify-content-center">
        <% Blob blob2 = emp.employeePhotoo; %>
        <% if (blob2 != null) { %>
            <% InputStream inputStream = blob2.getBinaryStream(); %>
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
                <div class="card leave">
                    <div class="card-header bg_main ">
                        <h3 class="mb-0">User Details</h3>
                    </div>
                    <div class="card-body">
                        <ol class="list-group">
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
                        </ol>
                    </div>
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

</body>
</html>


<%
	connection.close();
	
%>
    