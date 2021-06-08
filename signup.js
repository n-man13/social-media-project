function checkPassword(form) {
  password1 = form.password1.value;
  password2 = form.password2.value;

  if (password1 != password2) {
    alert("\nPasswords did not match: Please try again.");
    return false;
  } else {
    return true;
  }
}