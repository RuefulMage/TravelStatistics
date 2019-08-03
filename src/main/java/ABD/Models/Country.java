package ABD.Models;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor(force=true)
@Table(name = "countries")
@AllArgsConstructor
public class Country implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "countries_id")
    private int id;

    @Column(name="country")
    private String country;

    @Column(name="sightseingReason")
    private int sightseingReason;

    @Column(name = "sportReason")
    private int sportReason;

    @Column(name="wellnessReason")
    private int wellnessReason;


    @Column(name="visitReason")
    private int visitReason;

    @Column(name="natureReason")
    private int natureReason;
}
