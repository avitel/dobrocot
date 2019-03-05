<%@ page import="ru.inno.controller.MainController" %>
<%@ page import="ru.inno.entity.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Map" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page pageEncoding="UTF-8" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<title>Главная</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
    table {
        border-spacing: 0;
        width: 100%;
        border: 1px solid #ddd;
    }

    th {
        cursor: pointer;
    }

    th,
    td {
        text-align: left;
        padding: 16px;
    }

    tr:nth-child(even) {
        background-color: #f2f2f2
    }

    .form-control {
        display: block;
        width: 100%;
        height: calc(1.5em + .75rem + 2px);
        padding: .375rem .75rem;
        font-size: 1rem;
        font-weight: 400;
        line-height: 1.5;
        color: #495057;
        background-color: #fff;
        background-clip: padding-box;
        border: 1px solid #ced4da;
        border-radius: .25rem;
        transition: border-color .15s ease-in-out, box-shadow .15s ease-in-out;
    }

    .mySlides {
        display: none
    }

    .w3-left,
    .w3-right,
    .w3-badge {
        cursor: pointer
    }

    .w3-badge {
        height: 13px;
        width: 13px;
        padding: 0
    }

</style>

<body>

<%
    String userName = (String) request.getAttribute(MainController.USER_ATTRIBUTE);
    String contextPath = request.getContextPath();
    String hello = "Привет, " + userName;
    String regCab = "Личный кабинет";
    String regCabLink = "/cabinet";
    String logInOut = "Выйти";
    String logInOutLink = "/logout";
    String logInOutSign = "out";

    if (null == userName) {
        hello = "Привет, Гость!";
        regCab = "Регистрация";
        regCabLink = "/registration";
        logInOut = "Войти";
        logInOutLink = "/login";
        logInOutSign = "in";
    }
%>

<div class="w3-bar w3-black">
    <span class="w3-bar-item">
        <% out.println(hello); %>
    </span>
    <a href="<% out.println(contextPath + regCabLink); %> " class="w3-bar-item w3-button">
        <% out.println(regCab); %>
    </a>
    <a href="<% out.println(contextPath + logInOutLink); %>" class="w3-bar-item w3-button w3-right"><i class="fa fa-sign-<%out.println(logInOutSign);%>"></i>
        <% out.println(logInOut); %>
    </a>
</div>


<div class="w3-content w3-display-container" style="max-width:100%; ">
    <img class="mySlides" src="/static/car_1.jpg" style="width:100%">
    <img class="mySlides" src="/static/car_2.jpg" style="width:100%">
    <img class="mySlides" src="/static/car_3.jpg" style="width:100%">
    <div class="w3-center w3-container w3-section w3-large w3-text-white w3-display-bottommiddle" style="width:100%">
        <div class="w3-left w3-hover-text-khaki" onclick="plusDivs(-1)">&#10094;</div>
        <div class="w3-right w3-hover-text-khaki" onclick="plusDivs(1)">&#10095;</div>
        <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(1)"></span>
        <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(2)"></span>
        <span class="w3-badge demo w3-border w3-transparent w3-hover-white" onclick="currentDiv(3)"></span>
    </div>
</div>

<br>

