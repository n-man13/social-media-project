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
    <title>User-landing</title>
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
        <a href="explore.jsp" id="exploreform">
          <input type="submit" value="Explore" id="main-button" />
        </a>
        <form action="status" method="post" id="videogameform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="videogames" />
          <input type="submit" value="Video Games" id="main-button" />
        </form>
        <form action="status" method="post" id="musicform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="music" />
          <input type="submit" value="Music" id="main-button" />
        </form>
        <form action="status" method="post" id="artsncraftsform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="artsncrafts" />
          <input type="submit" value="Arts & Crafts" id="main-button" />
        </form>
        <form action="status" method="post" id="technologyform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="technology" />
          <input type="submit" value="Technology" id="main-button" />
        </form>
        <form action="status" method="post" id="sportsform">
          <input type="hidden" name="initload" value="initload" />
          <input type="hidden" name="pagename" value="sports" />
          <input type="submit" value="Sports" id="main-button" />
        </form>
      </div>
      <div class="main-content">
        <div class="content-piece">
          <h2>News Header</h2>
          <h3>Weekday, Month Day</h3>
          <p>
            Lorem ipsum dolor sit amet consectetur adipisicing elit. Rem possimus animi nam, fugiat laudantium vitae incidunt? Dicta odit, corrupti molestiae ullam tenetur assumenda tempora mollitia,
            veritatis minus amet nam eos repellendus, pariatur officia vitae molestias. Adipisci nihil fugiat suscipit placeat exercitationem aliquid! Ea, veritatis iure molestias quo tempora harum
            vero officia architecto vel quod illum ex at perspiciatis saepe, dolores magnam aperiam minus soluta non velit expedita explicabo aut consequatur. Doloribus, laboriosam incidunt fuga quis
            atque quas obcaecati nemo illum, necessitatibus praesentium voluptatibus! Fugit mollitia qui sed! Odit vitae quos fugiat debitis consequuntur laborum sunt architecto explicabo asperiores
            inventore rerum in odio, fuga commodi illum dolores tenetur et nesciunt adipisci. Id officiis dolorem quidem ducimus, corrupti eligendi temporibus nihil veniam veritatis error, vitae
            pariatur laboriosam expedita totam distinctio dignissimos illum dolor? Temporibus hic, eveniet rerum repudiandae voluptate ducimus molestias nesciunt animi. Facere rerum hic, neque nihil
            autem temporibus consectetur sint quis obcaecati eaque ipsa repellendus, nemo, fuga molestiae aspernatur eveniet deleniti sapiente assumenda voluptatibus? Est doloremque iste animi. Non,
            mollitia! Reprehenderit tenetur odio obcaecati vero fuga aut dignissimos, vitae architecto quisquam harum at amet possimus tempore natus corrupti earum. Vero obcaecati nostrum nobis quis
            et debitis blanditiis velit natus fugit?
          </p>
          <p>
            Lorem, ipsum dolor sit amet consectetur adipisicing elit. Perferendis amet earum quia laborum ipsam non dicta, voluptatum sunt eveniet numquam itaque dolorum, recusandae obcaecati
            inventore! Dolore excepturi cumque tempore commodi fuga quas, eveniet minus tenetur dolorum est exercitationem suscipit reprehenderit doloremque ratione porro! Optio cum asperiores
            repellat vero iure veritatis ducimus libero iste, exercitationem, consequuntur quam ullam perferendis nihil maxime nesciunt corrupti dolorem aut ab molestiae! Vel saepe cupiditate nobis
            temporibus totam placeat at sequi. Necessitatibus reiciendis aut, aperiam non ad incidunt quibusdam, totam eius itaque minima, vel repudiandae fugit magnam quos sunt animi repellendus quo
            illum qui harum? Minus labore, deserunt quis numquam consequatur aliquam nisi soluta, ad, quia cupiditate dolore possimus. Esse corrupti iusto ipsa ipsam nam qui, quibusdam delectus in
            minima ad officia provident molestias, quae iure, beatae nisi sapiente quis consequatur sint molestiae repudiandae minus consectetur. Obcaecati delectus velit voluptatum? Nulla earum eum
            accusamus, veritatis atque corrupti velit amet modi necessitatibus obcaecati tempora possimus, delectus commodi quidem perspiciatis mollitia voluptatibus at! Molestiae, deserunt ratione?
            Voluptas quas aperiam ad esse sit! Earum nam recusandae minima unde culpa, amet molestias quos illo architecto facilis praesentium asperiores odio iure adipisci quia qui porro illum
            dolore, dolores natus, quas temporibus.
          </p>
          <br />
          <hr />
        </div>
        <div class="content-piece">
          <h2>News Header</h2>
          <h3>Weekday, Month Day</h3>
          <p>
            Lorem ipsum dolor sit amet consectetur, adipisicing elit. Perspiciatis sequi tempora et optio eos cupiditate nam ducimus magni reprehenderit, labore libero, illum quos dolorem debitis
            inventore? Temporibus, nemo ex molestiae harum laborum consectetur impedit accusamus aspernatur a aut recusandae blanditiis distinctio unde magni necessitatibus odio, assumenda dolorum?
            Exercitationem laudantium quas dignissimos perferendis nisi dolore molestias, sint cupiditate, vel eveniet numquam. Ab repudiandae aperiam officiis minima eaque nemo vitae iure voluptatem,
            hic reiciendis amet enim, iusto ipsum facilis ex accusantium alias eveniet at. Soluta voluptas saepe voluptatem molestiae quaerat unde natus itaque officiis non eligendi rem fuga dolor
            voluptates distinctio praesentium veniam, quisquam aliquid corrupti nemo. Voluptatibus a illo officiis iste dignissimos asperiores ducimus, inventore vel corrupti odit nisi consequuntur,
            obcaecati laborum eligendi et debitis vitae consequatur voluptas libero! Officiis vitae vero asperiores repellat! Voluptatibus aspernatur, repellendus sequi facilis mollitia est nobis
            perferendis delectus inventore, tempore eos aperiam accusantium minima maxime blanditiis? Fugit laborum, nisi alias ut est suscipit adipisci vitae cupiditate error minus recusandae ipsam
            culpa dolore voluptas iste aliquam temporibus eligendi numquam magni sit laudantium reprehenderit. Laudantium voluptate reiciendis quos esse dolorum fugiat quas cupiditate fugit provident,
            voluptatem, tempora tenetur beatae odit quis neque ipsa distinctio eos! Provident aut beatae quis nam dicta amet laudantium cupiditate officiis! Quas voluptas ipsam consectetur id tempora
            facere error adipisci tenetur accusamus ipsa. Quis pariatur in reiciendis laboriosam laborum architecto, eaque dolore aspernatur dolorem quisquam eveniet, esse, tempora ratione harum est
            qui? Aliquid, sunt recusandae dolorem optio eaque earum consequatur cum aliquam nemo sapiente ratione deleniti, natus error consectetur nam saepe cumque molestiae, obcaecati rerum minima
            nesciunt unde quae. Obcaecati ipsam qui amet voluptatibus incidunt eaque consectetur ullam fugit asperiores. Maiores aliquam dolorem quasi accusantium provident repellendus harum et!
            Necessitatibus quo ducimus autem saepe. Laudantium ratione exercitationem reprehenderit iure illo, maxime quo distinctio.
          </p>
          <br />
          <hr />
        </div>
      </div>
    </div>
  </body>
</html>