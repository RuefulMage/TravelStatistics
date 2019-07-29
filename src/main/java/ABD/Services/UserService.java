package ABD.Services;

import ABD.Models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    void saveUser(User user);


    User findUserByEmail(String email);
}
