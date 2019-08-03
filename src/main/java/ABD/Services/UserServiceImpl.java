package ABD.Services;

import ABD.Models.Enums.UserRoleEnum;
import ABD.Models.User;
import ABD.Repos.HibernateUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

//TODO поменять все на Optional
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private HibernateUsersRepo userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

//    @Autowired
//    public UserServiceImpl(HibernateUsersRepo userRepository, PasswordEncoder passwordEncoder) {
//        this.userRepository = userRepository;
//        this.passwordEncoder = passwordEncoder;
//    }



    @Override
    public void saveUser(User user) {
        if (userRepository.findByUserName(user.getUsername()) != null) {
            throw new DuplicateKeyException("User with this nickname already exist!");
        }
        if (userRepository.findByEmail(user.getEmail()) != null) {
            throw new DuplicateKeyException("User with this email already exist");
        }
        user.setUserRole(UserRoleEnum.USER);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setPasswordRepeat(user.getPassword());
        userRepository.save(user);
    }


    @Override
    public User findUserByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }


    @Override
    public User findUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        return this.userRepository.findByUserName(userName);
    }
}
