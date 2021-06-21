<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- 
Author: Joseph Santantonio
Project: Social Media Project
Wrote HTML code for signup page
-->

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
    </script>
  </head>
  <body onload="getInfo()">
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

    <div class="adminpage-container">
      <!-- div left contains the login page image -->
      <div class="search-bar" id="admin-search">
        <form action="admin" method="post">
          <input type="hidden" name="search" />
          <input type="text" class="search" name="searchbox" placeholder="Search for a User..." id="adminsearch-box" />
          <input type="submit" value="GO" id="search-submit" />
        </form>
      </div>
      <div class="admin-searchresult">
        <div class="user-adminsearch">
          <h5 id="post-owner"><%=user.getFirstName()%> <%=user.getLastName()%></h5>
        </div>
        <div class="banbutton">
          <form action="admin" method="post">
            <input type="hidden" name="ban" />
            <input type="submit" value="BAN" id="admin-submit" />
          </form>
        </div>
      </div>
      <button type="button"><p>CREATE USER ACCOUNT</p></button>
    </div>
  </body>
</html>
