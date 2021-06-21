<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 
Author: Joseph Santantonio
Project: Social Media Project
-->

<!-- Java Collaboration: Atharv -->
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.njit.smp.model.UserMessage"%>

<%
List<UserMessage> userPosts = null;
String postRedirectId = null;

if (request.getAttribute("posts") != null) {
  userPosts = (List<UserMessage>) request.getAttribute("posts");
}

if (request.getAttribute("postRedirectId") != null) {
	postRedirectId = (String) request.getAttribute("postRedirectId");
	System.out.println("postRedirectId = " + postRedirectId);
}
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Videogames</title>
    <link rel="stylesheet" href="styles.css" />    
    <script>
      function getInfo() {
        var a_value = document.getElementById("username_placeholder");
        var first = '<%= session.getAttribute("fname") %>';
        var last = '<%= session.getAttribute("lname") %>';
        a_value.innerHTML = first + ", " + last;
      }

      function scroll() {
    	<% if (request.getAttribute("postRedirectId") != null) { %>
	    	document.getElementById(<%=postRedirectId%>).parentElement.scrollIntoView({
	            behavior:"smooth",
	            inline:"center"
	          });
    	<% } %>
      }
    </script>
  </head>
  <body onload="getInfo();scroll();">
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
          <input type="hidden" name="pagename" value="videogames">
          <input type="submit" value="Video Games" id="main-button" />
        </form>
        <form action="status" method="post" id="musicform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="music">
          <input type="submit" value="Music" id="main-button" />
        </form>
        <form action="status" method="post" id="artsncraftsform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="artsncrafts">
          <input type="submit" value="Arts & Crafts" id="main-button" />
        </form>
        <form action="status" method="post" id="technologyform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="technology">
          <input type="submit" value="Technology" id="main-button" />
        </form>
        <form action="status" method="post" id="sportsform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="sports">
          <input type="submit" value="Sports" id="main-button" />
        </form>
      </div>
      <div class="main-content">
        <div class="page-name">
          <h1>VIDEOGAMES</h1>
        </div>
        <div class="profile-post">
          <label for="userpage-textbox"><h2>Post your status!</h2></label>
          <form action="status" method="post">
            <textarea name="userpagetextbox" id="userpagetextbox" cols="60" rows="4" placeholder="Let the world know..." required></textarea>
            <input type="hidden" name="username" value="<%=session.getAttribute("uname")%>">
            <input type="hidden" name="pagename" value="videogames">
            <button type="submit" id="postbutton">Post<br> Status</button>
            <!-- <input type="submit" value="Post Status" /> -->
          </form>
        </div>
        <div class="user-posts userposts-mainpage">
        <% if(!userPosts.isEmpty()) {
        	for(UserMessage userPost: userPosts){
        		List<UserMessage> replies = userPost.getReplies();
        	%>
          <div class="post-content">
            <!-- hidden id for scrolling to post ID -->
            <input type="hidden" name="<%=postRedirectId%>" id="<%=postRedirectId%>"/>
            <h5 id="post-owner"><%=userPost.getFirstName()%> <%=userPost.getLastName() %></h5>
            <p>
              <%=userPost.getPostContent() %>
            </p>
            <hr />
            Replies to this post:
            <% if(!replies.isEmpty()) {
            	for(UserMessage reply: replies){ 
            	%>
            	<div class="replies">
              		<div class="user-reply">
                		<h5><%=reply.getFirstName()%> <%=reply.getLastName() %></h5>
                		<p><%=reply.getPostContent() %></p>
              		</div>
            	</div>
            <% } }%>
            <form action="status" method="post">
              <input type="hidden" name="postId" value="<%=userPost.getPostId() %>">
              <textarea name="replytextbox" id="replytextbox" cols="80" rows="2" placeholder="Reply to this post" required></textarea>
              <input type="hidden" name="username" value="<%=session.getAttribute("uname")%>">
              <input type="submit" value="Reply" id="post-response" />
            </form>
          </div>
          <% } }%>
        </div>
      </div>
    </div>
  </body>
</html>