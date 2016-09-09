<%@ page import="liliyayalovchenko.domain.Position" %>
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
          </ul>
        </div>
      </div>
    </div>
  </div>
</div> <!--Nav bar finished-->

<div class="container marketing" style="background-color: #ffffff">

  <div class="row">
    <div class="col-lg-8" style="float: none; margin-left: auto; margin-right: auto;">
      <h1 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">All employees</h1>
    </div>
  </div>

  <div class="row" style="margin-top: 40px;">
    <c:forEach items="${employees}" var="employee">
      <div class="col-lg-4">
        <a href="/admin/employee/${employee.id}"><img class="img-circle" src="${employee.photoLink}" alt="Employee photo"></a>
        <h2 style="color: #689f38;font-size: 30px;">${employee.firstName} ${employee.secondName}</h2>
        <h2 style="color: #000000;font-size: 18px;">${employee.position}</h2>
        <h2 style="color: #000000;font-size: 18px;">${employee.phone}</h2>
        <img src="/resources/images/removeEmployee.png" width="24">
        <a href="/admin/employee/remove/${employee.id}" style="text-decoration: none; color: darkred;">Delete</a>
      </div><!-- /.col-lg-4 -->
    </c:forEach>
  </div><!-- /.row -->

  <hr class="featurette-divider" style="margin-bottom: 25px; margin-top: 25px;">
  <div class="row">
    <div class="col-lg-8" style="float: none; margin-left: auto; margin-right: auto;">
      <h1 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">Add new employee</h1>

    </div>
  </div>
  <form role="form" action="/admin/employee/save" method="post">

    <div class="form-group">
      <label for="employeeName">Employee first name</label>
      <input type="text" id="employeeName" class="form-control" name="firstName" required>
    </div>

    <div class="form-group">
      <label for="employeeSecondName">Employee second name</label>
      <input type="text" id="employeeSecondName" class="form-control" name="secondName" required>
    </div>

    <div class="form-group">
      <label for="employeeEmplDate">Date in format "dd-MM-yyyy", e.g. 22-10-2012</label>
      <input type="text" id="employeeEmplDate" class="form-control" name="dateOfEmpl" required>
    </div>

    <div class="form-group">
      <label for="employeePhone">Phone</label>
      <input type="text" id="employeePhone" class="form-control" name="phone" required>
    </div>

    <div class="form-group">
      <label for="employeePosition">Position</label>
      <c:set var="enumValues" value="<%=Position.values()%>"/>
      <p class="text-center">
      <c:forEach items="${enumValues}" var="enumValue">
        <input type="radio" id="employeePosition" name="position" value="${enumValue.toString()}" required> ${enumValue.toString()}
      </c:forEach>
      </p>
    </div>

    <div class="form-group">
      <label for="employeeSalary">Salary</label>
      <input type="number" id="employeeSalary" class="form-control" name="salary" required>
    </div>

    <div class="form-group">
      <label for="employeePhoto">Photo link</label>
      <input type="text" id="employeePhoto" class="form-control" name="photoLink" required>
    </div>

    <button type="submit" class="btn btn-success"><img src="/resources/images/employee.png" width="32"> Add employee</button>
  </form>
</div><!-- /.container -->
</body>
</html>
