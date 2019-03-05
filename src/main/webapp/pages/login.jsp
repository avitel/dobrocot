<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>Авторизация</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>

<div class="w3-bar w3-black">
    <a href="${pageContext.request.contextPath}/" class="w3-bar-item w3-button w3-mobile"><i class="fa fa-home"></i> Главная</a>
    <a href="${pageContext.request.contextPath}/registration" class="w3-bar-item w3-button w3-right w3-mobile"><i class="fa fa-sign-in"></i> Регистрация</a>
</div>

<br><br>


<!-- The Modal -->
<div id="idModal" class="w3-modal" style="${modalStyle}">
    <div class="w3-modal-content">
        <header class="w3-container w3-light-grey">
            <h4>Ошибка авторизации</h4>
        </header>
        <div class="w3-container">

            <div class="w3-panel w3-leftbar w3-red">
                <p>Доступ запрещен.</p>
            </div>

            <button class="w3-btn w3-dark-grey"
                    onclick="document.getElementById('idModal').style.display='none'"
                    class="w3-button w3-display-topright">Закрыть
            </button>
            <br><br>
        </div>
    </div>
</div>

<div class="w3-display-content">
    <div class="w3-content" style="max-width:600px">
        <form action="login" method="POST"
              class="w3-container w3-text-dark-grey w3-card-4 w3-light-grey w3-margin  w3-border">

            <h2 class="w3-center">Авторизация</h2>

            <div class="w3-row w3-section">
                <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user-circle-o"></i></div>
                <div class="w3-rest">
                    <input class="w3-input w3-border w3-round" name="username" type="text" placeholder="Логин" autofocus>
                </div>
            </div>


            <div class="w3-row w3-section">
                <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-key"></i></div>
                <div class="w3-rest">
                    <input class="w3-input w3-border w3-round" name="password" placeholder="Пароль" type="password">
                </div>
            </div>

            <button name="submit" type="submit" class="w3-button w3-block w3-section w3-dark-grey w3-ripple w3-padding">
                Войти
            </button>

        </form>
    </div>
</div>

</body>

</html>
