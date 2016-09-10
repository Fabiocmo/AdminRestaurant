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

    <div class="row featurette">
        <div class="col-md-12">
            <h1 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; font-size: 80px; color: #e33539 ;">
                ${ingredient.ingredId.name}
            </h1>

            <p class="text-center"><img src="/resources/images/warehouse.png" width="64"></p>
        </div>
    </div>
    <hr class="featurette-divider" style="margin: 15px 0;">

    <div class="row">
        <div class="col-md-6">
            <div class="row">
                <div class="col-md-8" style="float: none; margin-left: auto; margin-right: auto;">
                    <h3 class="text-center" style="margin-top: 0; font-family: 'Minion Pro'; color: #e33539 ;">Edit</h3>
                </div>
            </div>
            <form role="form" action="/admin/warehouse/edit/${ingredient.id}" method="post">
                <div class="form-group">
                    <label for="ingredientName">Ingredient name</label>
                    <input type="text" id="ingredientName" class="form-control" name="name"
                           value="${ingredient.ingredId.name}" readonly required>
                </div>

                <div class="form-group">
                    <label for="amount">Amount</label>
                    <input type="number" id="amount" class="form-control" value="${ingredient.amount}" name="amount"
                           pattern="[0-9]{0,3}" required>
                </div>

                <button type="submit" class="btn btn-success">Edit</button>
            </form>
        </div>
    </div>
</div>
<!-- /.container -->

</body>
</html>
