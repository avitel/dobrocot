<%@ page import="java.util.Collection" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.inno.entity.*" %>
<%@ page import="java.util.Map" %><%--
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
<a href="${pageContext.request.contextPath}/">Home</a><br/>
Текущее время: <%= new java.util.Date() %>
<br/>
Введите параметры поиска автомобиля!<br/>

<form action="${pageContext.request.contextPath}/" method="post">

    <p><label>
        <select name="mark">
            <option selected disabled>Марка</option>
            <%
                Collection<Mark> marks = (Collection<Mark>) ((Map<String, Object>) request.getAttribute("filterOption")).get("marks");
                for (Mark mark : marks) {
                    out.println("<option value=" + mark.getId() + ">" + mark.getName() + "</option>");
                }
                ;
            %>
        </select>
    </label>

        <label>
            <select name="model">
                <option selected disabled>Модель</option>
                <%
                    Collection<Model> models = (Collection<Model>) ((Map<String, Object>) request.getAttribute("filterOption")).get("models");
                    ;
                    for (Model model : models) {
                        out.println("<option value=" + model.getId() + ">" + model.getName() + "</option>");
                    }
                    ;
                %>
            </select>
        </label>

        <label>
            <select name="color">
                <option selected disabled>Цвет</option>
                <%
                    Collection<Color> colors = (Collection<Color>) ((Map<String, Object>) request.getAttribute("filterOption")).get("colors");
                    ;
                    for (Color color : colors) {
                        out.println("<option value=" + color.getId() + ">" + color.getName() + "</option>");
                    }
                    ;
                %>
            </select>
        </label>

        <label>
            <select name="engine">
                <option selected disabled>Объем</option>
                <%
                    Collection<Engine> engines = (Collection<Engine>) ((Map<String, Object>) request.getAttribute("filterOption")).get("engines");
                    for (Engine engine : engines) {
                        out.println("<option value=" + engine.getId() + ">" + engine.getName() + "</option>");
                    }
                    ;
                %>
            </select>
        </label>

        <input type="submit" value="Поиск"></p>
</form>

<br/>
<br/>
Найденные авто:<br/>
<%
    List<Car> cars = (List<Car>) request.getAttribute("result");
    if (!(null == cars)) {
        for (Car car : cars) {
            out.println(car.toString() + "<br/>");
        }
    } else out.println("Вы ничего не выбрали!");

%>
</body>
</html>