<div class="w3-row">

    <div class="w3-container">

        <div class="w3-container">
            <div class="w3-card w3-border">
                <header class="w3-container w3-light-grey w3-border-bottom">
                    <h4>Параметры поиска авто</h4>
                </header>

                <form action="<%=contextPath%>/" method="post">
                    <%
                        Map<String,Object> filterOption = (Map<String, Object>) request.getAttribute(MainController.FILTER_OPTION_ATTRIBUTE);
                        if (null==filterOption) {
                            response.sendRedirect(contextPath+"/error");
                        }
                    %>
                    <div class="w3-row-padding">
                        <p>Выберите один или несколько</p>

                        <div class="w3-quarter">
                            <select class="form-control" name=<%=MainController.MARK_REQUEST%>>
                                <option value="" disabled selected>
                                    Марка
                                </option>
                                <%
                                    List<Mark> marks = (List<Mark>) filterOption.get(MainController.MARK_REQUEST);
                                    if (null != marks) {
                                        for (Mark mark : marks) {
                                            out.println("<option value=" + mark.getId() + ">" + mark.getName() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                        </div>

                        <div class="w3-quarter">
                            <select class="form-control" name=<%=MainController.MODEL_REQUEST%>>
                                <option value="" disabled selected>
                                    Модель
                                </option>
                                <%
                                    List<Model> models = (List<Model>) filterOption.get(MainController.MODEL_REQUEST);
                                    if (null != models) {
                                        for (Model model : models) {
                                            out.println("<option value=" + model.getId() + ">" + model.getName() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                        </div>

                        <div class="w3-quarter">
                            <select class="form-control" name=<%=MainController.COLOR_REQUEST%>>
                                <option value="" disabled selected>
                                    Цвет
                                </option>
                                <%
                                    List<Color> colors = (List<Color>) filterOption.get(MainController.COLOR_REQUEST);
                                    if (null != colors) {
                                        for (Color color : colors) {
                                            out.println("<option value=" + color.getId() + ">" + color.getName() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                        </div>

                        <div class="w3-quarter">
                            <select class="form-control" name=<%=MainController.ENGINE_REQUEST%>>
                                <option value="" disabled selected>
                                    Двигатель
                                </option>
                                <%
                                    List<Engine> engines = (List<Engine>) filterOption.get(MainController.ENGINE_REQUEST);
                                    if (null != engines) {
                                        for (Engine engine : engines) {
                                            out.println("<option value=" + engine.getId() + ">" + engine.getName() + "</option>");
                                        }
                                    }
                                %>
                            </select>
                        </div>

                    </div>

                    <br>

                    <div class="w3-row-padding">
                        <button type="submit" class="w3-block w3-button w3-dark-grey">Поиск</button>
                    </div>
                </form>

                <br>
            </div>

        </div>
    </div>

    <p></p>
    <p></p>
</div>

<br><br>

<div class="w3-container">

    <div class="w3-container">
        <div class="w3-card w3-border">
            <header class="w3-container w3-light-grey w3-border-bottom">
                <p>Надено:</p>
            </header>

            <div class="w3-container w3-responsive city w3-animate-opacity orders">
                <p></p>
                <table class="w3-table-all w3-small">
                    <thead class="thead-dark">
                    <tr class="w3-dark-grey w3-text-white">
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
                    <tr>
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
                                            "<td>" + "<button class=\"w3-block w3-button w3-dark-grey\" name=\"car_id\" " +
                                            "type=\"submit\" value=" + car.getId() + ">Выбрать</button> </td> </form>"
                                            + "</tr>"
                                    );
                                }
                            } else out.println("Ничего не найдено");
                        %>
                    </tr>
                    </tbody>
                </table>
            </div>

            <br>
        </div>
    </div>
</div>

<br><br>

<footer>
    <div class="w3-container w3-light-grey ">

        <p> @Copyright Dobrocot 2019</p>

    </div>

</footer>

<script>
    var myIndex = 0;
    carousel();

    function carousel() {
        var i;
        var x = document.getElementsByClassName("mySlides_");
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        myIndex++;
        if (myIndex > x.length) {
            myIndex = 1
        }
        x[myIndex - 1].style.display = "block";
        setTimeout(carousel, 2000); // Change image every 2 seconds
    }

</script>


<script>
    var slideIndex = 1;
    showDivs(slideIndex);

    function plusDivs(n) {
        showDivs(slideIndex += n);
    }

    function currentDiv(n) {
        showDivs(slideIndex = n);
    }

    function showDivs(n) {
        var i;
        var x = document.getElementsByClassName("mySlides");
        var dots = document.getElementsByClassName("demo");
        if (n > x.length) {
            slideIndex = 1
        }
        if (n < 1) {
            slideIndex = x.length
        }
        for (i = 0; i < x.length; i++) {
            x[i].style.display = "none";
        }
        for (i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" w3-white", "");
        }
        x[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " w3-white";
    }

</script>


</body>

</html>
