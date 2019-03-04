<%@ page import="ru.inno.entity.Mark" %>
<%@ page import="java.util.Map" %>
<%@ page import="java.util.Collection" %>
<%@ page import="ru.inno.entity.Model" %>
<%@ page import="ru.inno.entity.Color" %>
<%@ page import="ru.inno.entity.Engine" %>
<%@ page import="java.util.Calendar" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<title>Добавить авто</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>


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




</style>

<body>

<div class="w3-bar w3-black">
    <a href="${pageContext.request.contextPath}/" class="w3-bar-item w3-button"><i class="fa fa-home"></i> Главная</a>
    <a href="${pageContext.request.contextPath}/cabinet" class="w3-bar-item w3-button">Кабинет</a>
    <a href="${pageContext.request.contextPath}/logout" class="w3-bar-item w3-button w3-right"><i
            class="fa fa-sign-out"></i> Выйти</a>
</div>


<!-- The Modal -->
<div id="idModal" class="w3-modal" style="${modalStyle}">
    <div class="w3-modal-content">
        <header class="w3-container w3-light-grey">
            <h4>Результат обработки запроса</h4>
        </header>
        <div class="w3-container">

            <div class="w3-panel w3-leftbar w3-red">
                <p>
                    <%
                        if (null != request.getAttribute("ErrMessage"))
                            out.print(request.getAttribute("ErrMessage"));
                    %>
                </p>
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

<div class="w3-row">

    <div class="w3-container">

        <div class="w3-content">
            <div class="w3-card w3-border">
                <header class="w3-container w3-light-grey w3-border-bottom">
                    <h4>Добавить авто</h4>
                </header>

                <div class="w3-container">
                    <p>Выберите параметры вашего авто:</p>

                    <form method="post" action="${pageContext.request.contextPath}/addnewcar">

                        <select class="form-control" name="mark">
                            <option value="" disabled selected>Марка</option>
                            <%
                                Collection<Mark> marks = (Collection<Mark>) ((Map<String, Object>) request.getAttribute("param")).get("marks");
                                for (Mark mark : marks) {
                                    out.println("<option value=" + mark.getId() + ">" + mark.getName() + "</option>");
                                }

                            %>
                        </select>
                        <br>

                        <select class="form-control" name="model">
                            <option value="" disabled selected>Модель</option>
                            <%
                                Collection<Model> models = (Collection<Model>) ((Map<String, Object>) request.getAttribute("param")).get("models");

                                for (Model model : models) {
                                    out.println("<option value=" + model.getId() + ">" + model.getName() + "</option>");
                                }

                            %>
                        </select>
                        <br>

                        <select class="form-control" name="color">
                            <option value="" disabled selected>Цвет</option>
                            <%
                                Collection<Color> colors = (Collection<Color>) ((Map<String, Object>) request.getAttribute("param")).get("colors");

                                for (Color color : colors) {
                                    out.println("<option value=" + color.getId() + ">" + color.getName() + "</option>");
                                }

                            %>
                        </select>
                        <br>

                        <select class="form-control" name="engine">
                            <option value="" disabled selected>Объем</option>
                            <%
                                Collection<Engine> engines = (Collection<Engine>) ((Map<String, Object>) request.getAttribute("param")).get("engines");
                                for (Engine engine : engines) {
                                    out.println("<option value=" + engine.getId() + ">" + engine.getName() + "</option>");
                                }

                            %>
                        </select>
                        <br>

                        <select class="form-control" name="numbeerofseats">
                            <option value="" disabled selected>Количество сидений</option>
                            <%
                                for (int i = 1; i < 5; i++) {

                                    out.println("<option value=" + i + ">" + i + "</option>");
                                }

                            %>
                        </select>
                        <br>

                        <select class="form-control" name="assembledate">
                            <option value="" disabled selected>Год выпуска</option>
                            <%
                                Calendar now = Calendar.getInstance();
                                for (int i = now.get(Calendar.YEAR); i > 1990; i--) {
                                    out.println("<option value=" + i + ">" + i + "</option>");
                                }
                            %>
                        </select>
                        <br>

                        <input type="number" class="w3-input w3-border w3-round w3-text-dark-grey" name="dayprice" placeholder="Цена за день">
                        <br>

                        <button class="w3-block w3-button w3-dark-grey" type="submit">Добавить</button>

                    </form>

                <br>
                </div>
            </div>

        </div>
    </div>

    <br><br>
</div>

<br><br>

<br><br>

<footer>
    <div class="w3-container w3-light-grey ">
        <p> @Copyright Dobrocot 2019</p>
    </div>
</footer>

</body>

</html>
