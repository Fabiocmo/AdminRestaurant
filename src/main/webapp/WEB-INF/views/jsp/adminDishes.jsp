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

  <div class="row featurette">
    <div class="col-md-12">
      <h2 class="featurette-heading text-center" style="margin-top: 40px;"> All dishes</h2>
    </div>
    <p class="text-center"><a href="/admin/dish/add">
      <button type="submit" class="btn btn-success">
        <img src="/resources/images/add.png" width="24">
        Add dish
      </button>
    </a>
    </p>

  </div>
  <hr class="featurette-divider" style="margin: 15px 0;">
  <div class="list-group col-md-12 list-menu">
    <ul style="list-style-type: none; padding-left: 0; margin-bottom: 0">
      <c:forEach items="${dishList}" var="dish">
        <li style="font-family: OpenSans-Light; font-size: medium;">
          <div class="row">
            <div class="col-xs-4">
              <a href="/admin/dish/${dish.id}"><img src="${dish.photoLink}" style="float: left; padding: 5px 0;"></a>
            </div>
            <div class="col-xs-4">
              <h3 class="text-center" style="font-size: medium;">
                <a href="/admin/dish/${dish.id}"><em>${dish.name}</em></a>
              </h3>
            </div>
            <div class="col-xs-4">
              <h3 class="text-center" style="font-size: medium;">
                <button type="submit" class="btn btn-success">
                  <a href="/admin/dish/remove/${dish.id}"><img src="/resources/images/removeDish.png" width="24">
                  delete</a>
                  </button>
              </h3>
            </div>
          </div>
        </li>
      </c:forEach>
    </ul>
  </div>
</div><!-- /.container -->
</body>
</html>
