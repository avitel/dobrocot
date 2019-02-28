<%@ page import="ru.inno.controller.MainController" %>
<%@ page import="ru.inno.entity.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.5/css/bootstrap-select.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.5/js/bootstrap-select.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-select/1.13.5/js/i18n/defaults-*.min.js"></script>
</head>
<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark  justify-content-end">
    <ul class="navbar-nav">
        <%
            String userName = (String) request.getAttribute(MainController.USER_ATTRIBUTE);
            String contextPath = request.getContextPath();
            if (null == userName) {
                out.println("<li class=\"nav-item\"><a class=\"nav-link\" href=\"" + contextPath + "/\">Привет, Гость! </a></li>");
                out.println("<li class=\"nav-item\"><a class=\"nav-link\" href=\"" + contextPath + "/registration\">Регистрация</a></li>");
                out.println("<li class=\"nav-item\"><a class=\"nav-link\" href=\"" + contextPath + "/login\">Войти</a></li>");
            } else {
                out.println("<li class=\"nav-item\"><a class=\"nav-link\" href=\"" + contextPath + "/\">Привет, " + userName + "!</a></li>");
                out.println("<li class=\"nav-item\"><a class=\"nav-link\" href=\"" + contextPath + "/cabinet\">Личный кабинет</a></li>");
                out.println("<li class=\"nav-item\"><a class=\"nav-link\" href=\"" + contextPath + "/logout\">Выйти</a></li>");
            }
        %>
    </ul>
</nav>
<br>
<p>Сегодня: <%= new SimpleDateFormat("EEE dd.MM.yyyy").format(new Date()) %>
    <br/>
    Введите параметры поиска автомобиля!<br/>
    <form action="<%=contextPath%>/" method="post">
            <%
            Map<String,Object> filterOption = (Map<String, Object>) request.getAttribute(MainController.FILTER_OPTION_ATTRIBUTE);
            if (null==filterOption) {
            response.sendRedirect(contextPath+"/error");
            }
            %>
<p>
    <label>
        <select class="form-control" name=<%=MainController.MARK_REQUEST%>>
            <option selected disabled>Марка</option>
            <%
                List<Mark> marks = (List<Mark>) filterOption.get(MainController.MARK_REQUEST);
                if (null != marks) {
                    for (Mark mark : marks) {
                        out.println("<option value=" + mark.getId() + ">" + mark.getName() + "</option>");
                    }
                }
            %>
        </select>
    </label>

    <label>
        <select class="form-control" name=<%=MainController.MODEL_REQUEST%>>
            <option selected disabled>Модель</option>
            <%
                List<Model> models = (List<Model>) filterOption.get(MainController.MODEL_REQUEST);
                if (null != models) {
                    for (Model model : models) {
                        out.println("<option value=" + model.getId() + ">" + model.getName() + "</option>");
                    }
                }
            %>
        </select>
    </label>

    <label>
        <select class="form-control" name=<%=MainController.COLOR_REQUEST%>>
            <option selected disabled>Цвет</option>
            <%
                List<Color> colors = (List<Color>) filterOption.get(MainController.COLOR_REQUEST);
                if (null != colors) {
                    for (Color color : colors) {
                        out.println("<option value=" + color.getId() + ">" + color.getName() + "</option>");
                    }
                }
            %>
        </select>
    </label>

    <label>
        <select class="form-control" name=<%=MainController.ENGINE_REQUEST%>>
            <option selected disabled>Объем</option>
            <%
                List<Engine> engines = (List<Engine>) filterOption.get(MainController.ENGINE_REQUEST);
                if (null != engines) {
                    for (Engine engine : engines) {
                        out.println("<option value=" + engine.getId() + ">" + engine.getName() + "</option>");
                    }
                }
            %>
        </select>
    </label>

    <input class="btn btn-primary" type="submit" value="Поиск">
</p>
</form>

<br/>
<br/>
Найденные авто:<br/>
<!-- Modal body -->
<div class="modal-body">
    <table class="table table-striped table-sm">
        <thead class="thead-dark">
        <tr>
            <th>Владелец</th>
            <th>Марка</th>
            <th>Модель</th>
            <th>Год выпуска</th>
            <th>Количество сидений</th>
            <th>Цвет</th>
            <th>Объем</th>
            <th>Цена</th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <%
            List<Car> cars = (List<Car>) request.getAttribute(MainController.RESULT_ATTRIBUTE);
            if (null != cars) {
                for (Car car : cars) {
                    out.println("<tr><form action=\"" + request.getContextPath() + "/carreserve\" method=\"post\">" +
                            "<td>" + car.getOwner().getName() + "</td>" +
                            "<td>" + car.getMark().getName() + "</td>" +
                            "<td>" + car.getModel().getName() + "</td>" +
                            "<td>" + String.format("%tY", car.getAssembledate()) + "</td>" +
                            "<td>" + car.getNumberofseats() + "</td>" +
                            "<td>" + car.getColor().getName() + "</td>" +
                            "<td>" + car.getEngine().getName() + "</td>" +
                            "<td>" + car.getDayprice() + "</td>" +
                            "<td>" + "<button name=\"car_id\" type=\"submit\" value=" + car.getId() + ">Выбрать</button></p></form>" + "</td>"
                            + "</tr>"
                    );
                }
            } else out.println("Ничего не найдено");
        %></tbody>
    </table>
</div>
</body>
</html>

