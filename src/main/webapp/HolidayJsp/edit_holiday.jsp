<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%@ page import="com.admin.userManagement.bean.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.admin.userManagement.dao.*" %>

    
<%
	HoidayBean hld = new HoidayBean();
	

	Employee emp = new Employee();
	System.out.println("Test............" + request.getParameterMap().size());
	if (request.getParameterMap().size() <= 0) {
	    // If there are no parameters in the request, send an alert
	    out.println(EmployeeDao.sendAlert("Invalid Operation", "/LMS_V3/EmployDeatils2.jsp"));
		
	}else{
		hld = HolidayDao.getHolidayByDate(request.getParameter("holiday_date"));
	
	}

%>




<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Holiday</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
	<link rel="stylesheet" type="text/css" href="../banner.css">
	
	<style>
     .bg_main{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  
		}
  	</style>
</head>
<header>
	<jsp:include page="../Header/Haeder.jsp"></jsp:include>
</header>
<body>

<div class="container-fluid border-con p-4  col-11 "> 

<div class="row">
	<div class="container" style="margin-top: 1rem !important;">
	  <div class="row">
	    <div class="col-12">
	      <div class="banner-card">
	        <div class="welcome-msg-container">
	          <h2 class="h-4">Update Holiday</h2>
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
        
        <form class="mt-5 mb-5" action="${pageContext.request.contextPath}/HolidayServlet" method="post">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="holiday_date_to_up" value="<%= hld.getHolidayDate() %>">
            <div class="form-group">
                <label for="holiday_name">Holiday Name:</label>
                <input type="text" class="form-control" id="holiday_name" name="holiday_name" value="<%= hld.getHolidayName() %>" required>
            </div>
            <div class="form-group">
                <label for="holiday_date">Holiday Date:</label>
                <input type="date" class="form-control" id="holiday_date" name="holiday_date" value="<%= hld.getHolidayDate() %>" required>
            </div>
            <div class="form-group">
                <label for="holiday_description">Holiday Description:</label>
                <textarea class="form-control" id="holiday_description" name="holiday_description" rows="3" required><%= hld.getHolidayDescription() %></textarea>
            </div>
            <button type="submit" class="btn btn-block bg_main">Update</button>
        </form>
    </div>
    </div>
    </div>
    </div>
</body>
</html>
