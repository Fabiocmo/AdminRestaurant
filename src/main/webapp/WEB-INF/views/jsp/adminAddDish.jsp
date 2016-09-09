<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="liliyayalovchenko.domain.DishCategory" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
      <h1 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">Add new dish</h1>
    </div>
  </div>

  <form role="form" action="/admin/dish/addNew" method="post">

    <div class="form-group">
      <label for="dishName">Dish name</label>
      <input type="text" id="dishName" class="form-control" name="name" placeholder="Enter dish name" required>
    </div>

    <div class="form-group">
      <label for="dishCategory">Dish category</label>
      <c:set var="enumValues" value="<%=DishCategory.values()%>"/>
      <div class="row">
        <p class="text-center">
          <c:forEach items="${enumValues}" var="enumValue">
            <input type="radio" id="dishCategory" name="dishCategory" value="${enumValue.toString()}" required> ${enumValue.toString()}
          </c:forEach>
        </p>
      </div>
    </div>

    <div class="form-group">
      <label for="dishPrice">Dish price</label>
      <input type="number" id="dishPrice" class="form-control" name="price" placeholder="Enter dish price" required>
    </div>

    <div class="form-group">
      <label for="dishWeight">Dish weight</label>
      <input type="number" id="dishWeight" class="form-control" name="weight" placeholder="Enter dish weight, g." required>
    </div>

    <div class="form-group">
      <label for="dishPhoto">Dish photo</label>
      <input type="text" id="dishPhoto" class="form-control" name="photoLink" placeholder="Enter link to the photo, size must be 120*80 px">
    </div>

    <label for="table1">Choose ingredients for this dish</label>
    <table class="table" id="table1">

      <tbody>
      <tr>
        <td>Name</td>
        <td>Add</td>
      </tr>
      <c:forEach items="${ingredients}" var="ingredient">
        <tr>
          <td class="success">${ingredient.name}</td>
          <td class="success"><input type="checkbox" name="ingredientName" value="${ingredient.name}" ></td>
        </tr>
      </c:forEach>
      </tbody>
    </table>

    <button type="submit" class="btn btn-success"> <img src="/resources/images/add.png" width="24"> Add dish</button>
  </form>

  </div>

</body>
</html>
