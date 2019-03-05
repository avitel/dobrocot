<%@ page import="ru.inno.entity.Order" %>
<%@ page import="java.util.List" %>
<%@ page import="ru.inno.entity.Car" %>
<%@ page import="ru.inno.entity.Person" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.time.format.DateTimeFormatter" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<!DOCTYPE html>
<html>
<title>Personal cabinet</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<body>

<div class="w3-bar w3-black">
    <a href="${pageContext.request.contextPath}/" class="w3-bar-item w3-button w3-mobile"><i class="fa fa-home"></i>
        Главная</a>
    <a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button w3-right w3-mobile"><i
            class="fa fa-sign-out"></i> Выйти</a>
</div>

<p></p>


<div class="w3-row">

    <div class="w3-third">

        <div class="w3-container">
            <div class="w3-card w3-border">
                <header class="w3-container w3-light-grey">
                    <h4>
                        <%
                            Person curPerson = (Person) request.getAttribute("currentPerson");
                            if (curPerson != null) {
                                out.print(curPerson.getName());
                            }
                        %>
                    </h4>
                </header>
                <div class="w3-container">
                    <p></p>
                    <a href="#" class="w3-block  w3-button  w3-dark-grey"
                       onclick="document.getElementById('id01').style.display='block'">Мои авто</a>
                    <p></p>
                    <a href="${pageContext.request.contextPath}/addnewcar" class="w3-block  w3-button  w3-dark-grey">Добавить авто</a>

                    <div id="id01" class="w3-modal">
                        <div class="w3-modal-content" style="max-width:700px">
                            <div class="w3-container">
                                <p>Список моих авто</p>
                                <table class="w3-table-all w3-small w3-responsive">
                                    <thead class="thead-dark">
                                    <tr class="w3-dark-grey w3-text-white">
                                        <th>Марка</th>
                                        <th>Модель</th>
                                        <th>Цвет</th>
                                        <th>Двигатель</th>
                                        <th>Число мест</th>
                                        <th>Дата выпуска</th>
                                        <th>Цена за день</th>
                                        <th></th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <%
                                        List<Car> listCar = (List<Car>) request.getAttribute("carList");
                                        if (listCar != null) {
                                            for (Car car : listCar) {
                                                DateTimeFormatter d = DateTimeFormatter.ofPattern("d-MMM-uuuu");

                                                out.print("<tr>");
                                                out.println("<form action=\"" + request.getContextPath() + "/cabinet\" method=\"post\">");
                                                out.println("<td>" + car.getMark().getName() + "</td>");
                                                out.println("<td>" + car.getModel().getName() + "</td>");
                                                out.println("<td>" + car.getColor().getName() + "</td>");
                                                out.println("<td>" + car.getEngine().getName() + "</td>");
                                                out.println("<td>" + car.getNumberofseats() + "</td>");
                                                out.println("<td>" + car.getAssembledate().toLocalDateTime().format(d) + "</td>");
                                                out.println("<td>" + car.getDayprice() + " &#x20bd;</td>");
                                                out.println("<td>" + "<button class=\"w3-block w3-button w3-dark-grey\" name=\"car_id\" " +
                                                        "type=\"submit\" value=" + car.getId() + ">Удалить</button> </td> </form>");
                                                out.print("</tr>");
                                            }
                                        } else {
                                            out.print("<tr><td>Ничего не найдено</td></tr>");
                                        }
                                    %>
                                    </tbody>
                                </table>
                                <p></p>
                                <button class="w3-btn  w3-dark-grey"
                                        onclick="document.getElementById('id01').style.display='none'"
                                        class="w3-button w3-display-topright">Закрыть
                                </button>
                                <p></p>
                            </div>
                        </div>
                    </div>

                    <p></p>
                </div>
            </div>
        </div>
        <p></p>
        <p></p>
    </div>

    <div class="w3-twothird">

        <div class="w3-container">
            <div class="w3-card w3-border">

                <div class="w3-bar w3-border-bottom w3-light-grey intronav">
                    <a class="w3-bar-item w3-button tablink w3-dark-grey" onclick="openOrders(event, 'Customer')"
                       id="myLink">Взято в аренду</a>
                    <a class="w3-bar-item w3-button tablink" onclick="openOrders(event, 'Seller')">Предоставлено в
                        аренду</a>
                </div>


                <div id="Seller" class="w3-container w3-responsive city w3-animate-opacity orders"
                     style="display: none;">
                    <p></p>
                    <table class="w3-table-all w3-small">
                        <thead class="thead-dark">
                        <tr class="w3-dark-grey w3-text-white">
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
                                        out.println("<td>" + order.getDateOrder().toLocalDateTime().format(dt) + "</td>");
                                        out.println("<td>" + order.getCustomer().getName() + "</td>");
                                        out.println("<td>" + car.getMark().getName() + "</td>");
                                        out.println("<td>" + car.getModel().getName() + "</td>");
                                        out.println("<td>" + car.getColor().getName() + "</td>");
                                        out.println("<td>" + car.getEngine().getName() + "</td>");
                                        out.println("<td>" + order.getBegindate().toLocalDateTime().format(d) + "</td>");
                                        out.println("<td>" + order.getEnddate().toLocalDateTime().format(d) + "</td>");
                                        out.println("<td>" + order.getPrice() + "  &#x20bd;</td>");
                                        out.print("</tr>");
                                    }
                                }
                            } else {
                                out.print("<tr><td>Пустая машина</td></tr>");
                            }
                        %>
                        </tbody>
                    </table>
                </div>

                <div id="Customer" class="w3-container w3-responsive city w3-animate-opacity orders"
                     style="display: block;">
                    <p></p>
                    <table class="w3-table-all w3-small">
                        <thead class="thead-dark">
                        <tr class="w3-dark-grey w3-text-white">
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

                                            out.println("<tr><td>" + order.getDateOrder().toLocalDateTime().format(dt) + "</td>");
                                            out.println("<td>" + order.getOwner().getName() + "</td>");
                                            out.println("<td>" + car.getMark().getName() + "</td>");
                                            out.println("<td>" + car.getModel().getName() + "</td>");
                                            out.println("<td>" + car.getColor().getName() + "</td>");
                                            out.println("<td>" + car.getEngine().getName() + "</td>");
                                            out.println("<td>" + order.getBegindate().toLocalDateTime().format(d) + "</td>");
                                            out.println("<td>" + order.getEnddate().toLocalDateTime().format(d) + "</td>");
                                            out.println("<td>" + order.getPrice() + " &#x20bd;</td></tr>");
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

                <p></p>

            </div>
        </div>
    </div>
</div>


<script>
    function openOrders(evt, tableName) {
        var i, x, tablinks;
        x = document.getElementsByClassName("orders");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        tablinks = document.getElementsByClassName("tablink");
        for (i = 0; i < x.length; i++) {
            tablinks[i].className = tablinks[i].className.replace(" w3-dark-grey", "");
        }
        document.getElementById(tableName).style.display = "block";
        evt.currentTarget.className += " w3-dark-grey";
    }
</script>


</body>

</html>
