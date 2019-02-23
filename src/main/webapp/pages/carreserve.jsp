<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 21.02.2019
  Time: 14:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/">На главную</a>
<h2>Выбранное авто</h2><br>
<table border="1" width="100%" cellpadding="5">
    <tr>
        <th><h3>Цвет</h3></th>
        <th><h3>Двигатель</h3></th>
        <th><h3>Марка</h3></th>
        <th><h3>Модель</h3></th>
        <th><h3>Число сидячих мест</h3></th>
        <th><h3>Владелец</h3></th>
    </tr>
    <tr>
        <td>${color}</td>
        <td>${engine}</td>
        <td>${mark}</td>
        <td>${model}</td>
        <td>${numberofseats}</td>
        <td>${owner}</td>
    </tr>
</table>

<table border="1" width="10%" cellpadding="5">
    <tr>
        <th><h3>Количество дней</h3></th>
        <th><h3>${days}</h3></th>
        <th><h3>Сумма аренды</h3></th>
        <th><h3>${cost}</h3></th>
    </tr>
</table>
<form action="/carreserve3" method="post">
    <input type="date" name="date_begin"/>
    <input type="date" name="date_end"/>
    <button type="submit" name="submit" value="Добавить" >Посчитать</button>
</form>
<label></label>
<form action="/carreserve2" method="post">
    <button type="submit">Беру</button>
</form>

</body>
</html>
