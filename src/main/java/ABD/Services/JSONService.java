package ABD.Services;

import ABD.Models.DTOs.MarkerFromClientDTO;
import ABD.Models.Marker;
import ABD.Models.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class JSONService {
    public Marker convertString2Marker(String string, User user) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        MarkerFromClientDTO dto2 = objectMapper.readValue(string, MarkerFromClientDTO.class);
        List<Float> position = parsePosition(dto2.getPosition());
        System.out.println(dto2);
        Marker marker = new Marker();
        marker.setComment(dto2.getComment());
        marker.setCountry(dto2.getCountry());
        marker.setReasonOfTurism(dto2.getReason());
        marker.setUser(user);
        marker.setLatitude(position.get(1));
        marker.setLongitude(position.get(0));
        System.out.println(marker.getLatitude());
        System.out.println(marker.getLongitude());
        return marker;
    }

    public List<Float> parsePosition(String string){
        List<String> list = Arrays.asList(string.split(","));
        String latitude = list.get(0);
        String longitude = list.get(1);
        List<Float> position = new ArrayList<Float>();
        latitude = latitude.substring(1,latitude.length());
        longitude = longitude.substring(1,longitude.length() - 1);
        position.add(Float.parseFloat(latitude));
        position.add(Float.parseFloat(longitude));
        return position;
    }


}
