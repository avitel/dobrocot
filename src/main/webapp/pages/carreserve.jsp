<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<title>Главная</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<script type="text/javascript" src="./static/js/jquery.min.js"></script>
<script type="text/javascript" src="./static/js/datepicker.js"></script>
<link rel="stylesheet" href="./static/css/datepicker.css"/>

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

<body class="w3-theme-l4">

<div class="w3-bar w3-black">
    <a href="${pageContext.request.contextPath}/" class="w3-bar-item w3-button"><i class="fa fa-home"></i> Главная</a>
    <a href="${pageContext.request.contextPath}/cabinet" class="w3-bar-item w3-button">Кабинет</a>
    <a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button w3-right"><i
            class="fa fa-sign-out"></i> Выйти</a>
</div>

<br>

<div class="w3-row">

    <div class="w3-col l6 m12  w3-container w3-content" style="min-width: 280px">
        <div class="w3-card w3-border">
            <div class="w3-bar w3-border-bottom w3-light-grey intronav">
                <header class="w3-container">
                    <h4>Выбранная машина</h4>
                </header>
            </div>


            <div class="w3-container w3-responsive">
                <br>
                <table class="w3-table-all w3-hoverable w3-small">
                    <tbody>
                    <tr>
                        <td><b>Владелец</b></td>
                        <td>${owner}</td>
                        <td><b>Двигатель</b></td>
                        <td>${engine}</td>
                    </tr>
                    <tr>
                        <td><b>Марка</b></td>
                        <td>${mark}</td>
                        <td><b>Число мест</b></td>
                        <td>${numberofseats}</td>
                    </tr>
                    <tr>
                        <td><b>Модель</b></td>
                        <td>${model}</td>
                        <td><b>Дата выпуска</b></td>
                        <td>${assembledate}</td>
                    </tr>
                    <tr>
                        <td><b>Цена за день</b></td>
                        <td>${dayprice}</td>
                        <td><b>Цвет</b></td>
                        <td>${color}</td>
                    </tr>
                    </tbody>
                </table>

            </div>
            <br>

            <footer class="w3-container w3-responsive">
                <h5>Авто недоступно к бронированию в следующие даты:</h5>

                <button onclick="accordionAction('Demo1')" class="w3-btn w3-block w3-dark-grey w3-left-align">Показать
                    список
                    <i class="fa fa-caret-down"></i></button>
                <div id="Demo1" class="w3-container w3-hide">
                    <table class="w3-table-all  w3-hoverable w3-small">
                        <tbody>
                        <thead>
                        <tr class="w3-light-grey">
                            <th>C</th>
                            <th>По</th>
                        </tr>
                        </thead>
                        <%
                            HashMap<String, String> mapDates = (HashMap<String, String>) request.getAttribute("dateReserved");
                            for (String begin : mapDates.keySet()) {
                        %>
                        <tr>
                            <td><%=begin%>
                            </td>
                            <td><%=mapDates.get(begin)%>
                            </td>
                        </tr>
                        <%}%>
                        </tbody>
                    </table>
                </div>
                <br>
            </footer>

        </div>
        <br>
    </div>

    <div class="w3-col l6 m12 w3-container w3-content" style="min-width: 280px">
        <div class="w3-card w3-border">
            <div class="w3-bar w3-border-bottom w3-light-grey intronav">
                <header class="w3-container">
                    <h4>Выбери необходимые даты</h4>
                </header>
            </div>

            <br>

            <div class="w3-row-padding">
                <div class="w3-half" style="min-width: 280px">
                    <div id="dp1" class=" datepicker-here"></div>
                    <h4>Начало аренды:
                        <label id="startLabel">${date_begin}</label>
                        <!--                    <input type="text" value="" class="startDate" disabled>-->
                    </h4>
                </div>

                <div class="w3-half" style="min-width: 280px">
                    <div id="dp2" class=" datepicker-here"></div>
                    <h4>Окончание аренды:
                        <label id="endLabel">${date_end}</label>
                    </h4>
                </div>
            </div>

            <div class="w3-container">
                <h4>Аренда на ${days} дней. Сумма: ${cost} &#x20bd;</h4>
            </div>

            <div class="w3-container">
                <form action="${pageContext.request.contextPath}/carreserve3" method="post">
                    <input type="hidden" name="date_begin" class="startDate" value="">
                    <input type="hidden" name="date_end" class="endDate" value="">
                    <input type="hidden" name="car_id" value=${car_id}>
                    <br>
                    <button id="idCalc" class="w3-block w3-button w3-dark-grey" type="submit" disabled>Посчитать
                    </button>
                    <br>
                </form>

                <form action="${pageContext.request.contextPath}/carreserve2" method="post">
                    <input type="hidden" name="date_begin" value="${date_begin}" class="startDate">
                    <input type="hidden" name="date_end" value="${date_end}" class="endDate">
                    <input type="hidden" name="car_id" value=${car_id}>
                    <input type="hidden" name="id_owner" value=${id_owner}>
                    <input type="hidden" name="price" value=${cost}>
                    <button id="idSubmit" class="w3-block w3-button w3-dark-grey" type="submit" disabled>Арендовать
                    </button>
                </form>


            </div>


            <!-- The Modal -->
            <div id="idModal" class="w3-modal" style="${modalStyle}">
                <div class="w3-modal-content">
                    <header class="w3-container w3-light-grey">
                        <h4>Результат обработки запроса</h4>
                    </header>
                    <div class="w3-container">

                        <div class="w3-panel w3-leftbar ${msgStyle}">
                            <p>${submitMessage}</p>
                        </div>

                        <button class="w3-btn w3-dark-grey"
                                onclick="document.getElementById('idModal').style.display='none'"
                                class="w3-button w3-display-topright">Закрыть
                        </button>
                        <br><br>
                    </div>
                </div>
            </div>


            <br>
        </div>
        <br>
    </div>

</div>

<br><br>


<footer>
    <div class="w3-container w3-light-grey ">
        <p> @Copyright Dobrocot 2019</p>
    </div>
</footer>


<script type="text/javascript">

    ${jsScript}

    function accordionAction(id) {
        var x = document.getElementById(id);
        if (x.className.indexOf("w3-show") == -1) {
            x.className += " w3-show";
        } else {
            x.className = x.className.replace(" w3-show", "");
        }
    }

    $('#dp1').datepicker({
        minDate: new Date(),
        onSelect: function (fd) {
            setDate(fd, "startDate")
            document.getElementById("startLabel").innerHTML = fd;
        }
    })

    $('#dp2').datepicker({
        minDate: new Date(),
        onSelect: function (fd) {
            setDate(fd, "endDate")
            document.getElementById("endLabel").innerHTML = fd;
            enableBtn("idCalc");
            // enableBtn("idSubmit");
        }
    })

    function enableBtn(btnId) {
        var e = document.getElementById(btnId);
        e.removeAttribute("disabled");
    }

    function setDate(dateTime, elementId) {
        x = document.getElementsByClassName(elementId);
        for (i = 0; i < x.length; i++) {
            x[i].value = dateTime;
        }
    };

</script>

</body>

</html>
