<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.inno.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%--<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>--%>

<%@ page pageEncoding="UTF-8" %>
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
<%
    //    Person person = (Person) request.getAttribute("user");
    if (null == request.getAttribute("user")) {
        out.println("<p><a href=\"/registration\">Registration</a>");
        out.println("<p><a href=\"/login\">Login</a>");

    } else {
        out.println("Hello, " + request.getAttribute("user") + "!");
        out.println("<p><a href=\"/cabinet\">Cabinet</a>");
        out.println("<p><a href=\"/order\">Order</a>");
        out.println("<p><a href=\"/logout\">Logout</a>");
    }
%>
<p>Текущее время: <%= new java.util.Date() %>
    <br/>
    Введите параметры поиска автомобиля!<br/>

    <form action="${pageContext.request.contextPath}/" method="post">
        <%--
            <%
                Map<String, Object> filters = (Map<String, Object>) request.getAttribute("filterOption");
                Set<String> filterList = filters.keySet();
                for (String filterName : filterList) {
                    out.println("<p><label> <select name=" + filterName + "> <option selected disabled>" + filterName + "</option>");
        //            Collection marks = (Collection) ((Map<String, Object>) request.getAttribute("filterOption")).get("marks");
                    Collection options = (Collection) filters.get(filterName);



                    for (Object option : options) {
                        out.println("<option value=" + option.getId() + ">" + mark.getName() + "</option>");
                    }
                    ;
                    out.println("</select></label>");
                }
            %>--%>
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
            out.println("<form action=\"/carreserve\" method=\"post\">" +
                    car.getOwner().getName() + " | "
                    + car.getMark().getName() + " | "
                    + car.getModel().getName() + " | "
                    + car.getAssembledate().toString() + " | "
                    + car.getNumberofseats() + " | "
                    + car.getColor().getName() + "  "
                    + "<button name=\"car_id\" type=\"submit\" value=" + car.getId() + ">Выбрать</button></p></form>"
                    + "<br/>"
            );
        }
    } else out.println("Вы ничего не выбрали!");

%>
</body>
</html>

