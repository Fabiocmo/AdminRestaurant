<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Menu page</title>
  <meta charset="utf-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <meta name="description" content="Restaurant of health fresh food. Affordable to everyone.">
  <meta name="author" content="Liliya Yalovchenko">
  <link rel="shortcut icon" href="/resources/apple.ico">
  <!-- Bootstrap core CSS -->
  <link href="/resources/css/bootstrap.css" rel="stylesheet">
  <!-- Custom styles for this template -->
  <link href="/resources/css/carousel.css" rel="stylesheet">
</head>
<body>
<!--Navigation bar-->
<div class="navbar-wrapper" style="position: relative;">
  <div class="container">
    <div class="navbar navbar-inverse navbar-static-top" style="background-color: #689f38; border: none;" role="navigation">
      <div class="container">
        <div class="navbar-header">
          <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </button>
          <img src="/resources/images/apple(2).png" style="padding: 10px 0;"><a class="navbar-brand" style="color: #ffffff;" href="#">Fresh Point Administrator</a>
        </div>
        <div class="navbar-collapse collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="/admin/index" style="background-color: #e33539; color: #ffffff">Main page</a></li>
            <li><a href="/admin/order" style="color: #ffffff;">Orders</a></li>
            <li><a href="/admin/menu" style="color: #ffffff">Menus</a></li>
            <li><a href="/admin/employee" style="color: #ffffff">Employees</a></li>
            <li><a href="/admin/dish" style="color: #ffffff">Dishes</a></li>
            <li><a href="/admin/warehouse" style="color: #ffffff">Warehouse</a></li>
            <li><a href="/" style="color: #ffffff">Go to restaurant site</a></li>
          </ul>
        </div>
      </div>
    </div>
  </div>
</div> <!--Nav bar finished-->

<div class="container marketing" style="background-color: #ffffff">
  <div class="row">
    <div class="col-lg-8" style="float: none; margin-left: auto; margin-right: auto;">
      <h1 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">Fresh Point Cafe</h1>

    </div>
  </div>

  <hr class="featurette-divider" style="margin: 15px 0;">
  <div class="row" style="margin-top: 40px;">

    <div class="col-lg-4" style="margin: 0 auto; float: none;">
      <img class="img-circle" style="float: none; margin: 0 auto; text-align: center;" src="${employee.photoLink}" width="140" alt="${employee.firstName}">
      <h2 class="text-center" style="color: #689f38;font-size: 30px;">${employee.firstName} ${employee.secondName}</h2>
      <p class="text-center"><strong>Position</strong>: ${employee.position}</p>
      <p class="text-center"><strong>Phone</strong>: ${employee.phone}</p>
      <p class="text-center"><strong>Salary</strong>: ${employee.salary}</p>
      <p class="text-center"><strong>Employment date</strong>: ${date}</p>
      <c:set var="position" value="${employee.position}"/>
      <c:if test="${position eq 'WAITER' || position eq 'WAITRESS'}">
        <p class="lead text-center">
        <ul class="list-inline">
          <strong>Orders: </strong>
          <c:forEach items="${employee.orderList}" var="order">
            <a href="/admin/order/${order.id}"><li style="font-size: small; color: #e33539; font-family: OpenSans-Light, serif"><strong> Order number ${order.orderNumber} </strong></li></a>
          </c:forEach>
        </ul>
        </p>
      </c:if>
      <c:if test="${position eq 'COOK'}">
        <p class="lead text-center">
        <ul class="list-inline">
          <strong>Ready meal list: </strong>
          <c:forEach items="${employee.readyMealList}" var="readyMeal">
            <a href="/admin/dish/${readyMeal.dishId.id}"><li style="font-size: small; color: #e33539; font-family: OpenSans-Light, serif"><strong> Name: ${readyMeal.dishId.name} Order: ${readyMeal.orderId.orderNumber} </strong></li></a>
          </c:forEach>
        </ul>
        </p>
      </c:if>

      <p class="text-center" style="font-family: 'Minion Pro'; font-size: medium; color: #689f38">
        <a href="/admin/employee/edit/${employee.id}">Edit employee</a>
      </p>

    </div><!-- /.col-lg-4 -->

  </div><!-- /.row -->
</div><!-- /.container -->

</body>
</html>
