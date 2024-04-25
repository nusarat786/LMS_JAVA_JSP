<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.admin.userManagement.bean.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>


<%
	
	//EmployeeDao.giveSpinner(response);	

	


	Employee emp = (Employee)request.getAttribute("employee");
    List<LeaveRequestBean> lreq = LeaveRequestDao.getLeaveHistoryByEmployeeId(emp._id);
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Information</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  	
  	  <!-- Bootstrap CSS -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.7/css/bootstrap.min.css" rel="stylesheet">
  <!-- Font Awesome CSS (for edit icon) -->
  <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
  <!-- DataTables CSS -->
  <link href="https://cdn.datatables.net/1.11.5/css/dataTables.bootstrap4.min.css" rel="stylesheet">
    
  	<script src="https://code.jquery.com/jquery-3.5.1.min.js"></script>
	<script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
	<script src="https://cdn.datatables.net/select/1.3.4/js/dataTables.select.min.js"></script>
	<script>
	    
	$(document).ready(function () {
		  $('#dtBasicExample').DataTable({
		    "order": [[ 0, "desc" ]]
		  });
		    $('.dataTables_length').addClass('bs-select');
		});
	    
	    
	</script>
	
	<style type="text/css">
		body {
		  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif !important;
		}
		
		.bg_main ,option{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  
		}
		
		.pagination>.active>a, .pagination>.active>a:focus, .pagination>.active>a:hover, .pagination>.active>span, .pagination>.active>span:focus, .pagination>.active>span:hover{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  
		}
		
		.pagination>li>a, .pagination>li>span{
			color:black;
		}
		
		.form-control:focus {
		  border-color: rgb(49, 151, 149) !important;
		 }
		 
		 .font-ivory{
		 	color: rgb(49, 151, 149) !important;
		 	font-size:2rem;
		 }
		 
		 .edit-icon:hover{
		 	color: rgb(100, 159, 200) !important;
		 	
		 }
		 
		 
		 
		 /* Styling for banner card */
    .banner-card {
      border: 2px solid #ccc;
      border-radius: 10px;
      padding: 20px;
      text-align: center;
      margin-bottom: 20px;
      display: flex;
      justify-content: space-between; /* Arrange content with space between */
      align-items: center; /* Center content vertically */
      box-shadow: 
        0px 6px 10px rgba(49, 151, 149, 0.8), /* Main shadow */
        0px 1px 18px rgba(49, 151, 149, 0.6), /* Inset shadow */
        0px 3px 5px rgba(49, 151, 149, 0.4); /* Subtle bottom shadow */
    }
    
    

    /* Styling for banner image */
    .banner-image {
      max-width: 45%; /* Adjust width of image */
      height: auto;
      border-radius: 10px;
    }

    /* Styling for welcome message */
    .welcome-msg-container {
      flex: 1; /* Allow message to grow and occupy remaining space */
    }

    /* Increase font size of welcome message */
    .welcome-msg-container ,.h-4 {
      font-size: 3.5rem; /* Adjust font size as needed */
    }
		 
	.border-con{
		border: 2px solid #ccc;
      	border-radius: 10px;
      	margin:1rem;
	}
	
	@media (max-width: 576px) {
	  .welcome-msg-container, .h-4 {
	    font-size: 1.8rem; /* Set font size to 1rem for mobile devices */
	  }
	}
	</style>
  	
    
     
</head>
<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>
<body>


<div class="container-fluid border-con"> 
	<div class="container-fluid " style="margin-top: 1rem !important;">
	  <div class="row">
	    <div class="col-md-12">
	      <div class="banner-card">
	        <div class="welcome-msg-container">
	          <h2 class="h-4">Your All Leave Requests </h2>
	        </div>
	        <div>
	          <img src="https://i.ibb.co/P4rLmPN/pngegg-4.png" alt="usher" class="banner-image">
	        </div>
	      </div>
	    </div>
	  </div>
	</div>

	
    
    <div class="container">
        <div class="table-responsive text-nowrap">
            <table id="dtBasicExample" class="table table-hover align-middle mb-0 bg-white" cellspacing="0" width="100%">
    <thead>
        <tr class="bg_main">
            <th  class="sorting desc">Apply Date</th>
            <th>Leave ID</th>
            <th>Employee ID</th>
            <th>Duration</th>
            <th>Leave Type</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Reason</th>
            <th>Status</th>
            <th>Process By</th>
            <th>Process Date</th>
            
        </tr>
    </thead>
    <tbody>
        <% for (var leave : lreq) { %>
        <tr>
        	<td><%= leave.getApplyDate()%></td>
            <td><%= leave.getLeaveId() %></td>
            <td><%= leave.getEmployeeId() %></td>
            <td><%= leave.getDuration() %></td>
            <td><%= leave.getLeaveType() %></td>
            <td><%= leave.getStartDate() %></td>
            <td><%= leave.getEndDate() %></td>
            <td><%= leave.getReason() %></td>
            <td><%= leave.getStatus() %></td>
            <td><%= leave.getApprovalBy().equals("0") ? "--" : leave.getApprovalBy() %></td>
            <td><%= leave.getApprovalDate()==null? "--" : leave.getApprovalDate() %></td>                                   
        </tr>
        <% } %>
    </tbody>
</table>
            
        </div>
    </div>
 
<div>   
</div>
</div>   
  <!-- Add jQuery -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <!-- Add Bootstrap JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.5.7/js/bootstrap.bundle.min.js"></script>
    <!-- Add DataTables JS -->
    <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.11.5/js/dataTables.bootstrap4.min.js"></script>
    
    
    
</body>
</html>

<%
	
	EmployeeDao.removeSpinner(response);	

	
%>