package ABD.Models.Forms;

import ABD.Models.User;
import lombok.Data;
import org.springframework.security.crypto.password.PasswordEncoder;

@Data
public class RegistrationForm {
    private String userName;
    private String fullName;
    private String email;
    private String password;
    private String passwordRepeat;

    public User toUser(PasswordEncoder passwordEncoder){
        User user = new User(userName, fullName, password, passwordRepeat,email);
        return user;
    }
}
