<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
    <div class="navigation">
      <a href="">Home</a>
      <a href="">Link</a>
      <a href="">Link</a>
      <div class="right">
        <a href="">Username_Placeholder</a>
        <a href="login.jsp">Logout</a>
      </div>
    </div>

    <!-- Admin Splash -->
    <div class="admin-splash">
      <h2>ADMINISTRATION PAGE</h2>
    </div>

    <div class="adminpage-container">
      <!-- div left contains the login page image -->
      <button type="button"><p>User Statistics</p></button>
      <button type="button"><p>User lookup</p></button>
      <button type="button"><p>Administrative tools</p></button>
      <button type="button"><p>Banned Users</p></button>
    </div>
  </body>
</html>