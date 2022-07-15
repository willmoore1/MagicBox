<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- This web page gives the user the ability to register a new online banking account. Enter username
    password -->
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
    <a href=http://localhost:8080/JSP/>"Home Page"
     <span class="sr-only"></span>
    <h1>Fill in forms below to apply for new account &#127808;</h1>
	
<form action="newacc" form method="POST">
 		
 		<label>Username:</label> <input type="text" name="username" required
			placeholder="Enter a username please"> <br /> <label>Password:</label>
		<input type="password" name="password" required
			placeholder="Enter a password please">

		<!-- This button should send a request (specified by method) to the servlet indicated by "action" -->
		<input type="submit" value="Apply!">
</form>

	</div>
</body>
</html>