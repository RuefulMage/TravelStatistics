<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
</head>
<body>
<form action="/abd_war/registration" method="POST">
    <input name="userName" type="text"><br>
    <input name="fullName" type="text"><br>
    <input name="email" type="text"><br>
    <input name="password" type="password"><br>
    <input name="passwordRepeat" type="password"><br>
    <input type="submit">
</form>
</body>
</html>
