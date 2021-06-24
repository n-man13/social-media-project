<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!-- 
Author: Joseph Santantonio
Project: Social Media Project
-->

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User-profile</title>
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
        <a href="user-landing.jsp" id="nav-text">Home</a>
        <a href="user-inbox.jsp" id="nav-text">Inbox</a>
      </div>
      <div class="right" id="right-nav">
        <a href="user-profile.jsp" id="username_placeholder">Username_Placeholder</a>
        <a href="login.jsp" id="nav-text">Logout</a>
      </div>
    </div>

    <!-- Main Content -->
    <div class="main-container">
      <div class="hobby-bar">
        <div class="search-bar">
          <form action="search" method="post">
            <input type="text" name="searchbox" placeholder="Search..." id="search-box" />
            <input type="submit" value="GO" id="search-submit" />
          </form>
        </div>
        <a href="explore.jsp" id="exploreform">
          <input type="submit" value="Explore" id="main-button" />
        </a>
        <form action="status" method="post" id="videogameform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="videogames" />
          <input type="submit" value="Video Games" id="main-button" />
        </form>
        <form action="status" method="post" id="musicform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="music" />
          <input type="submit" value="Music" id="main-button" />
        </form>
        <form action="status" method="post" id="artsncraftsform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="artsncrafts" />
          <input type="submit" value="Arts & Crafts" id="main-button" />
        </form>
        <form action="status" method="post" id="technologyform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="technology" />
          <input type="submit" value="Technology" id="main-button" />
        </form>
        <form action="status" method="post" id="sportsform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="sports" />
          <input type="submit" value="Sports" id="main-button" />
        </form>
      </div>
      <div class="main-content">
        <h1 id="profileheader">WELCOME HOME</h1>
      </div>
    </div>
  </body>
</html>