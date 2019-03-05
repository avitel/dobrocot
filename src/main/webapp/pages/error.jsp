<%--
  Created by IntelliJ IDEA.
  User: Пользователь
  Date: 22.02.2019
  Time: 16:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>Ошибка</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>

<div class="w3-bar w3-black">
    <a href="${pageContext.request.contextPath}/" class="w3-bar-item w3-button"><i class="fa fa-home"></i> Главная</a>
    <a href="${pageContext.request.contextPath}/login" class="w3-bar-item w3-button w3-right"><i class="fa fa-sign-in"></i> Войти</a>
</div>

<div class="w3-display-container" style="height:300px;">

    <div class="w3-display-middle">

        <h3>
            Возникла непредвиденная ошибка. Попробуйте начать с <a href="${pageContext.request.contextPath}/">главной</a> страницы.
        </h3>

    </div>

</div>

</body>
</html>
