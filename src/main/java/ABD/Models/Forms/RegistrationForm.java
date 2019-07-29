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
        User user = new User();
        user.builder().userName(userName).fullName(fullName).email(email).password(passwordEncoder.encode(password))
                .passwordRepeat(passwordEncoder.encode(passwordRepeat));
        return user;
    }
}
