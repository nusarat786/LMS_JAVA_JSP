<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.admin.userManagement.bean.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>


<%

	Employee emp = new Employee();
	System.out.println("Test............" + request.getParameterMap().size());
	if (request.getParameterMap().size() <= 0) {
	    // If there are no parameters in the request, send an alert
	    out.println(EmployeeDao.sendAlert("Invalid Operation", "/LMS_V3/EmployDeatils2.jsp"));
		
	}else{
		emp = EmployeeDao.getEmpById(Integer.parseInt(request.getParameter("emp_id")));
	}
	
%>
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
			  btn-primary btn-block bg_main
		}
  	</style>
	
</head>

<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>
<body>

<div class="container-fluid border-con p-4  col-11 "> 

<div class="row">
	<div class="container" style="margin-top: 1rem !important;">
	  <div class="row">
	    <div class="col-12">
	      <div class="banner-card">
	        <div class="welcome-msg-container">
	          <h2 class="h-4">Edit Employee</h2>
	        </div>
	        <div>
	          <img src="https://i.postimg.cc/sDrhGn0Q/user-profile-edition-icon-icons-com-71300-1.png" alt="usher" class="banner-image">
	        </div>
	      </div>
	    </div>
	  </div>
	</div>
</div>
    
 <div class="container border-con-2">
    <div class="row">
    	<div class="offset-1 col-10">
        <form class="mt-5 mb-5" action="EditEmployServlet2"  method="post" enctype="multipart/form-data" >
            	
            <input type="number" type="hidden" class="form-control" id="emp_id" name="emp_id" value=<%= emp._id %> hidden required>
            
            <div class="mb-3">
                <label for="firstName" class="form-label">First Name:</label>
                <input type="text" class="form-control" id="firstName" name="firstName" value=<%= emp.getFirstName() %> required>
            </div>

            <div class="mb-3">
                <label for="lastName" class="form-label">Last Name:</label>
                <input type="text" class="form-control" id="lastName" name="lastName" value=<%= emp.getLastName() %> required>
            </div>

            <div class="mb-3">
                <label for="email" class="form-label">Email:</label>
                <input type="email" class="form-control" id="email" name="email" value=<%= emp.getEmail() %> required>
            </div>

            <div class="mb-3">
                <label for="position" class="form-label">Position:</label>
                <input type="text" class="form-control" id="position" name="position" value=<%= emp.getPosition() %> required>
            </div>

            <div class="mb-3">
                <label for="department" class="form-label">Department:</label>
                <input type="text" class="form-control" id="department" name="department" value=<%= emp.getDepartment() %> required>
            </div>

            <div class="mb-3">
                <label for="dateOfBirth" class="form-label">Date of Birth:</label>
                <input required type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value=<%= emp.getDateOfBirth() %>>
            </div>

            <div class="mb-3">
                <label for="gender" class="form-label">Gender:</label>
                <select class="form-select" id="gender" name="gender" value=<%= emp.getGender() %>>
                    <option value="Male">Male</option>
                    <option value="Female">Female</option>
                    <option value="Other">Other</option>
                </select>
            </div>

            <div class="mb-3">
                <label for="phoneNumber" class="form-label">Phone Number:</label>
                <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" pattern="[0-9]{10}" title="Please enter a 10-digit phone number" required value=<%= emp.getPhoneNumber() %>>
            </div>

            <div class="mb-3">
			    <label for="address" class="form-label">Address:</label>
			    <textarea class="form-control" id="address" name="address" required><%= emp.getAddress() %></textarea>
			</div>
            

            <div class="mb-3">
                <label for="status" class="form-label">Status:</label>
                <select class="form-select" id="status" name="status" value=<%= emp.getStatus() %>>
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>
            </div>
            
            
            <div class="mb-3">
			    <label for="updatePhoto" class="form-label">Update Employee Photo:</label>
			    <input type="checkbox" id="updatePhoto" name="updatePhoto" onchange="togglePhotoInput()">
			</div>

			<div class="mb-3" id="photoInput" style="display: none;">
			    <label for="employeePhoto" class="form-label">Employee Photo:</label>
			    <input type="file" class="form-control" id="employeePhoto" name="employeePhoto" accept="image/*" >
			</div>
			
			<div class="mb-3">
			    <label for="grantAdmin" class="form-label">Grant Admin </label>
				<input type="checkbox" id="grantAdmin" name="grantAdmin" <% if (emp.getIsAdmin() == 1) { %> checked <% } %> >
							    
			</div>
            

            <button type="submit" class="btn  btn-block bg_main">Submit</button>
        </form>
    </div>
    </div>
    </div>
</div>
    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
	
	<script>
    function togglePhotoInput() {
        var updateCheckbox = document.getElementById('updatePhoto');
        var photoInput = document.getElementById('photoInput');

        if (updateCheckbox.checked) {
            photoInput.style.display = 'block';
        } else {
            photoInput.style.display = 'none';
        }
    }
    
	</script>

</body>
</html>
