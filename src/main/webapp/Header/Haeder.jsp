<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>


<%
    Cookie[] cookies = request.getCookies();
    boolean isAuthenticated = false;

    // Check if the 'auth' cookie is present and set to 'true'
    if (cookies != null) {
        for (Cookie cookie : cookies) {
            if (cookie.getName().equals("auth") && cookie.getValue().equals("true")) {
                isAuthenticated = true;
                break;
            }
        }
    }
    
%>

<!DOCTYPE html>
<!-- Created By CodingNepal - www.codingnepalweb.com -->
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Responsive Navbar | CodingNepal</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css"/>

    <style>
        * {
            padding: 0;
            margin: 0;
            text-decoration: none;
            list-style: none;
            box-sizing: border-box;
        }

        .bodio {
            font-family: montserrat;
            margin-bottom: 9.5rem; /* Add bottom margin */
        }

        nav {
            background-color: rgb(49, 151, 149) !important;
            color: RGB(255, 255, 255) !important;
            height: 80px;
            width: 100%;
            position: fixed;
            top: 0;
            left: 0;
            z-index: 1000; /* Ensure it's above other content */
            margin-bottom: 1rem; /* Add bottom margin */
        }

        label.logo {
            color: white;
            font-size: 35px;
            line-height: 80px;
            padding: 0 100px;
            font-weight: bold;
        }

        nav ul {
            float: right;
            margin-right: 20px;
        }

        nav ul li {
            display: inline-block;
            line-height: 80px;
            margin: 0 5px;
        }

        nav ul li a {
            color: white;
            font-size: 17px;
            padding: 7px 13px;
            border-radius: 3px;
            text-transform: uppercase;
        }

        a.active, a:hover {
            background-color: rgb(90, 151, 149) !important;
            color: RGB(255, 255, 255) !important;

            transition: .5s;
        }

        .checkbtn {
            font-size: 30px;
            color: white;
            float: right;
            line-height: 80px;
            margin-right: 40px;
            cursor: pointer;
            display: none;
        }

        #check {
            display: none;
        }

        @media (max-width: 952px) {
            label.logo {
                font-size: 30px;
                padding-left: 50px;
            }

            nav ul li a {
                font-size: 16px;
            }
        }

        @media (max-width: 858px) {
            .checkbtn {
                display: block;
            }

            ul {
                position: fixed;
                width: 100%;
                height: 100vh;
                background-color:rgb(49, 151, 149) !important ; 
			   
                top: 80px;
                left: -100%;
                text-align: center;
                transition: all .5s;
            }

            nav ul li {
                display: block;
                margin: 50px 0;
                line-height: 30px;
            }

            nav ul li a {
                font-size: 20px;
            }

            a:hover, a.active {
                background: none;
                color: #0082e6;
            }

            #check:checked ~ ul {
                left: 0;
            }
        }

        section {
            background: url(bg1.jpg) no-repeat;
            background-size: cover;
            height: calc(100vh - 80px);
        }
        
        
        
    </style>
    
    <script>
    document.getElementById("logoutButton").addEventListener("click", function() {
        // Send a POST request to the logout servlet
        fetch('logout', {
            method: 'POST',
            credentials: 'same-origin' // Include cookies in the request
        }).then(function(response) {
            // Handle the response if needed
            window.location.href = "/login.jsp"; // Redirect to login page after logout
        }).catch(function(error) {
            console.error('Error:', error);
        });
    });
</script>
</head>

<div class="bodio">
    <nav>
        <input type="checkbox" id="check">
        <label for="check" class="checkbtn">
            <i class="fas fa-bars"></i>
        </label>
        <label class="logo">LMS-V1</label>
        
        <ul>
            <li><a href="/LMS_V3/EmployPortal.jsp">Employee Portal</a></li>
            <li><a href="/LMS_V3/AdminPortal.jsp">Admin Portal</a></li>
            
            <% if (!isAuthenticated) { %>
		        <li><a href="/LMS_V3/GetOtp.jsp">Login</a></li>
		    <% } %>
		    
            <li><a href="/LMS_V3/Logout">Logout</a></li>
        
        </ul>
        
    </nav>
    
</div>



</html>
