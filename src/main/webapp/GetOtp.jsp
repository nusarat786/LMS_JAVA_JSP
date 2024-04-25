<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
    
    .bg-main{
		background-color: rgb(49, 151, 149) !important;
        color: rgb(255, 255, 255) !important;    	
    }

    .login-container {
      max-width: 400px;
      margin: auto;
      padding: 20px;
      border-radius: 10px;
      box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
      background-color: rgba(255, 255, 255, 0.9);
    }

   

 
    

    
    .bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  btn-primary btn-block bg_main
		}
		
	.bg_main2{
			  
			  color :rgb(49, 151, 149) !important;
			  btn-primary btn-block bg_main
		}
		
		.bg_main2{
			  
			  color :black !important;
			  btn-primary btn-block bg_main
		}
  </style>
  
  <link rel="stylesheet" type="text/css" href="banner.css">
	

</head>
<body>

<jsp:include page="./FilterLeave/FormSpinner.jsp"></jsp:include>

<div class="container-fluid border-con p-4  col-11 "> 

<div class="row">
	<div class="container" >
	  <div class="row">
	    <div class="col-12">
	      <div class="banner-card">
	        <div class="Login">
	          <h2 class="h-4">Please Login</h2>
	        </div>
	        <div>
	          <img src="https://iili.io/JMnlQxj.md.png" alt="usher" class="banner-image">
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</div>

<div class="container border-con-2">
  <div class="row">
    <div class="col-lg-10 col-md-10 col-sm-10 col-xs-10 offset-lg-1 offset-md-1 offset-sm-1 offset-xs-1">
    
      <div class="login-container mt-5 mb-5">
        
        <form class="" id="loginForm" action="GetOtp" method="post" onsubmit="getSpinner()">
          <div class="mb-3">
            <label for="email" class="form-label">Email:</label>
            <input type="email" class="form-control" id="email" name="email" required>
          </div>
          <button type="submit" class="btn btn-primary btn-block bg-main">Send OTP</button>
          <div id="spinner" class="spinner-border text-primary bg_main2" role="status" style="display:none;">
            <span class="visually-hidden">Loading...</span>
          </div>
        </form>
      </div>
    </div>
  </div>
</div>

</div>

<script>
  function showSpinner() {
    // Set flag indicating form submission
    sessionStorage.setItem('isFormSubmitted', 'true');
    document.getElementById("spinner").style.display = "inline-block";
    // Clear flag after 2 seconds (adjust as needed)
    setTimeout(function () {
      sessionStorage.removeItem('isFormSubmitted');
    }, 2000);
  }
</script>

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
