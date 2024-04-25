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
    Employee emp = (Employee)request.getAttribute("employee");
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Leave Request Form</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom CSS -->
  
  
  <link rel="stylesheet" type="text/css" href="banner.css">
  <style>
     .bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  btn-primary btn-block bg_main
		}
		
		.bg_main-3{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif !important;
    		  font-size :2rem;
		}
		
		
  </style>
</head>
<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>

<jsp:include page="./FilterLeave/FormSpinner.jsp"></jsp:include>

<!-- your existing body content -->
  <div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="loadingModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-body text-center bg_main-3">
          <div class="spinner-border " role="status">
            <span class="sr-only">Loading...</span>
          </div>
          <p class="mt-2">Please wait...</p>
        </div>
      </div>
    </div>
  </div>
  
  
<body>
<div class="container-fluid border-con p-4  col-11 "> 

<div class="row">
	<div class="container" style="margin-top: 1rem !important;">
	  <div class="row">
	    <div class="col-12">
	      <div class="banner-card">
	        <div class="welcome-msg-container">
	          <h2 class="h-4">Apply For Leave </h2>
	        </div>
	        <div>
	          <img src="https://i.postimg.cc/XJxYbzS6/apply.png" alt="usher" class="banner-image">
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</div>

 <div class="container border-con-2">
    <div class="row">
    	<div class="offset-1 col-10">
  <form class="mt-5 mb-5" action="ApplyLeave" method="post" onsubmit="return validateDates();">
  	
  	<input type="text" value=<%=emp._id%> name="emp_id" hidden>
    <div class="form-group">
      <label for="leaveType">Leave Type:</label>
      <select class="form-control" id="leaveType" name="leaveType" required>
        <option value="Casual">Casual</option>
        <option value="Annual">Annual Leave</option>
        <option value="Sick">Sick Leave</option>
        <option value="Ocation">Ocation</option>
        <!-- Add more options if needed -->
      </select>
    </div>
    
    <div class="form-group">
      <label for="startDate">Start Date:</label>
      <input type="date" class="form-control" id="startDate" name="startDate" min="<%=java.time.LocalDate.now()%>" required>
    </div>
    <div class="form-group">
      <label for="endDate">End Date:</label>
      <input type="date" class="form-control" id="endDate" name="endDate" min="<%=java.time.LocalDate.now()%>" required>
    </div>
    
    <div class="form-group">
      <label for="reason">Reason:</label>
      <textarea class="form-control" id="reason" name="reason" rows="3" required></textarea>
    </div>
    <button type="submit" class="btn btn-primary btn-block bg_main">Submit Request</button>
  </form>
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
