<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" name="viewport" content="width=device-width, initial-scale=1">
<title>MagicBox</title>
<style>
body {
  background: linear-gradient(-45deg, #ee7752, #e73c7e, #23a6d5, #23d5ab);
	background-size: 400% 400%;
	animation: gradient 15s ease infinite;
	height: 100vh;
}

@keyframes gradient {
	0% {
		background-position: 0% 50%;
	}
	50% {
		background-position: 100% 50%;
	}
	100% {
		background-position: 0% 50%;
	}
}

.content {
  max-width: 500px;
  margin: auto;
  background: white;
  padding: 10px;
}

</style>
</head>
<body>
<div class="content">
    <h1>Welcome, to the bank! &#127808;</h1>
	<h3>Please login below &#127808;</h3>

	<!--  POST requests obfuscate the parameters from the search bar UNLIKE a GET request -->
	<form method="POST" action="login">
		<!--  The post request will be sent to http://localhost:8080/MagicBox/login -->

		<label>Username:</label> <input type="text" name="username" required
			placeholder="Enter a username please"> <br /> <label>Password:</label>
		<input type="password" name="password" required
			placeholder="Enter a password please">

		<!-- This button should send a request (specified by method) to the servlet indicated by "action" -->
		<input type="submit" value="Login!">
	</form>


	<!-- If someone clicks this button, they will apply for a bank account -->
	<form method="GET" action="apply">
		<!-- http://localhost:8080/Apply/employees -->

		<p>To apply for an online account, please click below</p>
		<input type="submit" value="Click me!">

	</form>
	</div>
</body>
</html>
