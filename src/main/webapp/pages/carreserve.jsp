<%@ page import="java.util.HashMap" %><%--
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
<a href="/logout">Logout</a><br>
<a href="/cabinet">Cabinet</a><br>
<a href="/">Home</a><br>
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
Это авто недоступно на сдедующие даты: <br>
<table border="1" width="10%" cellpadding="5">
    <%
        HashMap<String, String> mapDates = (HashMap<String, String>) request.getAttribute("dateReserved");
        for (String begin : mapDates.keySet()) {
    %>
    <tr>
        <td><%=begin%></td>
        <td><%=mapDates.get(begin)%></td>
    </tr>
   <%}%>
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
    <input type="date" name="date_begin"
           value="<%=request.getAttribute("date_begin")%>"
           data-date="" data-date-format="DD MMMM YYYY"/>
    <input type="date" name="date_end"
           value="<%=request.getAttribute("date_end")%>"
           data-date="" data-date-format="DD MMMM YYYY"/>
    <input type="hidden" name="car_id" value=${car_id} />

    <button type="submit" name="submit" value="Добавить" >Посчитать</button>
</form>
<label></label>
<form action="/carreserve2" method="post">
    <input type="hidden" name="date_begin"
           value="<%=request.getAttribute("date_begin")%>"
           data-date="" data-date-format="DD MMMM YYYY"/>
    <input type="hidden"  name="date_end"
           value="<%=request.getAttribute("date_end")%>"
           data-date="" data-date-format="DD MMMM YYYY"/>
    <input type="hidden" name="car_id" value=${car_id} />
    <input type="hidden" name="id_owner" value=${id_owner} />
    <input type="hidden" name="price" value=${cost} />
    <button type="submit">Беру</button>
</form>

</body>
</html>
