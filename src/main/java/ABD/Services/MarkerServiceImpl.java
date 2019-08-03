package ABD.Services;

import ABD.Models.DTOs.MarkerDTO;
import ABD.Models.Marker;
import ABD.Models.User;
import ABD.Repos.HibernateMarkersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

//TODO поменять все на Optional
@Service
public class MarkerServiceImpl implements MarkerService{
    private UserService userService;
    private HibernateMarkersRepo markersRepo;
    private CountriesService countriesService;

    @Autowired
    public MarkerServiceImpl(UserService userService, HibernateMarkersRepo markersRepo, CountriesService countriesService) {
        this.userService = userService;
        this.markersRepo = markersRepo;
        this.countriesService = countriesService;
    }

    @Override
    public Marker findMarker(int id) {
        Optional<Marker> marker = markersRepo.findById(id);
        if (marker.isPresent()){
            return marker.get();
        }throw new IllegalArgumentException("Marker not found");
    }

    @Override
    public List<Marker> findUsersMarkers(User user) {
        return markersRepo.findByUser(user.getUser_id());
    }

    @Override
    public List<Marker> findAllMarkers() {

        return markersRepo.findAll();
    }



    @Override
    public ArrayList<MarkerDTO> getAllMarkerDTOs() {
        ArrayList<Marker> arrayList = (ArrayList<Marker>) markersRepo.findAll();
        MarkerDTO markerDTO;
        ArrayList<MarkerDTO> markerDTOS = convertToDTOArray(arrayList);
        return markerDTOS;
    }


    @Override
    public ArrayList<MarkerDTO> getUsersMarkerDTOs(User user) {
        ArrayList<Marker> allMarkersList = (ArrayList<Marker>) findAllMarkers();
        ArrayList<Marker> userMarkers = new ArrayList<Marker>();
        int user_id = user.getUser_id();
        for (Marker marker: allMarkersList) {
            if(marker.getUser().getUser_id() == user_id){
                userMarkers.add(marker);
            }
        }
        ArrayList<MarkerDTO> markerDTOS = convertToDTOArray(userMarkers);
        return markerDTOS;
    }

    @Override
    public void saveMarkers(Marker marker) {

        markersRepo.save(marker);
        countriesService.updateCountry(marker);
    }

    @Override
    public void removeMarker(int id) {
        Optional<Marker> marker = markersRepo.findById(id);
        if (marker.isPresent()) {
            countriesService.removeCountry(marker.get());
            markersRepo.deleteById(id);
        }
    }

    public ArrayList<MarkerDTO> convertToDTOArray(List<Marker> markerList){
        ArrayList<MarkerDTO> markerDTOS = new ArrayList<MarkerDTO>();
        MarkerDTO markerDTO;
        for (Marker marker:markerList) {
            markerDTO = new MarkerDTO(marker.getId(), marker.getComment(),
                    marker.getCountry(), marker.getReasonOfTurism(), marker.getLongitude()
                    , marker.getLatitude());
            markerDTOS.add(markerDTO);
        }
        return markerDTOS;
    }

}