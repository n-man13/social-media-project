<!-- 
Author: Joseph Santantonio
Project: Social Media Project
Wrote HTML code for login page
-->

<!--  -->

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hobby Home Log in</title>
    <link rel="stylesheet" href="styles.css" />
    <script src="error_handling.js"></script>
  </head>

  <body onload="pageLoad()">
    <!-- Header -->
    <div class="header_container">
      <h1 class="hobby">HOBBY</h1>
      <div class="header_image">
        <img src="images/hobby_icon.png" alt="" />
      </div>
      <h1>HOMES</h1>
    </div>

    <div class="title"><h2>Sign In</h2></div>
    <div id="errormsg">Error message</div>

    <!-- Container for Log In -->
    <div class="login_container">
      <!-- div left contains the login page image -->
      <div class="left"></div>
      <!-- div right contains the actual log in form -->
      <div class="right">
        <div class="formBox">
          <form action="login" method="post">
            <input type="text" name="username" placeholder="Username" id="username" required />
            <input type="password" name="password" placeholder="Password" id="password" required />
            <!-- TEMPORARY TEST, SIGN IN = user-landing.html -->
            <input type="submit" value="Sign in" id="sign-in" />
            <a href="signup.jsp">
              <input type="button" value="Sign up" id="sign-up" />
            </a>
            <a href="UserSignInServlet"><p>Forgot Password</p></a>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>