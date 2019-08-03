package ABD.Services;

import ABD.Models.Country;
import ABD.Models.DTOs.CountryDTO;
import ABD.Models.Marker;

import java.util.List;

public interface CountriesService {
    List<Country> getAllCountries();
    void saveCountry(Country country);
    void updateCountry(Marker marker);
    Country getCountry(String country);
    void removeCountry(Marker marker);
    List<CountryDTO> convertToDTOArray(List<Country> countryList);
    List<CountryDTO> getAllCountryDTO();
}
