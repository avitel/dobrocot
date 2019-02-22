<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.io.IOException" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.inno.entity.*" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
    List<Car> cars = (List<Car>) request.getAttribute("result");
    if (!(null == cars)) {
        for (Car car : cars) {
            out.println("<form action=\"/order\" method=\"post\">" +
                    car.getOwner().getName() + " | "
                    + car.getMark().getName() + " | "
                    + car.getModel().getName() + " | "
                    + car.getAssembledate().toString() + " | "
                    + car.getNumberofseats() + " | "
                    + car.getColor().getName() + "  "
                    + "<button name=\"car_id\" type=\"submit\" value="+car.getId()+">Выбрать</button></p></form>"
                    + "<br/>"
            );
        }
    } else out.println("Вы ничего не выбрали!");

%>

</body>
</html>

