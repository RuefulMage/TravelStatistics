package ABD.Controllers;

import ABD.Models.Marker;
import ABD.Models.User;
import ABD.Repos.HibernateMarkersRepo;
import ABD.Repos.HibernateUsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/data")
public class DataController {
    @Autowired
    HibernateMarkersRepo hibernateMarkersRepo;

    @Autowired
    HibernateUsersRepo hibernateUsersRepo;

    @GetMapping("/data")
    public ModelAndView getMarkers(){
        User user = hibernateUsersRepo.findByUserName("TestyMage");
        List<Marker> markerList = hibernateMarkersRepo.findByUser(user);
        System.out.println(markerList);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("markerList");
        modelAndView.addObject("markerList", markerList);
        return modelAndView;
    }


}
