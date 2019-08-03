package ABD.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MarkerDTO {
    @JsonProperty("id")
    private int id;

    @JsonProperty("comment")
    private String comment;
    @JsonProperty("country")
    private String country;

    @JsonProperty("reason")
    private String reason;

    @JsonProperty("longitude")
    private float longitude;

    @JsonProperty("latitude")
    private float latitude;
}

