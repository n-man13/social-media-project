<%@ page import="java.util.ArrayList"%> <%@ page import="java.util.List"%> <%@ page import="com.njit.smp.model.DirectMessage"%> <% List<DirectMessage>
  userMessages = null; if (request.getAttribute("messages") != null) { userMessages = (List<DirectMessage
    >) request.getAttribute("messages"); } %>

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
          <a href="">Home</a>
          <a href="">Link</a>
          <a href="">Inbox</a>
          <div class="right">
            <a href="user-profile.jsp" id="username_placeholder">Username_Placeholder</a>
            <a href="login.jsp">Logout</a>
          </div>
        </div>

        <!-- Main Content -->
        <div class="main-container">
          <div class="hobby-bar">
            <div class="search-bar">
              <form action="usersearch" method="post">
                <input type="text" name="searchbox" placeholder="Search..." id="searchbox" />
                <input type="submit" value="GO" id="search-submit" />
              </form>
            </div>
            <%
              if(userFullName != null) {
            %>
            <form action="messageuser" method="post">
              <input type="hidden" name="initload" value="initload" />
              <input type="hidden" name="username" value="<%=session.getAttribute("uname")%>">
              <input type="submit" value="<%=userFullName%>" id="userbutton" />
            </form>
            <%} %>
            <% if(!userPosts.isEmpty()) {
              for(UserMessage userPost: userPosts){
                %>
            <%}
            }%>
          </div>
          <div class="main-content">
            <div class="messages">
              <% if(!userMessages.isEmpty()) { for(DirectMessage userMessage: userMessages){ %>
              <!-- User Message\reply Template START -->
              <div class="user-reply user-message" id="messagetemplate">
                <h5><%=userPost.getFirstName()%> <%=userPost.getLastName() %></h5>
                <p><%=userPost.getMessage()%></p>
              </div>
              <%} }%>
              <!-- User Message\reply Template END -->
            </div>
            <div class="profile-post send-message">
              <form action="message" method="post" id="sendform">
                <textarea name="message-textbox" id="messagetextbox" cols="80" rows="8" placeholder="Send your message" required></textarea>
                <input type="submit" value="Send" id="send-message" />
              </form>
            </div>
          </div>
        </div>
      </body>
    </html> </UserMessage
></UserMessage>
