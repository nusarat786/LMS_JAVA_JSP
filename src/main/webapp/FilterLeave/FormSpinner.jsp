<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>

<style>

.bg_main-3{
			  background-color: rgb(49, 151, 149) !important;
			  color :RGB(255, 255, 255) !important;
			  font-family: "Segoe UI", Tahoma, Geneva, Verdana, sans-serif !important;
    		  font-size :2rem;
		}


</style>
<script type="text/javascript">

function showLoadingModal() {
  $('#loadingModal').modal('show');
}

function hideLoadingModal() {
  $('#loadingModal').modal('hide');
}

function validateDates() {
  var startDate = new Date(document.getElementById("startDate").value);
  var endDate = new Date(document.getElementById("endDate").value);
  
  if (startDate > endDate) {
    alert("Invalid entry: Start date must be less than or equal to end date.");
    return false;
  }

  showLoadingModal(); // Show loading modal when form is submitted
  return true;
}


function getSpinner(){
	showLoadingModal(); // Show loading modal when form is submitted	 
}
// Hide loading modal when page reloads or navigates back
window.onload = function() {
  hideLoadingModal();
}

// Hide loading modal when page reloads or navigates back
window.onload = function() {
  hideLoadingModal();
}

// Handle back button press
window.addEventListener('popstate', function(event) {
  hideLoadingModal();
});


// Handle back button press
window.addEventListener('pageshow', function(event) {
  // If there's a persisted state, it means the user is navigating back
  if (event.persisted) {
    hideLoadingModal();
  }
});


// Add an event listener to the anchor tag
document.addEventListener('DOMContentLoaded', function() {
  var acceptLeaveBtn = document.getElementById('acceptLeaveBtn');
  if (acceptLeaveBtn) {
    acceptLeaveBtn.addEventListener('click', function(event) {
      event.preventDefault(); // Prevent the default behavior of the anchor tag
      showLoadingModal(); // Show the loading modal
      window.location.href = this.getAttribute('href'); // Redirect to the link
    });
  }
});


//Add an event listener to the reject button
document.addEventListener('DOMContentLoaded', function() {
  var rejectLeaveBtn = document.getElementById('rejectLeaveBtn');
  if (rejectLeaveBtn) {
    rejectLeaveBtn.addEventListener('click', function(event) {
      event.preventDefault(); // Prevent the default behavior of the anchor tag
      showLoadingModal(); // Show the loading modal
      window.location.href = this.getAttribute('href'); // Redirect to the link
    });
  }
});


function confirmSubmission() {
    var confirmed = confirm("Are you sure you want to update all leave balances with the given input?");
    if (confirmed) {
        alert("Update process initiated. Click OK to proceed.");
        showLoadingModal(); // Show loading modal when form is submitted
        return true; // Proceed with form submission
    } else {
        alert("Process cancelled. Leave balances will not be updated.");
        return false; // Cancel form submission
    }
}


function confirmSubmission2() {
    var confirmed = confirm("Are you sure you want to update leave balances with the given input for emp?");
    if (confirmed) {
        alert("Update process initiated. Click OK to proceed.");
        showLoadingModal(); // Show loading modal when form is submitted
        return true; // Proceed with form submission
    } else {
        alert("Process cancelled. Leave balances will not be updated.");
        return false; // Cancel form submission
    }
}





</script>

</head>

<div class="modal fade" id="loadingModal" tabindex="-1" role="dialog" aria-labelledby="loadingModalLabel" aria-hidden="true" data-backdrop="static">
    <div class="modal-dialog modal-dialog-centered" role="document">
      <div class="modal-content">
        <div class="modal-body text-center bg_main-3">
          <div class="spinner-border " role="status">
            <span class="sr-only">Loading...</span>
          </div>
          <p class="mt-2">Please wait...</p>
        </div>
      </div>
    </div>
  </div>
  
  
</html>