<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="com.admin.userManagement.bean.Employee" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>

<%
    // Import necessary classes
    
    // Logic to retrieve employee list directly in JSP
    List<Employee> employeeList = EmployeeDao.selectAllUsers();
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Employee Information</title>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" integrity="sha512-1ycn6IcaQQ40/MKBW2W4Rhis/DbILU74C1vSrLJxCq57o941Ym01SwNsOMqvEBFlcgUa6xLiPY/NS5R+E6ztJQ==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    
    
     <style>
        /* Table styles */
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            fontt-family:
        }

        th, td {
            padding: 8px;
            text-align: left;
            border-bottom: 1px solid #ddd;
            white-space: nowrap;
            border:non
        }

        th {
            background-color: #f2f2f2;
            font-weight: bold;
        }
        
        td {
		    overflow: hidden;
		    text-overflow: ellipsis; /* Show ellipsis (...) for overflow text */
		}

        /* Edit icon style */
        .edit-icon {
            color: blue;
        }

        .edit-icon:hover {
            color: darkblue;
            cursor: pointer;
        }
        
        table{
        	border: none;
        }
        
        
    </style>
</head>

<header>
	<jsp:include page="./Header/Haeder.jsp"></jsp:include>
</header>

<body>
    
    <div class="container">
        <h1 class="text-center">Employee Information</h1>

        <div class="table-responsive">
            <table class="table table-bordered">
                <thead class="table-light">
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
                        <th>Employee Photo</th>
                        <th>Leave Balance</th>
                        <th>Edit</th>
                    </tr>
                </thead>
                <tbody>
                    <% for (Employee emp : employeeList) { %>
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
                        <td><%= emp.employeePhotoo %></td>
                        <td><%= emp.getLeaveBalance() %></td>
                        <td>  <a href="EditEmploy.jsp?emp_id=${employee.employeeID}" class="fas fa-edit edit-icon"></a> </td>
                        
                        <!-- Add more columns if needed -->
                    </tr>
                    <% } %>
                </tbody>
            </table>
        </div>
    </div>

</body>
</html>
