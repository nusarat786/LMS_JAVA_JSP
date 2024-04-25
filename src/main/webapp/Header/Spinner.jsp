<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Loader Spinner Example</title>
  <!-- Bootstrap CSS -->
  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet">
  <style>
    /* Loader Styles */
    .loader-container {
      display: flex;
      justify-content: center;
      align-items: center;
      position: fixed;
      top: 0;
      left: 0;
      width: 100%;
      height: 100%;
      background-color: rgba(0, 0, 0, 0.5); /* Transparent background */
      z-index: 9999; /* Ensure it's above other content */
    }

    .loader {
      border: 8px solid #f3f3f3; /* Light grey */
      border-top: 8px solid #3498db; /* Blue */
      border-radius: 50%;
      width: 50px;
      height: 50px;
      animation: spin 1s linear infinite; /* Spinner animation */
    }

    @keyframes spin {
      0% { transform: rotate(0deg); }
      100% { transform: rotate(360deg); }
    }

    /* Hide loader when content is loaded */
    .loaded #loader {
      display: none;
    }
  </style>
</head>
<body>
  <!-- Loader Spinner Component -->
  <div id="loader" class="loader-container">
    <div class="loader"></div>
  </div>

 
  <!-- Bootstrap JS (optional) -->
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  <!-- JavaScript to hide loader when the page is fully loaded -->
  <script>
    window.onload = function() {
      var loader = document.getElementById('loader');
      loader.classList.add('loaded');
    };
  </script>
</body>
</html>
    