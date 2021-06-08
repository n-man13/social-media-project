<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>User-profile</title>
    <link rel="stylesheet" href="styles.css" />
  </head>
  <body>
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
        <a href="">Username_Placeholder</a>
        <a href="login.jsp">Logout</a>
      </div>
    </div>

    <!-- Main Content -->
    <div class="main-container">
      <div class="hobby-bar">
        <div class="search-bar">
          <form action="search" method="post">
            <input type="text" placeholder="Search..." id="search-box" />
            <input type="submit" value="GO" id="search-submit" />
          </form>
        </div>
        <div class="main-box"><p>Sports</p></div>
        <div class="main-box">
          <p>Video<br />Games</p>
        </div>
        <div class="main-box"><p>Music</p></div>
        <div class="main-box"><p>Arts & Crafts</p></div>
        <div class="main-box"><p>Technology</p></div>
      </div>
      <div class="main-content">
        <div class="profile-post">
          <label for="userpage-textbox"><h2>Post your status!</h2></label>
          <form action="status" method="post">
            <textarea name="userpagetextbox" id="userpagetextbox" cols="80" rows="8" placeholder="Let the world know..." required></textarea>
            <input type="submit" value="Post" id="userstatus" />
          </form>
        </div>
        <div class="user-posts">
          <div class="post-content">
            <h5 id="post-owner">Username_Placeholder</h5>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem asperiores, error deserunt sit officia voluptatem pariatur ex architecto commodi! Similique, nesciunt praesentium rerum
              excepturi ea quo veritatis iste deserunt eveniet voluptate. Esse eaque recusandae, quibusdam illo reprehenderit sunt nostrum officiis dolor eligendi adipisci. Numquam sint enim ratione,
              placeat non, aspernatur fugit ab sed perferendis quasi quia eum, ipsa tempora libero nostrum tempore! Sunt autem consequatur esse, obcaecati labore quasi cupiditate alias mollitia
              accusamus. Hic voluptatibus natus, repellat, quo dolores blanditiis debitis distinctio facilis dolorum iste facere nihil animi asperiores sunt assumenda nulla minus, mollitia sint
              expedita illo. Molestias voluptate quod repellat fugit voluptas totam cumque voluptates, consectetur aliquid rerum. Nam autem nulla nostrum quos facere quibusdam, a at hic rerum qui
              aperiam harum, saepe sed itaque obcaecati. Exercitationem qui nam atque blanditiis. A obcaecati rerum tenetur laborum, odit, dolor omnis, amet aspernatur cupiditate consequatur nam.
              Atque, debitis fugit! Optio voluptas repudiandae unde dolores consectetur dolor nihil necessitatibus ipsam ullam aperiam, enim est veritatis, magnam possimus odio impedit totam aliquid,
              voluptate debitis. Adipisci aperiam, rerum quas odio expedita aspernatur voluptatum tempore, dolores assumenda iste atque in nisi deleniti suscipit accusantium necessitatibus tenetur
              laboriosam eveniet saepe officia repellat quibusdam ipsam et! Repellat?
            </p>
            <hr />
            <div class="replies">
              <div class="userreply">
                <h5>Username_Placeholder</h5>
                <p>User reply goes here - Lorem ipsum dolor sit amet consectetur adipisicing elit. Soluta, pariatur.</p>
              </div>
              <div class="userreply">
                <h5>Username_Placeholder</h5>
                <p>User reply goes here - Lorem ipsum dolor sit amet consectetur adipisicing elit. Minus odio cumque veritatis.</p>
              </div>
            </div>
            <form action="status" method="post">
              <textarea name="replytextbox" id="replytextbox" cols="80" rows="4" placeholder="Reply to this post" required></textarea>
              <input type="submit" value="Reply" id="post-response" />
            </form>
          </div>
          <div class="post-content">
            <h5>Username_Placeholder</h5>
            <p>
              Lorem ipsum dolor sit amet, consectetur adipisicing elit. Autem asperiores, error deserunt sit officia voluptatem pariatur ex architecto commodi! Similique, nesciunt praesentium rerum
              excepturi ea quo veritatis iste deserunt eveniet voluptate. Esse eaque recusandae, quibusdam illo reprehenderit sunt nostrum officiis dolor eligendi adipisci. Numquam sint enim ratione,
              placeat non, aspernatur fugit ab sed perferendis quasi quia eum, ipsa tempora libero nostrum tempore! Sunt autem consequatur esse, obcaecati labore quasi cupiditate alias mollitia
              accusamus. Hic voluptatibus natus, repellat, quo dolores blanditiis debitis distinctio facilis dolorum iste facere nihil animi asperiores sunt assumenda nulla minus, mollitia sint
              expedita illo. Molestias voluptate quod repellat fugit voluptas totam cumque voluptates, consectetur aliquid rerum. Nam autem nulla nostrum quos facere quibusdam, a at hic rerum qui
              aperiam harum, saepe sed itaque obcaecati. Exercitationem qui nam atque blanditiis. A obcaecati rerum tenetur laborum, odit, dolor omnis, amet aspernatur cupiditate consequatur nam.
              Atque, debitis fugit! Optio voluptas repudiandae unde dolores consectetur dolor nihil necessitatibus ipsam ullam aperiam, enim est veritatis, magnam possimus odio impedit totam aliquid,
              voluptate debitis. Adipisci aperiam, rerum quas odio expedita aspernatur voluptatum tempore, dolores assumenda iste atque in nisi deleniti suscipit accusantium necessitatibus tenetur
              laboriosam eveniet saepe officia repellat quibusdam ipsam et! Repellat?
            </p>
            <hr />
            <div class="replies">
              <div class="user-reply">
                <h5>Username_Placeholder</h5>
                <p>User reply goes here - Lorem ipsum dolor sit amet, consectetur adipisicing elit. Odio, autem?</p>
              </div>
              <div class="user-reply">
                <h5>Username_Placeholder</h5>
                <p>User reply goes here - Lorem ipsum dolor sit amet consectetur adipisicing elit. Animi quod eaque consequuntur.</p>
              </div>
            </div>
            <form action="post-comment" method="post">
              <textarea name="post-textbox" id="post-textbox" cols="80" rows="4" placeholder="Reply to this post" required></textarea>
              <input type="submit" value="Reply" id="post-response" />
            </form>
          </div>
        </div>
      </div>
    </div>
  </body>
</html>
