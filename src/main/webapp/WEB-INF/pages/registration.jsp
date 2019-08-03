<%--<%@ page contentType="text/html; charset=UTF-8" %>--%>
<%--<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>--%>
<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%--<!DOCTYPE html>--%>
<%--<html>--%>
<%--<head>--%>
<%--    <meta charset="UTF-8">--%>
<%--</head>--%>
<%--<body>--%>
<%--<form action="/abd_war/registration" method="POST">--%>
<%--    <input name="userName" type="text"><br>--%>
<%--    <input name="fullName" type="text"><br>--%>
<%--    <input name="email" type="text"><br>--%>
<%--    <input name="password" type="password"><br>--%>
<%--    <input name="passwordRepeat" type="password"><br>--%>
<%--    <input type="submit">--%>
<%--</form>--%>
<%--</body>--%>
<%--</html>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html lang="ru">
<head>
    <meta charset="utf-8">
    <title>Регистрация</title>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>
<body>
<header class="page-header">
    <nav class="main-nav">
        <a class="main-nav__logo" href="main.html">
        </a>
        <ul class="main-nav__list main-nav__list--login">
            <li class="main-nav__item main-nav__item--active">
                <a class="main-nav__link main-nav__link--active" href="index.html">Главная</a>
            </li>
            <li class="main-nav__item">
                <a class="main-nav__link" href="photo.html">Фото и видео</a>
            </li>
            <li class="main-nav__item">
                <a class="main-nav__link" href="form.html">Форма отзыва</a>
            </li>
            <li class="main-nav__item">
                <a class="main-nav__link" href="#"></a>
            </li>
        </ul>
    </nav>
</header>
<main class="page-main page-main">
    <section class="login-window">
        <h1 class="login-window__headline">
            Регистрация
        </h1>
        <c:if test="${error != null}">
            <div class="text-danger">
                <span>${status.errorMessage}</span>
            </div>
        </c:if>
        <div class="login-form">
            <form class="login-form__field" action="/abd_war/registration" method="post">
                <ul class="user-form">
                    <li class="user-form__login">
                        <label for="yourlogin"></label>
                        <div class="bottom-line">
                            <input type="textarea" id="yourlogin" name="fullName" value="" placeholder="Имя Фамилия Отчество" required>
                        </div>
                    </li>
                    <li class="user-form__nickname">
                        <label for="password"></label>
                        <div class="bottom-line">
                            <input type="textarea" id="nickname" name="userName" placeholder="Никнейм" required>
                        </div>
                    </li>
                    <li class="user-form__password">
                        <label for="password"></label>
                        <div class="bottom-line">
                            <input type="textarea" id="password" name="password" placeholder="Пароль" required>
                        </div>
                    </li>
                    <li class="user-form__password-again">
                        <label for="password"></label>
                        <div class="bottom-line">
                            <input type="textarea" id="passwordRepeat" name="passwordRepeat" placeholder="Повторите пароль" required>
                        </div>
                    </li>
                    <li class="user-form__email">
                        <label for="password"></label>
                        <div class="bottom-line">
                            <input type="email" id="email" name="email" placeholder="anything@books.ru" required>
                        </div>
                    </li>
                    <li class="user-form__sign-in">
                        <input type="submit" value="Зарегистрироваться">
                    </li>
                </ul>
            </form>
            <div class="sideline">
            </div>
            <div class="user-registration">
                <p class="user-registration__text">У Вас уже есть аккаунт?
                </p>
                <a href="/abd_war/login" class="user-registration__link" >
                    <p>Войти в аккаунт.
                    </p></a>
            </div>
        </div>
    </section>
</main>
</body>
</html>