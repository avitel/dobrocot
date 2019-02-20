<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Registration new user</title>
</head>
<body>

<p><a href="${pageContext.request.contextPath}/">Home</a>

<form method="post" action="${pageContext.request.contextPath}/registration">

    name        <input type="text" name="name" required autofocus><br />
    login       <input type="text" name="login" required ><br />
    password    <input type="password" name="pass" required><br />
    is seller   <input type="checkbox" name="isseller" ><br />
    is customer <input type="checkbox" name="iscustomer" ><br />
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
    <input type="submit" value="Зарегистрироваться"/>

</form>


</body>
</html>