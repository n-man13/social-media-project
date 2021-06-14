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
            <input type="text" class="search" name="searchbox" placeholder="Search..." id="search-box" />
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
        <h2 id="explore-text">Are you bored and uninspired? <br />Generate a hobby idea!</h2>
        <div class="api-answer">
          <p>Generate a task below!</p>
        </div>
        <div class="categories">
          <form action="activity" method="post" id="apiform">
            <div class="radio-buttons">
              <div class="rbtn">
                <input type="radio" name="choice" value="random" id="random" checked="checked" />
                <label for="random">Random</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="education" id="education" />
                <label for="education">Education</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="recreation" id="recreation" />
                <label for="recreation">Recreation</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="social" id="social" />
                <label for="social">Social</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="diy" id="diy" />
                <label for="diy">DIY</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="charity" id="charity" />
                <label for="charity">Charity</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="cooking" id="cooking" />
                <label for="cooking">Cooking</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="relaxation" id="relaxation" />
                <label for="relaxation">Relaxation</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="music" id="music" />
                <label for="music">Music</label>
              </div>
              <div class="rbtn">
                <input type="radio" name="choice" value="busywork" id="busywork" />
                <label for="busywork">Busywork</label>
              </div>
            </div>
            <div class="form-right">
              <div class="optional-category">
                <div class="optional-form">
                  <label for="difficulty">Diffuclty</label>
                  <select name="difficulty" id="difficulty">
                    <option selected value>Random</option>
                    <option value="0.0">Easy</option>
                    <option value="0.5">Medium</option>
                    <option value="1.0">Hard</option>
                  </select>
                </div>
                <div class="optional-form">
                  <label for="cost">Cost</label>
                  <select name="cost" id="cost">
                    <option selected value>Random</option>
                    <option value="0.0">Free</option>
                    <option value="0.25">Low</option>
                    <option value="0.5">Moderate</option>
                    <option value="0.75">High</option>
                    <option value="1.0">Expensive</option>
                  </select>
                </div>
                <div class="optional-form">
                  <label for="people"
                    >Number of<br />
                    participants</label
                  >
                  <input type="number" name="people" id="people" value="1" min="1" />
                </div>
              </div>
              <input type="submit" value="GENERATE" id="generatebutton" />
            </div>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>
