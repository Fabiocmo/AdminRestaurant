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
    <div class="row">
        <div class="col-lg-8" style="float: none; margin-left: auto; margin-right: auto;">
            <h1 class="text-center"
                style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">${employee.firstName} ${employee.secondName}</h1>
        </div>
    </div>

    <form role="form" action="/admin/employee/save/${employee.id}" method="post">
        <div class="form-group">
            <label for="employeeId">Employee id</label>
            <input type="text" id="employeeId" class="form-control" name="id" value="${employee.id}" readonly>
        </div>
        <div class="form-group">
            <label for="employeeName">Employee firstName</label>
            <input type="text" id="employeeName" class="form-control" name="firstName" value="${employee.firstName}">
        </div>
        <div class="form-group">
            <label for="employeeSecondName">Employee secondName</label>
            <input type="text" id="employeeSecondName" class="form-control" name="secondName"
                   value="${employee.secondName}">
        </div>

        <div class="form-group">
            <label for="employeePosition">Employee category</label>
            <c:set var="enumValues" value="<%=Position.values()%>"/>
            <div class="row">
                <p class="text-center">
                    <c:forEach items="${enumValues}" var="enumValue">
                        <input type="radio" id="employeePosition" name="position" value="${enumValue.toString()}"
                               required> ${enumValue.toString()}
                    </c:forEach>
                </p>
            </div>
        </div>

        <div class="form-group">
            <label for="employeePhone">Employee phone</label>
            <input type="text" id="employeePhone" class="form-control" name="phone" value="${employee.phone}">
        </div>

        <div class="form-group">
            <label for="employeeSalary">Employee salary</label>
            <input type="number" id="employeeSalary" class="form-control" name="salary" value="${employee.salary}">
        </div>

        <div class="form-group">
            <label for="employeeEmplDate">Date in format "dd-MM-yyyy", e.g. 22-10-2012</label>
            <input type="text" id="employeeEmplDate" class="form-control" name="dateOfEmpl" value="${date}" required>
        </div>

        <div class="form-group">
            <label for="employeePhone">Employee photo</label>
            <input type="text" id="employeePhoto" class="form-control" name="photoLink" value="${employee.photoLink}">
        </div>

        <button type="submit" class="btn btn-success">Save changes</button>

    </form>
</div>

</body>
</html>
