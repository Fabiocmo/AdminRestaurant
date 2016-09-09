<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                        <li><a href="/" style="color: #ffffff">Go to restaurant site</a></li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div> <!--Nav bar finished-->

<div class="container marketing" style="background-color: #ffffff">
    <form role="form" action="/admin/menu/save/${menu.id}" method="post">
        <div class="form-group">
            <label for="menuId">Menu id</label>
            <input type="text" id="menuId" class="form-control" name="id" value="${menu.id}" readonly>
        </div>

        <div class="form-group">
            <label for="menuName">Menu name</label>
            <input type="text" id="menuName" class="form-control" name="name" value="${menu.name}">
        </div>

        <button type="submit" class="btn btn-success">Save changes</button>

    </form>

    <form role="form" action="/admin/removeDishFromMenu/${menu.id}" method="post">

        <label for="table1">Dish list for this menu</label>
        <table class="table" id="table1">

            <tbody>
            <tr>
                <td>Image</td>
                <td>Name</td>
                <td>Delete</td>
            </tr>
            <c:forEach items="${menu.dishList}" var="dish">
                <tr>
                    <td class="success"><img src="${dish.photoLink}"></td>
                    <td class="success">${dish.name}</td>
                    <td class="success"><input type="radio" name="dishName" value="${dish.name}"></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <button type="submit" class="btn btn-success">Delete dish</button>

    </form>

    <hr style="width: 95%;">
    <form role="form" action="/admin/addDishToMenu/${menu.id}" method="post">
        <label for="table2">Choose from existing dishes to add to this menu</label>
        <table class="table" id="table2">
            <thead></thead>
            <tbody>
            <tr>
                <td>Image</td>
                <td>Name</td>
                <td>Add</td>
            </tr>
            <c:forEach items="${allDishes}" var="dish">
                <tr>
                    <td class="success"><img src="${dish.photoLink}"></td>
                    <td class="success">${dish.name}</td>
                    <td class="success"><input type="radio" name="dishName" value="${dish.name}"></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <button type="submit" class="btn btn-success">Add dish</button>
    </form>
</div>
</body>
</html>
