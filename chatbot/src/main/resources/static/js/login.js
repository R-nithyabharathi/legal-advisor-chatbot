<head>
  <meta charset="UTF-8" />
  <title>Login</title>
  <link rel="stylesheet" th:href="@{/css/style.css}" />
  <script th:src="@{/js/login.js}" defer></script>
</head>



document.addEventListener("DOMContentLoaded", function () {
  const form = document.querySelector("form");

  form.addEventListener("submit", function (event) {
    const username = document.getElementById("username").value.trim();
    const password = document.getElementById("password").value.trim();

    if (username === "" || password === "") {
      alert("Please enter both username and password.");
      event.preventDefault(); // stop form submission
    }
  });
});
