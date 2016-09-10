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
    <script type="text/javascript">
        function AlertIt(id) {
            var answer = confirm("You are about to delete menu " + id + ". Press OK to continue.")
            if (answer)
                window.location = "http://localhost:8080/admin/menu/remove/" + id + "";
        }
    </script>
</head>
<body>

<!--Navigation bar-->
<div class="navbar-wrapper" style="position: relative;">
    <div class="container">
        <div class="navbar navbar-inverse navbar-static-top" style="background-color: #689f38; border: none;"
             role="navigation">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <img src="/resources/images/apple(2).png" style="padding: 10px 0;">
                    <a class="navbar-brand" style="color: #ffffff;" href="#">
                        Fresh Point Administrator
                    </a>
                </div>
                <div class="navbar-collapse collapse">
                    <ul class="nav navbar-nav">
                        <li class="active"><a href="/admin/index" style="background-color: #e33539; color: #ffffff">Main
                            page</a></li>
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
</div>
<!--Nav bar finished-->
<div class="container marketing" style="background-color: #ffffff">
    <div class="row featurette">
        <div class="col-md-12">
            <h2 class="featurette-heading text-center" style="margin-top: 40px;">Menu list</h2>
        </div>
    </div>
    <hr class="featurette-divider" style="margin: 15px 0;">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <c:forEach items="${menuList}" var="menu">

                <div class="row">
                    <div class="col-md-6">
                        <h4 class="text-center">
                            <a href="/admin/menu/${menu.id}">${menu.name}</a>
                        </h4>
                    </div>
                    <div class="col-md-6">
                        <a href="javascript:AlertIt(${menu.id});">
                            <h4 class="text-center"><img src="/resources/images/removeDish.png" width="24"> Delete</h4>
                        </a>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    <hr class="featurette-divider" style="margin: 15px 0;">
    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h2 class="text-center">Add new menu</h2>

            <form role="form" action="/admin/menu/add" method="post">
                <div class="form-group">
                    <label for="menuName">Menu name</label>
                    <input type="text" id="menuName" class="form-control" name="name" placeholder="Enter menu name"
                           maxlength="60" pattern="[A-Za-z]+" required>
                </div>
                <label for="table2">Choose from existing dishes to add to this menu</label>
                <table class="table" id="table2">
                    <thead></thead>
                    <tbody>
                    <tr>
                        <td>Image</td>
                        <td>Name</td>
                        <td>Add</td>
                    </tr>
                    <c:forEach items="${dishList}" var="dish">
                        <tr>
                            <td class="success"><img src="${dish.photoLink}"></td>
                            <td class="success">${dish.name}</td>
                            <td class="success"><input type="checkbox" name="dishName" value="${dish.name}"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                <button type="submit" class="btn btn-success">Add new menu</button>
            </form>
        </div>
    </div>
</div>
<!-- /.container -->
</body>
</html>
