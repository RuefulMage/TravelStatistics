package ABD.Controllers;

import ABD.Services.UserServiceImpl;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserServiceImpl userService;
    private UserDetailsService userDetailsService;


}
