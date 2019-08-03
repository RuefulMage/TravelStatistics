<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>
<html>
<head>
    <style>
        html, body, #container {
            width: 100%;
            height: 100%;
            margin: 0;
            padding: 0;
        }
    </style>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-core.min.js" type="text/javascript"></script>
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-map.min.js" type="text/javascript"></script>
    <script src="https://cdn.anychart.com/geodata/1.2.0/custom/world/world.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/proj4js/2.3.15/proj4.js"></script>
    <script src="https://cdn.anychart.com/releases/8.6.0/js/anychart-data-adapter.min.js"></script>
    <script type="text/javascript" src="<c:url value="/js/jquery-3.4.1.js" />"></script>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
</head>
<body>
<header class="page-header">
    <nav class="main-nav">
        <a class="main-nav__logo" href="/"></a>
        <ul class="main-nav__list main-nav__list--login">
            <li class="main-nav__item">
                <a class="main-nav__link" href="/abd_war/personalArea">Личный кабинет</a>
            </li>
            <security:authorize access="isAuthenticated()">
                <li><a href="<spring:url value="/logout" />">Log out</a></li>
            </security:authorize>
            <security:authorize access="isAnonymous()">
                <li><a href="<spring:url value="/registration"/>">Register</a></li>
                <li><a href="<spring:url value="/login"/>">Log in</a></li>
            </security:authorize>
        </ul>
    </nav>
</header>
<div id="container">
</div>
<%--<script type="text/javascript">--%>

<%--    anychart.onDocumentReady(function() {--%>
<%--        // create map--%>

<%--        var map = anychart.map();--%>
<%--        var dataSet;--%>

<%--        // create data set--%>
<%--        $.ajax({--%>
<%--            url: '/abd_war/countries/',--%>
<%--            type: 'GET',--%>
<%--            dataType: 'json',--%>
<%--            data: $(this).data('data')--%>
<%--        }).done(function(data) {--%>
<%--            console.log(data);--%>
<%--            dataSet = anychart.data.set(data);--%>
<%--            console.log(dataSet);--%>
<%--        });--%>

<%--        console.log(dataSet);--%>
<%--        // create choropleth series--%>
<%--        series = map.choropleth(dataSet);--%>

<%--        // set geoIdField to 'id', this field contains in geo data meta properties--%>
<%--        series.tooltip().format(function(e){--%>
<%--            return "Посещение достопримечательностей: " + e.getData("natureReason") +"\n"+--%>
<%--                "Спортивный туризм: " + e.getData("sightseenReason") + "\n" +--%>
<%--                "Оздоровительный туризм" + e.getData("sportReason") + "\n"--%>
<%--            "Визит друзей/родственников: " + e.getData("visitReason") + "\n" +--%>
<%--            "Природные особенности: " + e.getData("wellnessReason")--%>
<%--        });--%>

<%--        // set map color settings--%>
<%--        series.colorScale(anychart.scales.linearColor('#deebf7', '#3182bd'));--%>
<%--        series.hovered().fill('#addd8e');--%>

<%--        // set geo data, you can find this map in our geo maps collection--%>
<%--        // https://cdn.anychart.com/#maps-collection--%>
<%--        map.geoData(anychart.maps['world']);--%>

<%--        //set map container id (div)--%>
<%--        map.container('container');--%>

<%--        //initiate map drawing--%>
<%--        map.draw();--%>
<%--    });--%>
<%--</script>--%>
<!--Load the API from the specified URL
* The async attribute allows the browser to render the page while the API loads
* The key parameter will contain your own API key (which is not needed for this tutorial)
* The callback parameter executes the initMap() function
-->







<script type="text/javascript">

    anychart.onDocumentReady(function() {
        // create map

        var map = anychart.map();

        // create data set
        $.ajax({
            url: '/abd_war/countries/',
            type: 'GET',
            dataType: 'json',
            data: $(this).data('data')
        }).done(function(data) {
            var dataSet = anychart.data.set(data);
            series = map.choropleth(dataSet);

            // set geoIdField to 'id', this field contains in geo data meta properties
            series.tooltip().format(function(e){
                return "Посещение достопримечательностей: " + e.getData("natureReason") +"\n"+
                    "Спортивный туризм: " + e.getData("sightseingReason") + "\n" +
                    "Оздоровительный туризм: " + e.getData("sportReason") + "\n" +
                "Визит друзей/родственников: " + e.getData("visitReason") + "\n" +
                "Природные особенности: " + e.getData("wellnessReason")
            });

            // set map color settings
            series.colorScale(anychart.scales.linearColor('#deebf7', '#3182bd'));
            series.hovered().fill('#addd8e');

            // set geo data, you can find this map in our geo maps collection
            // https://cdn.anychart.com/#maps-collection
            map.geoData(anychart.maps['world']);

            //set map container id (div)
            map.container('container');

            //initiate map drawing
            map.draw();
        });

        // create choropleth series

    });
</script>
</body>
</html>