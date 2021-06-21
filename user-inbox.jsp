<!-- 
Author: Joseph Santantonio
Project: Social Media Project
-->

<!-- Java Collaboration: Atharv -->
<%@ page import="java.util.ArrayList"%>
<%@ page import="java.util.List"%>
<%@ page import="com.njit.smp.model.DirectMessage"%>

<% List<DirectMessage> userMessages = null;
  String userFullName = null;

if (request.getAttribute("messages") != null) {
  userMessages = (List<DirectMessage>) request.getAttribute("messages");
}

if (request.getAttribute("fullname") != null) {
    userFullName = (String) request.getAttribute("fullname");
}

if (request.getAttribute("otherperson") != null) {
    userFullName = (String) request.getAttribute("otherperson");
}

if (session.getAttribute("messaging") != null) {
	userFullName = (String) session.getAttribute("messaging");
}
%>

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User-inbox</title>
    <link rel="stylesheet" href="styles.css" />
    <script>
      function getInfo() {
        var a_value = document.getElementById("username_placeholder");
        var first = '<%= session.getAttribute("fname") %>';
        var last = '<%= session.getAttribute("lname") %>';
        a_value.innerHTML = first + ", " + last;
      }
      
      //await(5000);
    function submitForm(){
    	if(document.forms['openmessages']){
      		document.forms['openmessages'].submit();
      	}
    }
      
    function autoReload(){
    	setTimeout("submitForm()", 10000);
    }

    </script>
  </head>
  <body onload="getInfo();autoReload();">
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
        <form action="message" method="post">
          <input type="hidden" name="resetfullname" value="resetfullname" />
          <input type="submit" value="Home" id="reset-messages" />
        </form>
        <!-- <a href="user-landing.jsp">Home</a> -->
        <a href="user-inbox.jsp" id="nav-text">Inbox</a>
      </div>
      <div class="right" id="right-nav">
        <a href="user-profile.jsp" id="username_placeholder">Username_Placeholder</a>
        <form action="login" method="post">
          <input type="hidden" name="resetfullname" value="resetfullname" />
          <input type="submit" value="Logout" id="reset-messages" />
        </form>
        <!-- a href="login.jsp">Logout</a-->
      </div>
    </div>

    <!-- Main Content -->
    <div class="main-container">
      <div class="hobby-bar">
        <div class="search-bar">
          <form action="message" method="post">
            <input type="text" name="searchbox" placeholder="Search..." id="search-box" />
            <input type="submit" value="GO" id="search-submit" />
          </form>
        </div>
        <%
          if(userFullName != null || session.getAttribute("messaging") != null) {
        %>
        <form action="message" name="openmessages" method="post">
          <input type="hidden" name="messageuser" value="messageuser" />
          <input type="hidden" name="username" value="<%=session.getAttribute("uname")%>">
          <input type="hidden" name="messageto" value="<%=userFullName%>">
          <input type="submit" value="<%=userFullName%>" id="userbutton" />
        </form>
        <% } %>
      </div>
      <div class="main-content">
        <div class="messages">
          <% if(userMessages != null && !userMessages.isEmpty()) {
        	  userMessages = (List<DirectMessage>) request.getAttribute("messages");
            for(DirectMessage userMessage: userMessages){
            	System.out.println("message = "+userMessage.getMessage()+" user then otheruser = "+userMessage.getUsername()+" "+userMessage.getOtherUser());
          %>
          <!-- User Message\reply Template START -->
          <% if(userMessage.getUsername() == session.getAttribute("uname")) {%>
         <div class="user-reply user-message" id="messagetemplate">
           <h5><%=userMessage.getUsername()%></h5>
            <p><%=userMessage.getMessage()%></p>
          </div>
          <%} else { %>
            <div class="user-reply user-message" id="othermessagetemplate">
              <div class="innermsg">
                <h5><%=userMessage.getUsername()%></h5>
                 <p><%=userMessage.getMessage()%></p>
              </div>
             </div>
          <% } %>
          <%} }%>
          <!-- User Message\reply Template END -->
        </div>
        <div class="profile-post send-message">
          <form action="message" method="post" id="sendform">
          	<input type="hidden" name="sendmessage" value="sendmessage">
          	<input type="hidden" name="username" value="<%=session.getAttribute("uname")%>">
          	<input type="hidden" name="messageto" value="<%=userFullName%>" id="userbutton" />
            <textarea name="messagetextbox" id="messagetextbox" cols="60" rows="4" placeholder="Send your message" required></textarea>
            <input type="submit" value="Send" id="send-message" />
          </form>
        </div>
      </div>
    </div>
  </body>
</html> </UserMessage
></UserMessage>