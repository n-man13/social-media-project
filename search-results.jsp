<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!-- 
Author: Joseph Santantonio
Project: Social Media Project
Wrote HTML code for user-landing page
-->

<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.njit.smp.model.UserMessage"%>

<%
List<UserMessage> userPosts = null;

if (request.getAttribute("searchposts") != null) {
  userPosts = (List<UserMessage>) request.getAttribute("searchposts");
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
    <div class="navigation">
      <a href="user-landing.jsp">Home</a>
      <a href="">Link</a>
      <a href="">Link</a>
      <div class="right">
        <a href="user-profile.jsp" id="username_placeholder">Username_Placeholder</a>
        <a href="login.jsp">Logout</a>
      </div>
    </div>

    <!-- Main Content -->
    <div class="main-container">
      <div class="hobby-bar">
        <div class="search-bar">
          <form action="search" method="post">
            <input type="text" name="searchbox" placeholder="Search..." id="searchbox" />
            <input type="submit" value="GO" id="search-submit" />
          </form>
        </div>
        <%
        	if(userFullName != null) {
        %>
        	<div class="user" id="user-owner"><p><%=userFullName%></p></div>	
        <%} %>
        <% if(!userPosts.isEmpty()) {
          for(UserMessage userPost: userPosts){
            %>
        <%}
        }%>
      </div>
      <div class="main-content">
        <div class="user-posts userposts-mainpage">
        <% if(!userPosts.isEmpty()) {
        	for(UserMessage userPost: userPosts){
        		List<UserMessage> replies = userPost.getReplies();
        	%>
          <div class="post-content">
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
          <% } }%>
        </div>
      </div>
    </div>
  </body>
</html>