package ABD.Controllers;
import ABD.Models.Country;
import ABD.Models.DTOs.CountryDTO;
import ABD.Services.CountriesService;
import ABD.Services.CountriesServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/countries")
@RestController
public class CountriesController {
    private CountriesService countriesService;

    @Autowired
    public CountriesController(CountriesService countriesService){
        this.countriesService = countriesService;
    }

    @GetMapping
    public List<CountryDTO> getAllCountries(){
        return countriesService.getAllCountryDTO();
    }
}
