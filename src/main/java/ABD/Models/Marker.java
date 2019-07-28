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
public class Marker{
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    @Column(unique = true, nullable = false, name = "marker_id")
    private int id;

    @Column(name="comment")
    @Size(max=200, message="Comment must be less than 200 characters long")
    private String comment;

    @NotBlank
    @Column(name="longitude")
    private float longitude;

    @NotBlank
    @Column(name = "latitude")
    private float latitude;

    @NotBlank
    @Column(name="reasonOfTurism")
    private String reasonOfTurism;

    @NotBlank
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User user;
}
