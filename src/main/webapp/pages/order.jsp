<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 2019-02-18
  Time: 18:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Бронирование автомобиля</title>
  </head>
  <body>
  Форма бронировния автомобиля<br/>

  Вы авторизованы как
  <%out.println(request.getAttribute("username"));%><br/>

  <p><a href="${pageContext.request.contextPath}/logout">logout</a>
  <p><a href="${pageContext.request.contextPath}/">Home</a>

  </body>
</html>
