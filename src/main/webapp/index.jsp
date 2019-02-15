<%--
  Created by IntelliJ IDEA.
  User: ilya
  Date: 2019-02-12
  Time: 17:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Dobrocot</title>
</head>
<body>
<a href="/">Home</a><br/>
Текущее время: <%= new java.util.Date() %>
<br/>
Введите параметры поиска автомобиля!<br/>
<form action="/search" method="post">

  <p><select name="mark">
    <option selected disabled>Марка</option>
    <option value="BMV">BMV</option>
    <option value="Lada">Lada</option>
    <option value="Mersedes">Mersedes</option>
  </select>

  <select name="model">
    <option selected disabled>Модель</option>
    <option value="xxx">xxx</option>
    <option value="yyy Гена">yyy</option>
    <option value="zzz">zzz</option>
  </select>

  <select name="color">
    <option selected disabled>Цвет</option>
    <option value="red">red</option>
    <option value="green">green</option>
    <option value="blue">blue</option>
  </select>

  <select name="engine">
    <option selected disabled>Двигатель</option>
    <option value="2">2.0</option>
    <option value="3">3.0</option>
    <option value="7.3">7.3</option>
  </select>

  <select name="places">
    <option selected disabled>Мест</option>
    <option value="2">2</option>
    <option value="4">4</option>
    <option value="6">6</option>
  </select>

  <input type="submit" value="Поиск"></p>
</form>
</body>
</html>

