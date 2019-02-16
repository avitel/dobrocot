<%@ page import="java.util.Collection" %>
<%@ page import="ru.inno.entity.Mark" %>
<%@ page import="ru.inno.entity.Model" %>
<%@ page import="ru.inno.entity.Color" %>
<%@ page import="ru.inno.entity.Engine" %><%--
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
        <%
            Collection<Mark> marks = (Collection<Mark>) request.getAttribute("marks");
            for (Mark mark : marks) {
                out.println("<option value=" + mark.getId() + ">" + mark.getName() + "</option>");
            }
            ;
        %>
    </select>

        <select name="model">
            <option selected disabled>Модель</option>
            <%
                Collection<Model> models = (Collection<Model>) request.getAttribute("models");
                for (Model model : models) {
                    out.println("<option value=" + model.getId() + ">" + model.getName() + "</option>");
                }
                ;
            %>
        </select>

        <select name="color">
            <option selected disabled>Цвет</option>
            <%
                Collection<Color> colors = (Collection<Color>) request.getAttribute("colors");
                for (Color color : colors) {
                    out.println("<option value=" + color.getId() + ">" + color.getName() + "</option>");
                }
                ;
            %>
        </select>

        <select name="engine">
            <%
                Collection<Engine> engines = (Collection<Engine>) request.getAttribute("engines");
                for (Engine engine : engines) {
                    out.println("<option value=" + engine.getId() + ">" + engine.getName() + "</option>");
                }
                ;
            %>
        </select>

        <input type="submit" value="Поиск"></p>
</form>
</body>
</html>

