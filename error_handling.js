function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

async function pageLoad() {
  var myDiv = document.getElementById("errormsg");

  var errorCode = '<%= request.getAttribute("error") %>';
  console.log(errorCode);

  if (errorCode == 10 || errorCode == 11) {
    const codes = {
      10: "User does not exist.",
      11: "This user account has be banned.",
    };

    myDiv.innerHTML = codes[errorCode];
    myDiv.style.display = "block";
    await sleep(3000);
    myDiv.style.display = "none";
  }
}
