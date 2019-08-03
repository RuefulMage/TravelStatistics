package ABD.Controllers;

import ABD.Models.Marker;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/personalArea")
public class PersonalAreaController {
    @GetMapping
    public String getPersonal(Authentication authentication){
        if (authentication == null){
            return "redirect:login";
        }
        return "PersonalArea";
    }
}
