<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 
<%@ page import="java.sql.Blob" %>
<%@ page import="java.io.InputStream" %>
<%@ page import="java.io.ByteArrayOutputStream" %>
<%@ page import="java.util.Base64" %>
<%@ page import="com.admin.userManagement.bean.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>
<%@ page import="com.connection.*" %>
<%@ page import="java.sql.*" %>


<%

	Employee emp =  null;
	List<LeaveRequestBean> lreq = null;
	
	// Step 1: Establishing a Connection
	Connection_ con = new Connection_();
	Connection connection = con.getc();
	
	emp = EmployeeDao.getEmpByemailv2(connection,EmployeeDao.getCookieValue(request.getCookies(), "email"));
    lreq = LeaveRequestDao.getLeaveHistory();
	
    int accepted_request= 0;
    int rejected_request= 0;
    int pending_request= 0;
    
    for(var l: lreq){
    	if(l.getStatus().equals("ACCEPTED")) {
    		accepted_request++;
    	}
    	
    	if(l.getStatus().equals("REJECTED")) {
    		rejected_request++;
    	}
    	
    	if(l.getStatus().equals("PENDING")) {
    		pending_request++;
    	}
    	
    }
    
%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Portals</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
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
    .welcome-msg-container h4 {
      font-size: 24px; /* Adjust font size as needed */
    }

    /* Styling for employee card */
    .employee-card { 
      border-radius: 10px; /* Rounded borders */
      padding: 20px;
      text-align: center;
      margin-bottom: 20px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 200px; /* Set fixed height for all cards */
      box-shadow: 
        0px 6px 10px rgba(0,0,0,0), /* Main shadow */
        0px 1px 18px rgba(1, 1, 1, 0.6), /* Inset shadow */
        0px 3px 5px rgba(255, 255, 255, 0.4); /* Subtle bottom shadow */
        
    
      transition: all 0.3s ease-in-out; /* Add smooth hover effect */
      
    }
    
    .employee-card:hover{
    	background-color: #f0f0f0;
    }
    
    .employee-card-2 { 
      border-radius: 10px; /* Rounded borders */
      padding: 20px;
      text-align: center;
      margin-bottom: 20px;
      display: flex;
      flex-direction: column;
      justify-content: center;
      align-items: center;
      height: 200px; /* Set fixed height for all cards */
      transition: all 0.2s ease-in-out; /* Add smooth hover effect */
      
    }

    .employee-card:hover {
      transform: scale(1.02); /* Slight scale up on hover */
    }

    /* Profile image styles with shadow */
    .profile-image {
      width: 120px;
      height: 120px;
      border-radius: 50%;
      object-fit: cover;
      margin: auto;
      box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2); /* Add shadow */
    }

	/* Profile image styles with shadow */
	.profile-image-squre {
	  width: 120px;
	  height: 120px;
	  object-fit: cover;
	  margin: auto;
	  box-shadow: 0px 2px 5px rgba(0, 0, 0, 0.2); /* Add shadow */
	}
		

    /* Remove margin from employee names (added in previous responses) */
    .col-md-4 h5 {
      margin-bottom: 0;
    }
    
    .sh{
    	box-shadow: 
        0px 6px 10px rgba(0,0,0,0), /* Main shadow */
        0px 1px 18px rgba(1, 1, 1, 0.6), /* Inset shadow */
        0px 3px 5px rgba(255, 255, 255, 0.4); /* Subtle bottom shadow */
    
    }
    
    /* Add box shadow to row */
    
    /* Add rounded shadow to row */
    .row-with-rounded-shadow {
    
      border-radius: 10px; /* Rounded corners */
      
    }
    
     


    /* Responsive layout using Bootstrap grid system */
    @media (max-width: 768px) {
      .row {
        display: flex;
        flex-wrap: wrap; /* Wrap cards if needed */
      }

      .col-md-4 {
        flex: 1 0 100%; /* Allow cards to take full width when needed */
        margin-bottom: 10px; /* Adjust margin for better spacing */
      }
    }
    
    .v50{
    	width: 90%
    }
    
    
      #card-cursor:hover .employee-card {
    cursor: pointer;
    transition: all 0.3s ease-in-out; /* Add smooth hover effect */
      
  }
  </style>
</head>

<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>
<body>



<div class="container-fluid card border mb-5 v50">
<!-- Admin Portal Section -->
<div class="container mt-5">
  <div class="row">
    <div class="col-md-12">
      <div class="banner-card">
        <div class="welcome-msg-container">
          <h2>Welcome to Admin Portal</h2>
        </div>
        <div>
          <a href="https://imgbb.com/"><img src="https://i.ibb.co/bsT7Rs1/usher.png" alt="usher" class="banner-image"></a>
        </div>
      </div>
    </div>
  </div>
</div>


