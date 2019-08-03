package ABD.Models;

import ABD.Models.Enums.UserRoleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.*;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "users")
public class User implements UserDetails {

    public User(String userName, String fullName,String password,String passwordRepeat,  String email) {
        this.userName = userName;
        this.fullName = fullName;
        this.password = password;
        this.passwordRepeat = passwordRepeat;
        this.email = email;
    }

    @Id
    @Column(unique = true, nullable = false, name ="user_id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int user_id;

    @Column(name ="userName")
    @Size(min=6, message="Username must be at least 6 characters long")
    private String userName;

    @Column(name ="fullName")
    private String fullName;

    @Column(name ="password")
    @Size(min=6, message="Password must be at least 6 characters long")
    private String password;

    @Transient
    private String passwordRepeat;

    @Column(name = "email")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,255}$", message = "Wrong email!")
    private String email;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE, mappedBy = "user")
    private List<Marker> markers = new ArrayList<Marker>();

    @Enumerated(value = EnumType.STRING)
    private UserRoleEnum userRole;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String userRole = getUserRole().name();
        SimpleGrantedAuthority authority = new SimpleGrantedAuthority(userRole);
        return Collections.singletonList(authority);
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

}
