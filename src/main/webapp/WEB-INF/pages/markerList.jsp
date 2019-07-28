<%--
  Created by IntelliJ IDEA.
  User: abdulkodir
  Date: 26.07.19
  Time: 23:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Markers</title>
</head>
<body>
Hello
<c:forEach var="marker" items="${markerList}">
    ${marker}
</c:forEach>
</body>
</html>
