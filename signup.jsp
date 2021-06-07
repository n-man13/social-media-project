<!-- 
Author: Joseph Santantonio
Project: Social Media Project
Wrote HTML code for signup page
Wrote Script for CheckPassword
-->

<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>Hobby Home Sign Up</title>
    <link rel="stylesheet" href="styles.css" />
    <script src="signup.js"></script>
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

    <div class="title"><h2>Sign Up Today</h2></div>
    <div id="errormsg">Error message</div>

    <!-- Container for Log In -->
    <div class="login_container">
      <!-- div left contains the login page image -->
      <div class="left"></div>
      <!-- div right contains the actual log in form -->
      <div class="right">
        <div class="formBox-signup">
          <form action="signup" method="post" onsubmit="return checkPassword(this)">
            <input type="text" name="firstname" placeholder="First Name" id="firstname" title="Letters only" required pattern="[a-zA-Z]+" />
            <p class="req">* Required</p>
            <input type="text" name="lastname" placeholder="Last Name" id="lastname" title="Letters only" required pattern="[a-zA-Z]+" />
            <p class="req">* Required</p>
            <input type="text" name="email" placeholder="E-mail" id="email" title="Please enter a valid email. &#013Example: JohnSmith@gmail.com" required pattern="[a-zA-Z0-9]+@[a-zA-Z]+\.[a-zA-Z]+"/>
            <p class="req">* Required</p>
            <input type="text" name="username" placeholder="Username" id="username" required />
            <p class="req">* Required</p>
            <input type="password" name="password1" placeholder="Password" id="password1" required />
            <p class="req">* Required</p>
            <input type="password" name="password2" placeholder="Re-enter Password" id="password2" required />
            <p class="req">* Required</p>
            <input type="submit" value="Create Account" id="create-acc" />
            <a href="login.html">
              <input type="button" value="Sign in" id="sign-in" />
            </a>
          </form>
        </div>
      </div>
    </div>
  </body>
</html>