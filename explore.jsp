<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> <% String username = (String) session.getAttribute("uname"); %>
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
    <title>Explore</title>
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
        <form action="status" method="post" id="exploreform">
          <input type="hidden" name="initload" value="initload" />
          <input type="submit" value="Explore" id="main-button" />
        </form>
        <form action="status" method="post" id="videogameform">
          <input type="hidden" name="initload" value="initload" />
          <input type="submit" value="Video Games" id="main-button" />
        </form>
        <input type="submit" value="Music" id="main-button" />
        <input type="submit" value="Arts & Crafts" id="main-button" />
        <input type="submit" value="Technology" id="main-button" />
        <input type="submit" value="Sports" id="main-button" />
      </div>
      <div class="main-content">
        <h2 id="explore-text">Are you bored and uninspired? Choose a category below for a random generated hobby idea!</h2>
        <div class="api-answer">
          <p>Task from API goes here</p>
        </div>
        <div class="randomdiv">
          <form action="status" method="post" id="random">
            <input type="hidden" name="initload" value="initload" />
            <input type="submit" value="Random" id="main-button" />
          </form>
        </div>
        <div class="categories">
          <div class="row">
            <form action="status" method="post" id="education">
              <input type="hidden" name="initload" value="initload" />
              <input type="submit" value="education" id="main-button" />
            </form>
            <form action="status" method="post" id="recreation">
              <input type="hidden" name="initload" value="initload" />
              <input type="submit" value="recration" id="main-button" />
            </form>
            <form action="status" method="post" id="social">
              <input type="hidden" name="initload" value="initload" />
              <input type="submit" value="social" id="main-button" />
            </form>
          </div>
          <div class="row">
            <form action="status" method="post" id="diy">
              <input type="hidden" name="diy" value="initload" />
              <input type="submit" value="Random" id="main-button" />
            </form>
            <form action="status" method="post" id="charity">
              <input type="hidden" name="initload" value="initload" />
              <input type="submit" value="charity" id="main-button" />
            </form>
            <form action="status" method="post" id="cooking">
              <input type="hidden" name="initload" value="initload" />
              <input type="submit" value="cooking" id="main-button" />
            </form>
          </div>
          <div class="row">
            <form action="status" method="post" id="relaxation">
              <input type="hidden" name="initload" value="initload" />
              <input type="submit" value="relaxation" id="main-button" />
            </form>
            <form action="status" method="post" id="music">
              <input type="hidden" name="initload" value="initload" />
              <input type="submit" value="music" id="main-button" />
            </form>
            <form action="status" method="post" id="busywork">
              <input type="hidden" name="initload" value="initload" />
              <input type="submit" value="busywork" id="main-button" />
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
