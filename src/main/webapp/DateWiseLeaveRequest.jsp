<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.admin.userManagement.bean.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>

<%
	int emp_id = 1;
	
    //List<LeaveRequestBean> lreq = LeaveRequestDao.getLeaveHistoryByEmployeeId(emp_id);

    var date = request.getParameter("date");
    var employeeList=EmployeeDao.selectEmpReqJoinDate(request.getParameter("date"));
   	var date2 = EmployeeDao.convertDateFormat(date);
   	System.out.println();
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
    
    .banner-card2 {
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
        
        box-shadow: rgba(0, 0, 0, 0.05) 0px 0px 0px 1px, rgb(209, 213, 219) 0px 0px 0px 1px inset;
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
	
	body {
      font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif !important;
    }

    .login-container {
      margin: auto;
      padding: 10px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      background-color: rgba(255, 255, 255, 0.9);
    }

    .loading-overlay {
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.5);
      display: none;
      align-items: center;
      justify-content: center;
    }

    .loading-spinner {
      border: 5px solid rgba(255, 255, 255, 0.3);
      border-top: 5px solid #fff;
      border-radius: 50%;
      width: 50px;
      height: 50px;
      animation: spin 1s linear infinite;
    }
    
    .spinner-border {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}
    

    @keyframes spin {
      0% { transform: rotate(0deg); }
      100% { transform: rotate(360deg); }
    }
    
    .bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  btn-primary btn-block bg_main
		}
		
		
		input{
			width:50% !important;
			margin-left:25%;
			font-size:2.2rem !important;
			color:rgb(49, 151, 149) !important;
		}
		
		.b{
		margin-top: 1rem !important;
		}
		
		/* Media query for mobile screens */
		@media (max-width: 576px) {
		    input {
		        width: 100% !important;
		        margin-left: 0; /* Reset margin for mobile screens */
		        font-size: 2rem !important;
		    }
		}
		
				/* Media query for tablet screens */
		@media (min-width: 576px) and (max-width: 992px) {
		    input {
		        width: 70% !important;
		        margin-left: 15%; /* Adjust margin for tablet screens */
		        font-size: 2rem !important;
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
	          <h2 class="h-4">Leave Requests By Date </h2>
	        </div>
	        <div>
	          <img src="https://i.ibb.co/P4rLmPN/pngegg-4.png" alt="usher" class="banner-image">
	        </div>
	      </div>
	    </div>
	  </div>
	</div>


	<div class="container-fluid " style="margin-top: 1rem !important;">
	  <div class="row">
	    <div class="col-md-12">
	      <div class="banner-card2">
	        <div class="welcome-msg-container">
	        <form id="dateForm">
    <input type="date" id="selectedDate" class="form-control" required value="<%= date2 %>" onchange="validateDate()">
    <div id="dateError" style="color: red; display: none;">Please select a valid date.</div>
    <input type="button" id="sub" class="btn btn-primary btn-block bg_main b" value="Submit" onclick="submitForm()">
</form>


	        
	        </div>
	        
	      </div>
	    </div>
	  </div>
	</div>


    
    <br>
    <br>
    
    <div class="container">
        <div class="table-responsive text-nowrap">
            <table id="dtBasicExample" class="table table-hover align-middle mb-0 bg-white" cellspacing="0" width="100%">
    <thead>
    <tr class="bg_main">
        <th class="sorting desc">Apply Date</th>       
        <th>Employee ID</th>
        <th>First Name</th>
        <th>Last Name</th>
        <th>Duration</th>
        <th>Leave ID</th>       
        <th>Leave Type</th>
        <th>Start Date</th>
        <th>End Date</th>
        <th>Reason</th>
        <th>Status</th>
        <th>Process By</th>
        <th>Process Date</th>
        <!-- Additional Columns -->
        
        <th>Email</th>
        <th>Position</th>
        <th>Department</th>
        <th>Date of Birth</th>
        <th>Gender</th>
        <th>Phone Number</th>
        <th>Address</th>
        <th>Estatus</th>
        <th>Leave Balance</th>
        
    </tr>
</thead>
<tbody>
    <% for (var empLeave : employeeList) { %>
    <tr>
        <td><%= empLeave.getApplyDate() %></td>
        <td><%= empLeave.getEmployeeId() %></td>
        <td><%= empLeave.getFirstName() %></td>
        <td><%= empLeave.getLastName() %></td>
        <td><%= empLeave.getDuration() %></td>
        <td><%= empLeave.getLeaveId() %></td>                
        <td><%= empLeave.getLeaveType() %></td>
        <td><%= empLeave.getStartDate() %></td>
        <td><%= empLeave.getEndDate() %></td>
        <td><%= empLeave.getReason() %></td>
        <td><%= empLeave.getLstatus() %></td>
        <td><%= empLeave.getApprovalBy().equals("0") ? "--" : empLeave.getApprovalBy() %></td>
        <td><%= empLeave.getApprovalDate() == null ? "--" : empLeave.getApprovalDate() %></td>
        <!-- Additional Columns -->
        
        <td><%= empLeave.getEmail() %></td>
        <td><%= empLeave.getPosition() %></td>
        <td><%= empLeave.getDepartment() %></td>
        <td><%= empLeave.getDateOfBirth() %></td>
        <td><%= empLeave.getGender() %></td>
        <td><%= empLeave.getPhoneNumber() %></td>
        <td><%= empLeave.getAddress() %></td>
        <td><%= empLeave.getEstatus() %></td>
        <td><%= empLeave.getLeaveBalance() %></td>
        
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
    
<!-- JavaScript -->


<script>
    function validateDate() {
        var selectedDate = document.getElementById('selectedDate').value;
     
        if (selectedDate) {
            return true;
        } else{
        	return false;
        }
    }

    function submitForm() {
        if (validateDate()) {
        	var selectedDate = document.getElementById('selectedDate').value;
            var parts = selectedDate.split("-");
            var formattedDate = parts[2] + "-" + parts[1] + "-" + parts[0];
            window.location.href = 'DateWiseLeaveRequest.jsp?date=' + formattedDate;
        
        	
            } else {
            
            	alert('Please enter a valid date in the format dd-mm-yyyy.');
                
        }
    }
    
    
    function formatDate(inputDate) {
        var parts = inputDate.split("-");
        return parts[2] + "-" + parts[1] + "-" + parts[0];
    }

</script>
    
    
</body>
</html>