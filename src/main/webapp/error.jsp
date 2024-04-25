<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <!-- Add Bootstrap CSS link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <style>
        body {
            font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif !important;
            color: #333;
            margin: 50px;
        }
        
        .error-container {
            background-color: #fff;
            padding: 20px;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            display: inline-block;
        }
        
        h1 {
            color: #e74c3c;
        }
        
        .bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  btn-primary btn-block bg_main
		}
		
		.bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  btn-primary btn-block bg_main
		}
    </style>
    
    <link rel="stylesheet" type="text/css" href="banner.css">
	
</head>
<body>
<div class="container-fluid border-con p-4  col-11 "> 



<div class="row">
	<div class="container" >
	  <div class="row">
	    <div class="col-12">
	      <div class="banner-card">
	        <div class="Login">
	          <h2 class="h-4"> Error Occurred </h2>
	        </div>
	        <div>
	          <img src="https://i.ibb.co/JyKjZ3p/warning.png" alt="usher" class="banner-image">
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
        
        <div onsubmit="showSpinner()">
	          <h3 class="alert p-0">${err}  </h3>
	          <p class="alert alert-danger">${errorMessage}</p>
	          <!-- Add the back button -->
	          <button type="button" class="btn bg_main"  onclick="goBack()"> Go back</button>
          	  
	    </div>
      </div>
    </div>
  </div>
</div>




    
</div>
    <!-- Add Bootstrap JS and Popper.js scripts if needed -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>

    <!-- JavaScript function to go back to the previous page -->
    <script>
        function goBack() {
            window.history.back();
        }
    </script>
</body>
</html>
