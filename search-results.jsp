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
String userFullName = null;

if (request.getAttribute("searchposts") != null) {
  userPosts = (List<UserMessage>) request.getAttribute("searchposts");
}

if (request.getAttribute("fullname") != null) {
  userFullName = (String) request.getAttribute("fullname");
}
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Search Results</title>
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
      </div>
      <div class="main-content">
        <div class="user-posts userposts-mainpage">
        <% if(!userPosts.isEmpty()) {
        	for(UserMessage userPost: userPosts){
        		List<UserMessage> replies = userPost.getReplies();
        	%>
          <div class="post-content">
              <form action="status" method="post" id="postlink">
                <input type="hidden" name="redirect" id="redirect"/>
                <input type="hidden" name="postRedirectId" id="postRedirectId" value="<%=userPost.getPostId()%>"/>
                <input type="submit" value="<%=userPost.getFirstName()%> <%=userPost.getLastName() %>" id="userlink">
              </form>
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
              <textarea name="replytextbox" id="replytextbox" cols="80" rows="4" placeholder="Reply to this post" required></textarea>
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