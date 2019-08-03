package ABD.Models.DTOs;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CountryDTO {
    @JsonProperty("id")
    private String country;

    @JsonProperty("sightseingReason")
    private int sightseingReason;

    @JsonProperty("sportReason")
    private int sportReason;

    @JsonProperty("wellnessReason")
    private int wellnessReason;

    @JsonProperty("visitReason")
    private int visitReason;

    @JsonProperty("natureReason")
    private int natureReason;
}

