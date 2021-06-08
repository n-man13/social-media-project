function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

async function pageLoad() {
  var myDiv = document.getElementById("errormsg");

  var errorCode = '<%= request.getAttribute("error") %>';
  console.log(errorCode);

  if (errorCode == 10) {
    const codes = {
      10: "User does not exist.",
      5: "A user already exists with that username",
      6: "A user already exists with that email address",
    };

    myDiv.innerHTML = codes[errorCode];
    myDiv.style.display = "block";
    await sleep(3000);
    myDiv.style.display = "none";
  }
}
