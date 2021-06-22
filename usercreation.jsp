<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 
Author: Joseph Santantonio
Project: Social Media Project
+Wrote Script for CheckPassword
-->

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User Creation</title>
    <link rel="stylesheet" href="styles.css" />
    <script src="signup.js"></script>
    <script>
        function getInfo() {
          var a_value = document.getElementById("username_placeholder");
          var first = '<%= session.getAttribute("fname") %>';
          var last = '<%= session.getAttribute("lname") %>';
          a_value.innerHTML = first + ", " + last;
        }
      </script>
  </head>
  
  <body onload="pageLoad();getInfo();">
    <!-- Header -->
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
          <a href="admin-landing.jsp" id="nav-text">Home</a>
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

    <div class="title"><h2>USER CREATION</h2></div>
    <div id="errormsg">Error message</div>
    

    <!-- Container for Log In -->
    <div class="admincreate_container">
      <!-- div left contains the login page image -->
      <div class="left"></div>
      <!-- div right contains the actual log in form -->
      <div class="right">
        <div class="formBox-signup">
          <form action="signup" method="post" onsubmit="return checkPassword(this)">
            <input type="text" name="firstname" placeholder="First Name" id="firstname" title="Letters only" required pattern="[a-zA-Z]+" />
            <p class="req">* Required</p>
            <input type="text" name="lastname" placeholder="Last Name" id="lastname" title="Letters only" required pattern="[a-zA-Z]+" />
            <p class="req">* Required</p>
            <input type="text" name="email" placeholder="E-mail" id="email" title="Please enter a valid email. &#013Example: JohnSmith@gmail.com" required pattern="[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+"/>
            <p class="req">* Required</p>
            <input type="text" name="username" placeholder="Username" id="username" required />
            <p class="req">* Required</p>
            <input type="password" name="password1" placeholder="Password" id="password1" required />
            <p class="req">* Required</p>
            <input type="password" name="password2" placeholder="Re-enter Password" id="password2" required />
            <p class="req">* Required</p>
            <input type="submit" value="Create Account" id="create-acc" />
            <a href="admin-landing.jsp">
              <input type="button" value="Back to Admin" id="sign-in" />
            </a>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>