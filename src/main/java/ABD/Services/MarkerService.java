package ABD.Services;

import ABD.Models.DTOs.MarkerDTO;
import ABD.Models.Marker;
import ABD.Models.User;

import java.util.ArrayList;
import java.util.List;

public interface MarkerService {
    Marker findMarker(int id);
    List<Marker> findUsersMarkers(User user);
    List<Marker> findAllMarkers();
    ArrayList<MarkerDTO> getAllMarkerDTOs();
    ArrayList<MarkerDTO> getUsersMarkerDTOs(User user);
    void saveMarkers(Marker marker);
    void removeMarker(int id);
}
