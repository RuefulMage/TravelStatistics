package ABD.Services;

import ABD.Models.Country;
import ABD.Models.DTOs.CountryDTO;
import ABD.Models.DTOs.MarkerDTO;
import ABD.Models.Marker;
import ABD.Repos.HibernateCountriesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

//TODO поменять все на Optional
//TODO понять, как сделать без этого хардкода
@Service
public class CountriesServiceImpl implements CountriesService {
    private HibernateCountriesRepo countriesRepo;

    @Autowired
    public CountriesServiceImpl(HibernateCountriesRepo countriesRepo){
        this.countriesRepo = countriesRepo;
    }

    @Override
    public List<Country> getAllCountries() {
        return countriesRepo.findAll();
    }

    @Override
    public void saveCountry(Country country) {
        countriesRepo.save(country);
    }

    @Override
    public void updateCountry(Marker marker) {
        Country country = countriesRepo.findByCountry(marker.getCountry());
        if (country == null){
            country = new Country();
            country.setCountry(marker.getCountry());
        }
        String reason = marker.getReasonOfTurism();
        if (reason.equals("Посещение достопримечательностей")){
            country.setSightseingReason(country.getSightseingReason() + 1);
        }
        else if(reason.equals("Спортивный туризм")){
            country.setSportReason(country.getSportReason() + 1);
        }
        else if(reason.equals("Оздоровительный туризм")){
            country.setWellnessReason(country.getWellnessReason() + 1);
        }
        else if(reason.equals("Визит друзей/родственников")){
            country.setVisitReason(country.getVisitReason() + 1);
        }
        else if(reason.equals("Природные особенности")){
            country.setNatureReason(country.getNatureReason() + 1);
        }
        countriesRepo.save(country);
    }

    @Override
    public Country getCountry(String country) {
        return countriesRepo.findByCountry(country);
    }

    @Override
    public List<CountryDTO> getAllCountryDTO(){
        return convertToDTOArray(getAllCountries());
    }
    @Override
    public void removeCountry(Marker marker) {
        Country country = countriesRepo.findByCountry(marker.getCountry());
        String reason = marker.getReasonOfTurism();
        if (reason.equals("Посещение достопримечательностей")){
            country.setSightseingReason(country.getSightseingReason() - 1);
        }
        else if(reason.equals("Спортивный туризм")){
            country.setSportReason(country.getSportReason() - 1);
        }
        else if(reason.equals("Оздоровительный туризм")){
            country.setWellnessReason(country.getWellnessReason() - 1);
        }
        else if(reason.equals("Визит друзей/родственников")){
            country.setVisitReason(country.getVisitReason() - 1);
        }
        else if(reason.equals("Природные особенности")){
            country.setNatureReason(country.getNatureReason() - 1);
        }
        countriesRepo.save(country);
    }

    @Override
    public List<CountryDTO> convertToDTOArray(List<Country> countryList){
        ArrayList<CountryDTO> countryDTOS = new ArrayList<CountryDTO>();
        CountryDTO countryDTO;
        for (Country country:countryList) {
            countryDTO = new CountryDTO(country.getCountry(), country.getSightseingReason(),
                    country.getSportReason(), country.getWellnessReason(), country.getVisitReason(),
                    country.getNatureReason());
            countryDTOS.add(countryDTO);
        }
        return countryDTOS;
    }

}
