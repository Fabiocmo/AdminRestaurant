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

  <div class="row featurette">
    <div class="col-md-12">
      <h1 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">
        Warehouse
      </h1>
      <p class="text-center"><img src="/resources/images/warehouse.png" width="64"></p>
    </div>
  </div>
  <hr class="featurette-divider" style="margin: 15px 0;">

  <div class="row">
    <div class="col-md-6">
      <div class="row">
        <div class="col-md-4">
          <p class="text-center"><a href="/admin/warehouse/filter/name"><img src="/resources/images/filter.png" width="24"> Sort name</a></p>
        </div>
        <div class="col-md-4">
          <form action="/admin/warehouse/find"  method="post">
            <input type="text" class="text-center" name="name" pattern="[A-Za-z]+" placeholder="enter ingredient name">
          </form>
        </div>
        <div class="col-md-4">
          <p class="text-center"><a href="/admin/warehouse/filter/amount"><img src="/resources/images/filter.png" width="24"> Sort by amount</a></p>
        </div>
      </div>
      <form role="form" action="/admin/warehouse/remove" method="post">
        <table class="table" id="table1">
          <tbody>
            <tr>
              <td>Name</td>
              <td>Amount</td>
              <td>Delete</td>
            </tr>
            <c:forEach items="${warehouseList}" var="ingredient">
              <tr>
                <td class="success">${ingredient.ingredId.name}</td>
                <td class="success">${ingredient.amount}</td>
                <td class="success"><input type="radio" name="ingredName" value="${ingredient.ingredId.name}"></td>
              </tr>
            </c:forEach>
          </tbody>
        </table>
        <p class="text-center">
        <button type="submit" class="btn btn-success">Delete ingredient</button>
        </p>
      </form>
    </div>
    <div class="col-md-6">
      <div class="row">
      <div class="col-md-8" style="float: none; margin-left: auto; margin-right: auto;">
        <h3 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; color: #e33539 ;">Add ingredient to warehouse</h3>
      </div>
    </div>
    <form role="form" action="/admin/warehouse/add" method="post">
      <div class="form-group">
        <label for="ingredientName">Ingredient name</label>
        <input type="text" id="ingredientName" class="form-control" name="name" required>
      </div>

      <div class="form-group">
        <label for="amount">Amount</label>
        <input type="number" id="amount" class="form-control" name="amount" required>
      </div>

      <button type="submit" class="btn btn-success">Add ingredient to warehouse</button>
    </form>
    </div>
  </div>
</div><!-- /.container -->

</body>
</html>
