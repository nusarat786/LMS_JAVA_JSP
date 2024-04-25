<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.admin.userManagement.bean.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>

<%
	
	boolean isresent = false;

	isresent = EmployeeDao.getCookieValue(request.getCookies(),"isresent") != null ? true : false;
	System.out.println("Test................" + isresent);
%>

<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0
    response.setHeader("Expires", "0"); // Proxies
    
%>

<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login Page</title>
  <!-- Bootstrap CSS -->
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <!-- Custom CSS -->
  <style>
  
  
   
    
    body {
      font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif !important;
    }

    .login-container {
      max-width: 400px;
      margin: auto;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      background-color: rgba(255, 255, 255, 0.9);
    }

    
}
    

   
    
    .bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  btn-primary btn-block bg_main
		}
		
		.bg_main2{
			  
			  color :black !important;
			  btn-primary btn-block bg_main
		}
  </style>
  
  <link rel="stylesheet" type="text/css" href="banner.css">

	
</head>
<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>
<body>

<jsp:include page="./FilterLeave/FormSpinner.jsp"></jsp:include>

<div class="container-fluid border-con p-4  col-11 "> 

<div class="row">
	<div class="container" >
	  <div class="row">
	    <div class="col-12">
	      <div class="banner-card">
	        <div class="Login">
	          <h2 class="h-4">Reset All Employee Leave Balanace</h2>
	        </div>
	        <div>
	          <img src="https://i.postimg.cc/QM55V45C/reset.png" alt="usher" class="banner-image">
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</div>

<div class="container">
  <div class="row">
    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 offset-lg-1 offset-md-1 offset-sm-1 offset-xs-1">
      <div class="login-container">
        <form id="loginForm" class="" action="LeaveReset" method="get" onsubmit="return confirmSubmission();" >
          <div class="mb-3">
            <label for="" class="form-label">Leave Balance:</label>
            <input type="number" class="form-control" id="balance" name="balance" min="5" max="45"  required>
          </div>
          
          
        
          <button type="submit" class="btn  btn-block bg_main">Update All</button>
          <div id="spinner" class="spinner-border text-primary bg_main2"  role="status" style="display:none;">
            <span class="visually-hidden">processing....</span>
          </div>
        </form>
        
        
      </div>
    </div>
  </div>
</div>


</div>


<script>
  // Function to reload the page
  function refreshPage() {
    location.reload(true);
  }

  // Attach event listener to the pageshow event
  window.addEventListener('pageshow', function(event) {
    // If the persisted property is true or the navigation type is back_forward
    if (event.persisted || (event && event.persisted)) {
      // Reload the page
      refreshPage();
    }
  });
</script>







<!-- Bootstrap JS -->
<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>



</body>
</html>
