package ABD.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MarkerFromClientDTO implements Serializable {
//    @JsonProperty("id")
//    private int id;

    @JsonProperty("comment")
    private String comment;

    @JsonProperty("country")
    private String country;

    @JsonProperty("reason")
    private String reason;

//    @JsonProperty("longitude")
//    private float longitude;
//
//    @JsonProperty("latitude")
//    private float latitude;

//    @JsonProperty("position")
//    private float[] position;

    @JsonProperty("position")
    private String position;
}
