<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
        
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Information Form</title>

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
	<link rel="stylesheet" type="text/css" href="banner.css">

	<style>
     .bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  
		}
  </style>
</head>
<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>

<jsp:include page="./FilterLeave/FormSpinner.jsp"></jsp:include>

<body>



<div class="container-fluid border-con p-4  col-11 "> 

<div class="row">
	<div class="container" style="margin-top: 1rem !important;">
	  <div class="row">
	    <div class="col-12">
	      <div class="banner-card">
	        <div class="welcome-msg-container">
	          <h2 class="h-4">Add Employ</h2>
	        </div>
	        <div>
	          <img src="https://i.postimg.cc/J4JrFdh0/add-modeless-quantity-record-512.png" alt="usher" class="banner-image">
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</div>

 <div class="container border-con-2">
    <div class="row">
    	<div class="offset-1 col-10">
    		<form class="mt-5 mb-5" action="EmployeeServlet" method="post" enctype="multipart/form-data" onsubmit="getSpinner()" >
            <div class="mb-3">
                <label for="firstName" class="form-label">First Name:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" required>
            </div>

            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" required>
            </div>

            <div class="mb-3">
                <label for="position" class="form-label">Position:</label>
                <input type="text" class="form-control" id="position" name="position" required>
            </div>

            <div class="mb-3">
                <label for="department" class="form-label">Department:</label>
                <input type="text" class="form-control" id="department" name="department" required>
            </div>

            <div class="mb-3">
                <label for="dateOfBirth" class="form-label">Date of Birth:</label>
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" required>
            </div>

            <div class="mb-3">
                <label for="gender" class="form-label" required>Gender:</label>
                <select class="form-select" id="gender" name="gender">
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>

           <div class="mb-3">
			    <label for="phoneNumber" class="form-label" required>Phone Number:</label>
			    <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" pattern="[0-9]{10}" title="Please enter a 10-digit phone number" required>
		   </div>
           

            <div class="mb-3">
                <label for="address" class="form-label" >Address:</label>
                <textarea class="form-control" id="address" name="address" required></textarea>
            </div>

            <div class="mb-3">
                <label for="status" class="form-label" required>Status:</label>
                <select class="form-select" id="status" name="status">
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="employeePhoto" class="form-label" >Employee Photo:</label>
                <input type="file" class="form-control" id="employeePhoto" name="employeePhoto" accept="image/*" required>
                
             </div>

            <button type="submit" class=" btn btn-block bg_main">Submit</button>
            
        </form>
    		
    	</div>
    </div>
 	</div>
 	</div>
 
    
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>

<br>
<br>


</body>


</html>
