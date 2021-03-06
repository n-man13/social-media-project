<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%> <% String username = (String) session.getAttribute("uname"); %>
<!-- 
Author: Joseph Santantonio
Project: Social Media Project
-->

<!-- Java Collaboration: Atharv -->
<%@ page import="java.util.ArrayList"%> <%@ page import="java.util.List"%> <%@ page import="com.njit.smp.model.BoredItem"%> <% BoredItem activity = null; if (request.getAttribute("activity") != null)
{ activity = (BoredItem) request.getAttribute("activity"); } %>

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
        <a href="explore.jsp" id="exploreform">
          <input type="submit" value="Explore" id="main-button" />
        </a>
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
        <div class="api-answer" id="api-answer">
          <% if(activity != null) {
          		if (activity.getActivity() == "error") { %>
          			<p>No hobby idea was found with those search parameters. Please try again.</p>
          	 <% }
          		else { %>
          			<p><%=activity.getActivity()%></p>
          			<div class="activitystats">
		            <div class="pricediv">
		              <%if(activity.getPrice() == 0) {%>
		              <div class="price">
		                <h3 id="price">Price:</h3>
		                <p id="on"></p>
		                <p id="off">$$$$</p>
		              </div>
		              <%} else if (activity.getPrice() <= 0.25) {%>
		              <div class="price">
		                <h3 id="price">Price:</h3>
		                <p id="on">$</p>
		                <p id="off">$$$</p>
		              </div>
		              <%} else if (activity.getPrice() <= 0.5) {%>
		              <div class="price">
		                <h3 id="price">Price:</h3>
		                <p id="on">$$</p>
		                <p id="off">$$</p>
		              </div>
		              <%} else if (activity.getPrice() <= 0.75) {%>
		              <div class="price">
		                <h3 id="price">Price:</h3>
		                <p id="on">$$$</p>
		                <p id="off">$</p>
		              </div>
		              <%} else {%>
		              <div class="price">
		                <h3 id="price">Price:</h3>
		                <p id="on">$$$$</p>
		                <p id="off"></p>
		              </div>
		              <%}%>
		            </div>
		            <div class="accessdiv">
		              <%if(activity.getAccessibility() == 0) {%>
		              <div class="access">
		                <h3 id="access">Accessibility:</h3>
		                <p id="on">***</p>
		                <p id="off"></p>
		              </div>
		              <%} else if (activity.getAccessibility() <= 0.5) {%>
		              <div class="access">
		                <h3 id="access">Accessibility:</h3>
		                <p id="on">**</p>
		                <p id="off">*</p>
		              </div>
		              <%} else {%>
		              <div class="access">
		                <h3 id="access">Accessibility:</h3>
		                <p id="on">*</p>
		                <p id="off">**</p>
		              </div>
		              <%}%>
		            </div>
		          </div>
          	 <% } %>
          <% } else{ %>
          <p>Generate a hobby idea below!</p>
          <% }%>
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
                    <option value="random" selected>Random</option>
                    <option value="easy">Easy</option>
                    <option value="medium">Medium</option>
                    <option value="hard">Hard</option>
                  </select>
                </div>
                <div class="optional-form">
                  <label for="cost">Cost</label>
                  <select name="cost" id="cost">
                    <option value="random" selected>Random</option>
                    <option value="free">Free</option>
                    <option value="low">Low</option>
                    <option value="moderate">Moderate</option>
                    <option value="high">High</option>
                    <option value="expensive">Expensive</option>
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