<!DOCTYPE html>


<%
    String banner_heading = request.getParameter("param1");
    String image_url = request.getParameter("param2");
%>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Portal</title>
  <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Styling for banner card */
    .banner-card {
      border: 2px solid #ccc;
      border-radius: 10px;
      padding: 20px;
      text-align: center;
      margin-bottom: 20px;
      display: flex;
      justify-content: space-between; /* Arrange content with space between */
      align-items: center; /* Center content vertically */
      box-shadow: 
        0px 6px 10px rgba(49, 151, 149, 0.8), /* Main shadow */
        0px 1px 18px rgba(49, 151, 149, 0.6), /* Inset shadow */
        0px 3px 5px rgba(49, 151, 149, 0.4); /* Subtle bottom shadow */
    }

    /* Styling for banner image */
    .banner-image {
      max-width: 45%; /* Adjust width of image */
      height: auto;
      border-radius: 10px;
    }

    /* Styling for welcome message */
    .welcome-msg-container {
      flex: 1; /* Allow message to grow and occupy remaining space */
    }

    /* Increase font size of welcome message */
    .welcome-msg-container h4 {
      font-size: 24px; /* Adjust font size as needed */
    }
  </style>
</head>
<body>

<div class="container mt-5">
  <div class="row">
    <div class="col-md-12">
      <div class="banner-card">
        <div class="welcome-msg-container">
          <h2><%=banner_heading %></h2>
        </div>
        <div>
          <a href="https://imgbb.com/"><img src=<%=image_url %> alt="usher" class="banner-image"></a>
        </div>
      </div>
    </div>
  </div>
</div>

<script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>
