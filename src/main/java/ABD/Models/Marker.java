package ABD.Models;

import lombok.*;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Data
@Entity
@NoArgsConstructor(force=true)
@Table(name = "markers")
@AllArgsConstructor
public class Marker implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "marker_id")
    private int id;

    @Column(name="comment")
    private String comment;


    @Column(name="country")
    private String country;

    @Column(name="longitude")
    private float longitude;

    @Column(name = "latitude")
    private float latitude;

    @NotBlank
    @Column(name="reasonOfTurism")
    private String reasonOfTurism;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;

}
