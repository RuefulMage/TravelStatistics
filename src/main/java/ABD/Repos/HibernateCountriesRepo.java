package ABD.Repos;

import ABD.Models.Country;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HibernateCountriesRepo extends CrudRepository<Country,Integer>{
    List<Country> findAll();
    Country findByCountry(String country);
}
