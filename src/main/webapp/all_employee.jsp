<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.admin.userManagement.bean.Employee" %>
<%@ page import="java.util.List" %>

<!DOCTYPE html>
<html>

<html>
<head>
    <meta charset="UTF-8">
    <title>All Employees</title>
    <!-- Add Bootstrap CSS link -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
</head>
<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>
<body>
    <div class="container mt-5">
        <h2>All Employees</h2>
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Employee ID</th>
                    <th>First Name</th>
                    <th>Last Name</th>
                    <th>Email</th>
                    <th>Position</th>
                    <th>Department</th>
                    <th>Date of Birth</th>
                    <th>Gender</th>
                    <th>Phone Number</th>
                    <th>Address</th>
                    <th>Status</th>
                    <th>Leave Balance</th>
                    <!-- Add more columns if needed -->
                </tr>
            </thead>
            <tbody>
                <% for (Employee emp : (List<Employee>)request.getAttribute("employeeList")) { %>
                    <tr>
                        <td><%= emp._id %></td>
                        <td><%= emp.getFirstName() %></td>
                        <td><%= emp.getLastName() %></td>
                        <td><%= emp.getEmail() %></td>
                        <td><%= emp.getPosition() %></td>
                        <td><%= emp.getDepartment() %></td>
                        <td><%= emp.getDateOfBirth() %></td>
                        <td><%= emp.getGender() %></td>
                        <td><%= emp.getPhoneNumber() %></td>
                        <td><%= emp.getAddress() %></td>
                        <td><%= emp.getStatus() %></td>
                        <td><%= emp.getLeaveBalance() %></td>
                        <!-- Add more columns if needed -->
                    </tr>
                <% } %>
            </tbody>
        </table>
    </div>
    <!-- Add Bootstrap JS and Popper.js scripts if needed -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
</body>
</html>