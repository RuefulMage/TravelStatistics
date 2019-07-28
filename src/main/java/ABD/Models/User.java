package ABD.Models;

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
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @Column(unique = true, nullable = false, name ="user_id")
    private int user_id;

    @NotBlank
    @Column(unique = true, nullable = false, name ="userName")
    @Size(min=6, message="Username must be at least 6 characters long")
    private String userName;

    @NotBlank
    @Column(nullable = false, name ="fullName")
    private String fullName;

    @NotBlank
    @Column(nullable = false, name ="password")
    @Size(min=6, message="Password must be at least 6 characters long")
    private String password;

    @NotBlank
    @Transient
    private String passwordRepeat;

    @NotBlank
    @Column(nullable = false, unique = true, name = "email")
    @Pattern(regexp = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,255}$", message = "Wrong email!")
    private String email;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
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

//    private boolean active;
//    private String googleName;
//    private String googleUsername




}
