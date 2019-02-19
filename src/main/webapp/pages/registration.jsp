<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration new user</title>
</head>
<body>

<form method="post" action="${pageContext.request.contextPath}/registration">

    <input type="name" name="name" required autofocus><br />
    <input type="text" name="login" required ><br />
    <input type="password" name="pass" required><br />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>
</form>

</body>
</html>