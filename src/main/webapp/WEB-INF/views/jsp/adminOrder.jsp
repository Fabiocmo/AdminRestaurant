<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Menu page</title>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
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
                    <img src="/resources/images/apple(2).png" style="padding: 10px 0;"><a class="navbar-brand"
                                                                                          style="color: #ffffff;"
                                                                                          href="#">Fresh Point
                    Administrator</a>
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
            <h1 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">
                Order number #${order.orderNumber}
            </h1>

            <p class="text-center"><img src="/resources/images/orders.png" width="64"></p>
        </div>
    </div>
    <hr class="featurette-divider" style="margin: 15px 0;">

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <table class="table" id="table1">
                <tbody>
                <tr>
                    <td>Waiter/ss</td>
                    <td>Table</td>
                    <td>Date</td>
                    <td>Order status</td>
                    <td>Dish list</td>
                </tr>
                <tr>
                    <td class="success"><a
                            href="/admin/employee/${order.employeeId.id}">${order.employeeId.firstName} ${order.employeeId.secondName}</a>
                    </td>
                    <td class="success">${order.tableNumber}</td>
                    <td class="success">${order.orderDate}</td>
                    <td class="success">${order.status}</td>
                    <td class="success">
                        <c:forEach items="${order.dishList}" var="dish">
                            <ol>
                                <li><a href="/admin/dish/${dish.id}">${dish.name}</a></li>
                            </ol>
                        </c:forEach>
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>
</div>
<!-- /.container -->
</body>
</html>
