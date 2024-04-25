<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@ page import="com.admin.userManagement.bean.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>


<%
	Employee emp = null;
	try{
    emp = EmployeeDao.getEmpById(Integer.parseInt(request.getParameter("emp_id")));
    
    if(emp==null){
    	throw new Exception("Emp With Id " + request.getParameter("emp_id") + " Can Not Be Found");
    }
	}catch(Exception ee) {
		System.out.println();
		ee.printStackTrace();
		EmployeeDao.errorPage(ee,request,response,"Server Error");
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
</head>

<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>
<body>
    <div class="container mt-5">
        <h2 class="mb-4">Edit Employee </h2>

        <form action="EditEmployServlet" method="post" enctype="multipart/form-data" >
            	
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
                <input type="date" class="form-control" id="dateOfBirth" name="dateOfBirth" value=<%= emp.getDateOfBirth() %>>
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
                <input type="tel" class="form-control" id="phoneNumber" name="phoneNumber" value=<%= emp.getPhoneNumber() %>>
            </div>

            <div class="mb-3">
			    <label for="address" class="form-label">Address:</label>
			    <textarea class="form-control" id="address" name="address"><%= emp.getAddress() %></textarea>
			</div>
            

            <div class="mb-3">
                <label for="status" class="form-label">Status:</label>
                <select class="form-select" id="status" name="status" value=<%= emp.getStatus() %>>
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>
            </div>

           
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>
    </div>

    <!-- Bootstrap JS and Popper.js -->
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
