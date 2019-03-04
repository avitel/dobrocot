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
<head>
    <meta charset="UTF-8">
    <title>Добавление авто</title>
</head>
<body>

<a href="${pageContext.request.contextPath}/">Home</a> <br/>

<br/>
<font size="5">Add new car</font>
<br/>
<br/>

<font color="red">
    <%
        if (null != request.getAttribute("ErrMessage"))
            out.print(request.getAttribute("ErrMessage"));
    %></font>
<br/>

<form method="post" action="${pageContext.request.contextPath}/addnewcar">
    <p><label>
        <select name="mark">
            <option selected disabled>Марка</option>
            <%
                Collection<Mark> marks = (Collection<Mark>) ((Map<String, Object>) request.getAttribute("param")).get("marks");
                for (Mark mark : marks) {
                    out.println("<option value=" + mark.getId() + ">" + mark.getName() + "</option>");
                }

            %>
        </select>
    </label>

        <label>
            <select name="model">
                <option selected disabled>Модель</option>
                <%
                    Collection<Model> models = (Collection<Model>) ((Map<String, Object>) request.getAttribute("param")).get("models");

                    for (Model model : models) {
                        out.println("<option value=" + model.getId() + ">" + model.getName() + "</option>");
                    }

                %>
            </select>
        </label>


        <label>
            <select name="assembledate">
                <option selected disabled>Год выпуска</option>
                <%
                    Calendar now = Calendar.getInstance();
                    for (int i = now.get(Calendar.YEAR); i > 1990; i--) {
                        out.println("<option value=" + i + ">" + i + "</option>");
                    }
                %>
            </select>
        </label>

        <label>
            <select name="color">
                <option selected disabled>Цвет</option>
                <%
                    Collection<Color> colors = (Collection<Color>) ((Map<String, Object>) request.getAttribute("param")).get("colors");

                    for (Color color : colors) {
                        out.println("<option value=" + color.getId() + ">" + color.getName() + "</option>");
                    }

                %>
            </select>
        </label>

        <label>
            <select name="engine">
                <option selected disabled>Объем</option>
                <%
                    Collection<Engine> engines = (Collection<Engine>) ((Map<String, Object>) request.getAttribute("param")).get("engines");
                    for (Engine engine : engines) {
                        out.println("<option value=" + engine.getId() + ">" + engine.getName() + "</option>");
                    }

                %>
            </select>
        </label>

        <label>
            <select name="numbeerofseats">
                <option selected disabled>Количество сидений</option>
                <%
                    for (int i = 1; i < 5; i++) {

                        out.println("<option value=" + i + ">" + i + "</option>");
                    }

                %>
            </select>
        </label>

        <input type="text" name="dayprice" required pattern="^[1-9][0-9]+$">Цена

        <input type="submit" value="Добавить"></p>


</form>

</body>
</html>