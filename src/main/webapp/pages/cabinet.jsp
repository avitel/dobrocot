<%@ page import="ru.inno.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.inno.entity.Car" %>
<%@ page import="ru.inno.entity.Person" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html lang="en">

<head>
    <title>Personal area</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body>

<nav class="navbar navbar-expand-sm bg-dark navbar-dark  justify-content-end">
    <ul class="navbar-nav">
        <li class="nav-item">
            <a class="nav-link" href="/">Главная</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="/logout">Выйти</a>
        </li>
    </ul>
</nav>
<br>


<div class="container">

    <div class="container">
        <h3>Личный кабинет</h3>
        <div class="card">
            <div class="card-body">
                <h4 class="card-title">
                    <%
                        Person curPerson = (Person) request.getAttribute("currentPerson");
                        if (curPerson != null) {
                            out.print(curPerson.getName());
                        }
                    %>
                </h4>
                <p class="card-text">Ваш идентификатор - <% out.print(curPerson.getId());%></p>
                <a href="/addnewcar" class="card-link">Добавить свой авто</a>
                <a href="#" class="card-link" data-toggle="modal" data-target="#myModal">Список моих авто</a>


                <div class="container">
                    <!-- Button to Open the Modal -->
                    <!--
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal">
                        Open modal
                    </button>
-->

                    <!-- The Modal -->
                    <div class="modal" id="myModal">
                        <div class="modal-dialog">
                            <div class="modal-content">

                                <!-- Modal Header -->
                                <div class="modal-header">
                                    <h4 class="modal-title">Мои авто</h4>
                                    <button type="button" class="close" data-dismiss="modal">&times;</button>
                                </div>

                                <!-- Modal body -->
                                <div class="modal-body">
                                    <table class="table table-striped table-sm">
                                        <thead class="thead-dark">
                                        <tr>
                                            <th>Марка</th>
                                            <th>Модель</th>
                                            <th>Цвет</th>
                                            <th>Двигатель</th>
                                            <th>Число мест</th>
                                            <th>Дата выпуска</th>
                                            <th>Цена за день</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <%
                                            List<Car> listCar = (List<Car>) request.getAttribute("carList");
                                            if (listCar != null) {
                                                for (Car car : listCar) {
                                                    DateTimeFormatter d = DateTimeFormatter.ofPattern("d-MMM-uuuu");

                                                    out.print("<tr>");
                                                    out.println("<td>" + car.getMark().getName() + "</td>");
                                                    out.println("<td>" + car.getModel().getName() + "</td>");
                                                    out.println("<td>" + car.getColor().getName() + "</td>");
                                                    out.println("<td>" + car.getEngine().getName() + "</td>");
                                                    out.println("<td>" + car.getNumberofseats() + "</td>");
                                                    out.println("<td>" + car.getAssembledate().toLocalDateTime().format(d) + "</td>");
                                                    out.println("<td>" + car.getDayprice() + "</td>");
                                                    out.print("<tr>");
                                                }
                                            } else {
                                                out.print("<tr><td>Ничего не найдено</td></tr>");
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>

                                <!-- Modal footer -->
                                <div class="modal-footer">
                                    <button type="button" class="btn btn-danger" data-dismiss="modal">Закрыть</button>
                                </div>

                            </div>
                        </div>
                    </div>

                </div>


            </div>
        </div>
    </div>

    <br/><br/>
    <div class="row">
        <div class="col">
            <div class="container">
                <p>Сделки аренды автомобилей</p>
            </div>

            <table class="table table-striped table-sm">
                <thead class="thead-dark">
                <tr>
                    <th>Дата</th>
                    <th>Арендодатель</th>
                    <th>Марка</th>
                    <th>Модель</th>
                    <th>Цвет</th>
                    <th>Двигатель</th>
                    <th>Начало</th>
                    <th>Окончание</th>
                    <th>Сумма</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Order> customerOrders = (List<Order>) request.getAttribute("customerOrders");
                    if (customerOrders != null) {
                        for (Order order : customerOrders) {
                            Car car = order.getCar();
                            if (car != null) {
                                DateTimeFormatter dt = DateTimeFormatter.ofPattern("d-MMM-uuuu HH:mm:ss");
                                DateTimeFormatter d = DateTimeFormatter.ofPattern("d-MMM-uuuu");

                                out.print("<tr>");
                                out.println("<td>" + order.getDateOrder().toLocalDateTime().format(dt) + "</td>");
                                out.println("<td>" + order.getOwner().getName() + "</td>");
                                out.println("<td>" + car.getMark().getName() + "</td>");
                                out.println("<td>" + car.getModel().getName() + "</td>");
                                out.println("<td>" + car.getColor().getName() + "</td>");
                                out.println("<td>" + car.getEngine().getName() + "</td>");
                                out.println("<td>" + order.getBegindate().toLocalDateTime().format(d) + "</td>");
                                out.println("<td>" + order.getEnddate().toLocalDateTime().format(d) + "</td>");
                                out.println("<td>" + order.getPrice() + "</td>");
                                out.print("<tr>");
                            } else {
                                out.print("<tr><td>Пустая машина</td></tr>");
                            }
                        }
                    } else {
                        out.print("<tr><td>Ничего не найдено</td></tr>");
                    }
                %>
                </tbody>
            </table>
        </div>


        <div class="col">
            <div class="container">
                <p>Предоставленые авто в аренду</p>
            </div>

            <table class="table table-striped table-sm">
                <thead class="thead-dark">
                <tr>
                    <th>Дата</th>
                    <th>Арендатор</th>
                    <th>Марка</th>
                    <th>Модель</th>
                    <th>Цвет</th>
                    <th>Двигатель</th>
                    <th>Начало</th>
                    <th>Окончание</th>
                    <th>Сумма</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Order> sellerOrders = (List<Order>) request.getAttribute("sellerOrders");
                    if (sellerOrders != null) {
                        for (Order order : sellerOrders) {
                            Car car = order.getCar();
                            if (car != null) {
                                DateTimeFormatter dt = DateTimeFormatter.ofPattern("d-MMM-uuuu HH:mm:ss");
                                DateTimeFormatter d = DateTimeFormatter.ofPattern("d-MMM-uuuu");

                                out.print("<tr>");
                                out.println("<td>" + order.getDateOrder().toLocalDateTime().format(dt)  + "</td>");
                                out.println("<td>" + order.getCustomer().getName() + "</td>");
                                out.println("<td>" + car.getMark().getName() + "</td>");
                                out.println("<td>" + car.getModel().getName() + "</td>");
                                out.println("<td>" + car.getColor().getName() + "</td>");
                                out.println("<td>" + car.getEngine().getName() + "</td>");
                                out.println("<td>" + order.getBegindate().toLocalDateTime().format(d)  + "</td>");
                                out.println("<td>" + order.getEnddate().toLocalDateTime().format(d)  + "</td>");
                                out.println("<td>" + order.getPrice() + "</td>");
                                out.print("<tr>");
                            }
                        }
                    } else {
                        out.print("<tr><td>Пустая машина</td></tr>");
                    }
                %>
                </tbody>
            </table>
        </div>
    </div>
</div>

</body>

</html>