<!-- Employee Portal Section -->
<div class="container mt-5">
  <div class="row p-3">

    <div class="col-md-4" id="card1">
      <div class="employee-card">
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
                                
                                <h5 class="m-0">(<%=emp._id%>)</h5>
      
       
    </div>
    </div>

    <div class="col-md-4" id="card2">
      <div class="employee-card">
      	<h5 class="m-0"><%=emp.getFirstName() + " " + emp.getLastName() %></h5>
      </div>
    </div>

    <div class="col-md-4" id="card3">
      <div class="employee-card">
      	<h5 class="m-0"><%=emp.getPosition() %></h5>
      </div>
    </div>

  </div>
  
  <div class="row p-3 " >

    <div class="col-md-4" id="card1">
      <div class="employee-card">
      	<img  src="https://i.ibb.co/P4rLmPN/pngegg-4.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
        
      	<h5>Total Leave Requests (<%=lreq.size() %>) </h5>
      </div>
    </div>

    
    <div class="col-md-4" id="card-cursor" onclick="redirectToLink('/LMS_V3/FilterLeave/FilteredLeaveRequest.jsp?status=ACCEPTED')">
      <div class="employee-card">
      	<img  src="https://i.postimg.cc/MHKLRcjQ/approval.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
        <h5 class="m-0">Total Request Approved (<%=accepted_request %>)</h5>
      </div>
    </div>

    <div class="col-md-4" id="card-cursor" onclick="redirectToLink('/LMS_V3/FilterLeave/FilteredLeaveRequest.jsp?status=REJECTED')">
      <div class="employee-card">
      
        <img  src="https://i.ibb.co/DfBqK2V/reject.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
        <h5 class="m-0">Total Rejected Request (<%=rejected_request %>)</h5>
      
      </div>
    </div>
    			
    <div class="col-md-4" id="card-cursor" onclick="redirectToLink('/LMS_V3/FilterLeave/FilteredLeaveRequest.jsp?status=PENDING')">
 
	 <div class="employee-card">
        <img  src="https://i.postimg.cc/QMDHp5jC/deadline.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
        <h5 class="m-0">Pending request (<%=pending_request %>)</h5>
     </div>
    </div>
    
    
     <div class="col-md-4" id="card-cursor"  onclick="redirectToLink('/LMS_V3/AllLeaveRequest.jsp')">
      <div class="employee-card">
      	<img  src="https://i.ibb.co/Q9W4C3P/personal.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
      	<h5> Leave Requests </h5>
      </div>
     </div>
     
     <div class="col-md-4" id="card-cursor"  onclick="redirectToLink('/LMS_V3/add_employ.jsp')">
      <div class="employee-card">
      	<img  src="https://iili.io/JMdtz41.th.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
      	<h5> Add Employee </h5>
      </div>
     </div>
     
     <div class="col-md-4" id="card-cursor"  onclick="redirectToLink('/LMS_V3/EmployDeatils2.jsp')">
      <div class="employee-card">
      	<img  src="https://i.ibb.co/ph8Yw9x/note-book.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
      	<h5> Employee List </h5>
      </div>
     </div>
     
     <div class="col-md-4" id="card-cursor"  onclick="redirectToLink2('/LMS_V3/DateWiseLeaveRequest.jsp')">
      <div class="employee-card">
      	<img  src="https://i.postimg.cc/CKVC2zsn/user.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
      	<h5> Date Wise Leave History </h5>
      </div>
     </div>
     
     <div class="col-md-4" id="card-cursor"  onclick="redirectToLink('/LMS_V3/HolidayJsp/create_holidays.jsp')">
      <div class="employee-card">
      	<img  src="https://i.postimg.cc/CLhCs1VL/add.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
      	<h5> Create Holidays </h5>
      </div>
     </div>
     
     <div class="col-md-4" id="card-cursor"  onclick="redirectToLink('/LMS_V3/HolidayJsp/holidays.jsp')">
      <div class="employee-card">
      	<img  src="https://i.postimg.cc/52j0xc8m/holidays.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
      	<h5> Holidays List </h5>
      </div>
     </div>
     
     <div class="col-md-4" id="card-cursor"  onclick="redirectToLink('/LMS_V3/ResetLeave.jsp')">
      <div class="employee-card">
      	<img  src="https://i.postimg.cc/QM55V45C/reset.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
      	<h5> Reset Leave Balance </h5>
      </div>
     </div>
     
     <div class="col-md-4" id="card-cursor"  onclick="redirectToLink('/LMS_V3/ResetLeave_By_Id.jsp')">
      <div class="employee-card">
      	<img  src="https://i.postimg.cc/QM55V45C/reset.png" alt="external-applications-recruitment-agency-flaticons-flat-flat-icons" alt="Profile Picture" class="profile-image-squre"/>
      	<h5> Reset Leave Balance(EMP ID) </h5>
      </div>
     </div>
    
    

  </div>
  
	
</div>
</div>



<script type="text/javascript">
	
function redirectToLink(link) {
    window.location.href = link;
  }
</script>
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<script>
// Function to redirect to the specified link with today's date
function redirectToLink2(link) {

 	// Get today's date
    var today = new Date();

 // Format the date as "DD-MM-YYYY"
    var currentDate = ('0' + today.getDate()).slice(-2) + '-' + ('0' + (today.getMonth() + 1)).slice(-2) + '-' + today.getFullYear();

    // Construct the URL with the current date
    var url = link + '?date=' + currentDate;

    // Redirect to the new URL
    window.location.href = url;
}
</script>

</body>
</html>


<%
	connection.close() ;   
%>