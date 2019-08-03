<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Вход</title>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>
<body>
<header class="page-header">
    <nav class="main-nav">
        <a class="main-nav__logo" href="/"></a>
        <ul class="main-nav__list main-nav__list--login">
            <li class="main-nav__item main-nav__item--active">
                <a class="main-nav__link main-nav__link--active" href="/abd_war/">Главная</a>
            </li>
        </ul>
    </nav>
</header>
<main class="page-main page-main">
    <section class="login-window">
        <h1 class="login-window__headline">
            Вход на сайт
        </h1>
        <div class="login-form">
            <form class="login-form__field" action="/abd_war/login" method="post">
                <ul class="user-form">
                    <li class="user-form__login">
                        <label for="yourlogin"></label>
                        <div class="bottom-line">
                            <input type="login" id="yourlogin" name="userName" value="" placeholder="Логин" required>
                        </div>
                    </li>
                    <li class="user-form__password">
                        <label for="password"></label>
                        <div class="bottom-line">
                            <input type="password" id="password" name="password" placeholder="Пароль" required>
                        </div>
                    <li class="user-form__sign-in">
                        <input type="submit" value="Войти">
                    </li>
                </ul>
            </form>
            <div class="sideline">
            </div>
            <div class="user-registration">
                <p class="user-registration__text">У Вас еще нет аккаунта?
                </p>
                <a href="/abd_war/registration" class="user-registration__link" >
                    <p>Создать аккаунт.
                    </p></a>
            </div>
        </div>
    </section>
</main>
</body>
</html>
