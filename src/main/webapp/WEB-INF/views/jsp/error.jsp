<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Error Page</title>
</head>
<body>
<h2>Application Error, please contact support.</h2>

<h3>Debug Information:</h3>

Requested URL= ${url}<br><br>

Exception= ${exception.message}<br><br>

<strong>Exception Stack Trace</strong><br>
<c:forEach items="${exception.stackTrace}" var="ste">
    ${ste}
</c:forEach>

</body>
</html>