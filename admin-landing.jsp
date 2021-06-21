<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- 
Author: Joseph Santantonio
Project: Social Media Project
Wrote HTML code for signup page
-->

<%@ page import="com.njit.smp.model.User"%> 

<%
User user = null;
int success = -1;

if (request.getAttribute("result") != null) {
	user = (User) request.getAttribute("result");
}

if (request.getAttribute("success") != null) {
	success = (int) request.getAttribute("success");
}
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Admin Page</title>
    <link rel="stylesheet" href="styles.css" />
    <script>
      function getInfo() {
        var a_value = document.getElementById("username_placeholder");
        var first = '<%= session.getAttribute("fname") %>';
        var last = '<%= session.getAttribute("lname") %>';
        a_value.innerHTML = first + ", " + last;
      }

      function sleep(ms) {
        return new Promise((resolve) => setTimeout(resolve, ms));
      }

      async function banned() {
        var myDiv = document.getElementById("errormsg");

        const codes = {
          5: "This user account has been de-activated.",
          6: "This user account has been activated.",
          7: "There was an issue with de-activating this user.",

        };
		<% if (success > -1) {
	         if (success == 0) { %>
	              errorCode = 5;
	        <% } 
	           else if (success == 1) { %>
	              errorCode = 6;
	        <% }
	           else { %>
	           	  errorCode = 7;
	        <% } %>
	        myDiv.innerHTML = codes[errorCode];
	        myDiv.style.display = "block";
	        await sleep(3000);
	        myDiv.style.display = "none";
	    <% } %>
      }
    </script>
  </head>
  <body onload="getInfo();banned();">
    <!-- Header  -->
    <div class="header_container">
      <h1 class="hobby">HOBBY</h1>
      <div class="header_image">
        <img src="images/hobby_icon.png" alt="" />
      </div>
      <h1>HOMES</h1>
    </div>

    <!-- Navigation -->
    <div class="nav">
      <div class="left" id="left-nav">
        <a href="user-landing.jsp" id="nav-text">Home</a>
        <a href="user-inbox.jsp" id="nav-text">Inbox</a>
      </div>
      <div class="right" id="right-nav">
        <a href="user-profile.jsp" id="username_placeholder">Username_Placeholder</a>
        <a href="login.jsp" id="nav-text">Logout</a>
      </div>
    </div>

    <!-- Admin Splash -->
    <div class="admin-splash">
      <h2>ADMINISTRATION PAGE</h2>
    </div>

    <div id="errormsg">Error message</div>

    <div class="adminpage-container">
      <!-- div left contains the login page image -->
      <div class="search-bar" id="admin-search">
        <form action="admin" method="post">
          <input type="hidden" name="search" />
          <input type="text" class="search" name="searchbox" placeholder="Search for a User..." id="adminsearch-box" />
          <input type="submit" value="GO" id="search-submit" />
        </form>
      </div>
      <% if (user != null) { 
    	 	String val = "ACTIVATE";
    	 	System.out.println(user.isActive());
      	 	if (user.isActive()) {
      	 		val = "DE-ACTIVATE";
      	 	}
      %>
	      <div class="admin-searchresult">
	        <div class="user-adminsearch">
	          <h5 id="post-owner"><%=user.getUsername()%></h5>
	        </div>
	        <div class="banbutton">
	          <form action="admin" method="post">
	            <input type="hidden" name="action" value=<%=val%> />
	            <input type="hidden" name="username" value="<%=user.getUsername()%>" />
	            <input type="submit" value=<%=val%> id="admin-submit" />
	          </form>
	        </div>
	      </div>
	  <% } %>
      <button type="button"><p>CREATE USER ACCOUNT</p></button>
    </div>
  </body>
</html>