<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="security" %>

<html>
<head>
    <link href="<c:url value="/css/style.css" />" rel="stylesheet">
    <style>
        #map {
            height: 400px;
            width: 100%;
            z-index: 1;
        }

    </style>
    <script type="text/javascript" src="<c:url value="/js/jquery-3.4.1.js" />"></script>
</head>
<body>
<header class="page-header">
    <header class="page-header">
        <nav class="main-nav">
            <a class="main-nav__logo" href="main.html">
<%--                <img src="<c:url value="/js/logo.png"/>" height="90" alt="">--%>
            </a>
            <ul class="main-nav__list main-nav__list--login">
                <li class="main-nav__item">
                    <a class="main-nav__link" href="account.html">Личный кабинет</a>
                </li>

                <security:authorize access="isAuthenticated()">
                    <li><a href="<spring:url value="/logout" />">Log out</a></li>
                </security:authorize>
            </ul>
        </nav>
    </header>
    <h1>Личный кабинет</h1>
</header>
<div id="form">
    <form id="formId" class="form-horizontal save-form" style="display: none">
        <h1>Add me!</h1>
        <div class="controls" style="display: none;">
            <input type="text" name="coordinates">
        </div>
        <div class="serials" style="display: none;">
            <input type="text" name="country">
        </div>
        <div class="control-group">
            <label class="control-label" for="category">По какой причине вы хотите посетить это место?</label>
            <div class="controls">
                <select name="reason">
                    <option value="Посещение достопримечательностей">Посещение достопримечательностей</option>
                    <option value="Спортивный туризм">Спортивный туризм</option>
                    <option value="Оздоровительный туризм">Оздоровительный туризм</option>
                    <option value="Визит друзей/родственников">Визит друзей/родственников</option>
                    <option value="Природные особенности">Природные особенности</option>
                </select>
            </div>
        </div>
        <label class="control-label" for="description">Комментарий</label>
        <div class="controls">
            <textarea class="input-xlarge" name="comment" rows="3"></textarea>
            <div class="form-actions">
                <button type="submit" class="btn btn-primary">Сохранить маркер</button>
            </div>

        </div>
    </form>

</div>
<div id="map"></div>
<script>
    var map;
    var markers = {};
    var counter = -1;
    function initMap() {
        var geocoder = new google.maps.Geocoder();

        var uluru = {lat: -25.363, lng: 131.044};
        var map = new google.maps.Map(document.getElementById('map'), {
            zoom: 4,
            center: uluru
        });
        $(document).ready(function(){
            $.ajax({
                url: '/abd_war/markers/personal',
                type: 'GET',
                dataType: 'json',
                data: $(this).data('data')
            }).done(function(data) {
                // $.getJSON('http://localhost:8080/abd_war/markers', function(data){
                // alert(data);
                // alert(data[3].latitude);
                for (var i = 0; i < data.length; i++) {
                    var marker = new google.maps.Marker({
                        position: new google.maps.LatLng(data[i].longitude, data[i].latitude),
                        map: map,
                        id: markers.length
                    });
                    markers[marker.id] = marker;
                    bindMarkerEvents(marker);
                    marker.addListener('dblclick', function() {
                        infowindow.open(map, marker);
                        infowindow.setContent(data[i][1] + data[i][2]);
                    });
                }
            }).fail(function(error) {
                console.log(error);
            });
        });


        var addMarker = google.maps.event.addListener(map, 'click', function(e) {

            var lat = e.latLng.lat(); // lat of clicked point
            var lng = e.latLng.lng(); // lng of clicked point
            var id = markers.length;
            var marker = new google.maps.Marker({
                position: getLatLng(lat, lng),
                map: map,
                id: markers.length
            });
            map.panTo(getLatLng(lat, lng));
            markers[marker.id] = marker; // cache marker in markers object
            bindMarkerEvents(marker);
            var form =  $(".save-form").clone().show();
            var infowindow_content = form[0];

            var infowindow = new google.maps.InfoWindow({
                content: infowindow_content
            });


            geocoder.geocode({ 'latLng': e.latLng }, function (results, status) {
                if (status == google.maps.GeocoderStatus.OK) {
                    result=results[0].address_components;
                    var info=[];
                    for(var i=0;i<result.length;++i)
                    {
                        if(result[i].types[0]=="country")
                        {
                            marker.country = result[i].short_name;
                        }
                    }

                }
                else {
                    // alert('wow');
                }
            });


            marker.addListener('dblclick', function() {
                infowindow.open(map, marker);
                $('body').on('submit', '#formId', function(e) {
                    event.preventDefault();
                    $("input[name=coordinates]").val(marker.position);
                    $("input[name=country]").val(marker.country);
                    var data = {
                        comment: $("textarea[name=comment]", this).val(),
                        reason: $("select[name=reason]",this).val(),
                        position: $("input[name=coordinates]",this).val(),
                        country: $("input[name=country]",this).val()
                    };
                    data.country = "US";
                    var dataFields = "comment=" + data.comment + "&reason=" + data.reason + "&country=" + data.country+ "&position=" + data.position;
                    console.log(data);
                    console.log(dataFields);
                    $.ajax({

                        type: 'POST', url: '/abd_war/markers/add', data: JSON.stringify(data),
                        dataType: 'json',
                        contentType: 'application/json' ,success: function() {
                            alert('did it');
                        }
                });


                    infowindow.setContent('Причина по которой вы хотите посетить место: ' + '<br>' + data.reason + '<br>' + 'Ваш комментарий: ' + data.comment)
                    return false;

                });
            });


        });


        var getLatLng = function(lat, lng) {
            return new google.maps.LatLng(lat, lng);
        };
    }

    var bindMarkerEvents = function(marker) {
        google.maps.event.addListener(marker, "rightclick", function () {
            removeMarker(marker, marker.id); // remove it
        });
    };

    var removeMarker = function(marker, markerId) {
        $ajax({
            type: 'POST',
            url: '/abd_war/markers/remove', data: markerId,
            dataType: 'json',
            contentType: 'application/json' ,success: function() {
                alert('did it');
            }
        });
        marker.setMap(null); // set markers setMap to null to remove it from map
        markers.splice(markerId, 1); // delete marker instance from markers object
    };
</script>
<!--Load the API from the specified URL
* The async attribute allows the browser to render the page while the API loads
* The key parameter will contain your own API key (which is not needed for this tutorial)
* The callback parameter executes the initMap() function
-->

<script async defer src="https://maps.googleapis.com/maps/api/js?&callback=initMap"
        type="text/javascript"></script>
</body>
</html>
