package ABD.Controllers;

import ABD.Models.Forms.RegistrationForm;
import ABD.Models.User;
import ABD.Services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private PasswordEncoder passwordEncoder;
    private UserServiceImpl userService;

    @Autowired
    public RegistrationController(UserServiceImpl userService, PasswordEncoder passwordEncoder){
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    private String showRegistrationForm(ModelMap map) {
        return "registration";
    }

    @GetMapping
    public String registration(Authentication authentication, ModelMap modelMap){
        if (authentication != null){
            return "redirect:home";
        }
        modelMap.put("user", new User());
        return showRegistrationForm(modelMap);
    }

    @PostMapping
    public String registerHandler(
            @ModelAttribute("user") @Valid User user,
            BindingResult result,
            ModelMap modelMap)
    {
        if(!result.hasErrors()){
            try{
                userService.saveUser(user);
                return "redirect:home";
            }catch(DuplicateKeyException ex){
                result.rejectValue("username", "entry.duplicate", "There is account with such email already.");
            }
        }
        else{
            modelMap.addAttribute("user", user);
        }
        return showRegistrationForm(modelMap);
    }




}
