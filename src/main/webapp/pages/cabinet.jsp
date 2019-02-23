<%@ page import="ru.inno.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.inno.entity.Car" %>
<%@ page import="ru.inno.entity.Person" %><%--
  Created by IntelliJ IDEA.
  User: yuri
  Date: 2019-02-22
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Personal area</title>
</head>
<body>
<h4>Customer Orders</h4>
<%
    out.print(request.getAttribute("personid"));
%>
<ul>
    <%
        List<Order> list = (List<Order>) request.getAttribute("customerOrders");
        if (list != null) {
            for (Order order : list) {
                Car car = order.getCar();
                Person person = order.getCustomer();
                out.println("<li> Car: " + car.getMark().getName() + " Customer Name: " + order.getOwner().getName() + "</li>");
            }
        }
    %>
</ul>
</body>
</html>
