<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- This web page gives the user the ability to see the accounts they own and the balances and only perform
    a transfer of one account into another -->
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
    <h1>Successfully Logged In, Your Accounts &#127808;</h1>
	    <!-- List Accounts [id], balance, active boolean. Somehow add code for user to select
	    account id to transfer money from and into another account id.  only allow one transfer per
	    page visit -->

<form action="transfer" form method="POST">
  <label for="quantity">Transfer Amount (between 10 and 20000):</label>
  <input type="number" id="quantity" name="quantity" required min="10" max="20000">
  <input type="submit" value="Submit">
</form>

	</div>
</body>
</html>
