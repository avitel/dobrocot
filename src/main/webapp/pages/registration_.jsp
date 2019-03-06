<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<title>регистрация</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<body>

<div class="w3-bar w3-black">
    <a href="${pageContext.request.contextPath}/" class="w3-bar-item w3-button w3-mobile"><i class="fa fa-home"></i> Главная</a>
    <a href="${pageContext.request.contextPath}/login" class="w3-bar-item w3-button w3-right w3-mobile"><i class="fa fa-sign-in"></i> Войти</a>
</div>

<br><br>

<div class="w3-content w3-panel ${messageStyle}">
    <h3>${WarningMessage}</h3>
</div>

<div class="w3-content" style="max-width:600px">
    <form method="post" action="${pageContext.request.contextPath}/registration" class="w3-container w3-text-dark-grey w3-card-4 w3-light-grey w3-margin  w3-border">

        <h2 class="w3-center">Регистрация нового пользователя</h2>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user-plus"></i></div>
            <div class="w3-rest">
                <input id="userName" class="w3-input w3-border w3-round" name="name" type="text" placeholder="Имя" required pattern="[A-Za-z0-9]{3,20}" autofocus onkeyup='check();'>
            </div>
        </div>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user-circle-o"></i></div>
            <div class="w3-rest">
                <input id="userLogin" class="w3-input w3-border w3-round" name="login" type="text" placeholder="Логин"  required pattern="[A-Za-z0-9]{3,20}" onkeyup='check();'>
            </div>
        </div>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-key"></i></div>
            <div class="w3-rest">
                <input id="password" class="w3-input w3-border w3-round" name="passOrig" placeholder="Пароль" type="password"  required pattern="^[a-zA-Z0-9]{3,20}$" onkeyup='check();'>
            </div>
        </div>

        <div class="w3-row w3-section">
            <div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-repeat "></i></div>
            <div class="w3-rest">
                <input id="confirm_password" class="w3-input w3-border w3-round" type="password" name="pass" required pattern="^[a-zA-Z0-9]{3,20}$" onkeyup='check();' placeholder="Подтверждение пароля">
                <span id='message'></span>
            </div>
        </div>

        <button id="idSubmit" class="w3-button w3-block w3-section w3-dark-grey w3-ripple w3-padding" disabled>Зарегистрировать</button>

    </form>
</div>



<script>
    function enableBtn(btnId){
        document.getElementById(btnId).removeAttribute("disabled");
    }

    function disableBtn(btnId){
        document.getElementById(btnId).setAttribute("disabled", "true");
    }

    var check = function() {
        if (document.getElementById('password').value ==
            document.getElementById('confirm_password').value &&
            document.getElementById('userName').value != "" &&
            document.getElementById('userLogin').value != "" &&
            document.getElementById('password').value != "" &&
            document.getElementById('userLogin').value.length > 2 &&
            document.getElementById('userName').value.length > 2 &&
            document.getElementById('password').value.length > 2
        ) {
            document.getElementById('message').style.color = 'green';
            document.getElementById('message').innerHTML = 'пароли совпадают';
            enableBtn("idSubmit");
        } else {
            document.getElementById('message').style.color = 'red';

            if( document.getElementById('password').value !=
                document.getElementById('confirm_password').value){
                document.getElementById('message').innerHTML = 'пароли не совпадают';
            } else {
                document.getElementById('message').innerHTML = 'введены не все данные';
            }

            disableBtn("idSubmit");
        }
        if (document.getElementById('userName').value.length < 3 ||
            document.getElementById('userLogin').value.length < 3 ||
            document.getElementById('password').value.length < 3 ){
            document.getElementById('message').innerHTML = 'латиница и цифры, не менее 3х символов';
        }
    }

</script>
</body>
</html>
