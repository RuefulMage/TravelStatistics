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

    // $('body').on('click', function () {
    // alert('let go');
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
                        marker.country = result[i].long_name;
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
                data.country = "Russia";
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

/**
 * Removes given marker from map.
 * @param {!google.maps.Marker} marker A google.maps.Marker instance that will be removed.
 * @param {!string} markerId Id of marker.
 */
var removeMarker = function(marker, markerId) {
    marker.setMap(null); // set markers setMap to null to remove it from map
    markers.splice(markerId, 1); // delete marker instance from markers object

};
