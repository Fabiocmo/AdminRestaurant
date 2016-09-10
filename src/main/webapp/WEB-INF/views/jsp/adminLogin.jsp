<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login page</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="Restaurant of health fresh food. Affordable to everyone.">
    <meta name="author" content="Liliya Yalovchenko">
    <link rel="shortcut icon" href="/resources/apple.ico">
    <!-- Bootstrap core CSS -->
    <link href="/resources/css/bootstrap.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="/resources/css/signin.css" rel="stylesheet">
</head>
<body>

<div class="container">
    <form class="form-signin" role="form" method="post" action="/admin/">
        <p>${message}</p>

        <h2 class="form-signin-heading" style="color: #e33539">Please sign in</h2>
        <input type="text" class="form-control" name="login" placeholder="Login" required autofocus>
        <input type="password" class="form-control" name="password" placeholder="Password" required>
        <button class="btn btn-lg btn-primary btn-block" style="background-color: #689f38; border: none;" type="submit">
            Sign in
        </button>
    </form>

</div>
<!-- /container -->

</body>
</html>
