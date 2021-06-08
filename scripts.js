function getInfo() {
  var a_value = document.getElementById("username_placeholder");
  var first = '<%= request.getAttribute("fname") %>';
  var last = '<%= request.getAttribute("lname") %>';
  a_value.innerHTML = first + ", " + last;
}
