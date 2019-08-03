package ABD.Controllers;

import ABD.Models.DTOs.MarkerDTO;
import ABD.Models.Marker;
import ABD.Models.User;
import ABD.Services.JSONService;
import ABD.Services.MarkerService;
import ABD.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/markers")
public class MarkersController {

    private UserService userService;
    private MarkerService markerService;
    private JSONService jsonService;



    @Autowired
    public MarkersController(UserService userService, MarkerService markerService, JSONService jsonService){
        this.userService = userService;
        this.markerService = markerService;
        this.jsonService = jsonService;
    }

    @GetMapping
    public @ResponseBody
    List<MarkerDTO> getMarkers(){
        List<MarkerDTO> list= markerService.getAllMarkerDTOs();
        return list;
    }

    @GetMapping("/personal")
    public List<MarkerDTO> getUsersMarkers(Authentication auth){
        User user = userService.findUserByUserName(auth.getName());
        List<MarkerDTO> list= markerService.getUsersMarkerDTOs(user);
        return list;
    }


    @ResponseBody
    @PostMapping("/add")
    public ResponseEntity addMarker( Authentication authentication, @RequestBody String string) throws IOException {
        User user = userService.findUserByUserName(authentication.getName());
        Marker marker = jsonService.convertString2Marker(string, user);
        markerService.saveMarkers(marker);
        return ResponseEntity.ok().build();
    }

    @ResponseBody
    @PostMapping("/remove")
    public ResponseEntity removeMarker( @RequestBody int i){
        markerService.removeMarker(i);
        return ResponseEntity.ok().build();
    }




}
