package ABD.Repos;

import ABD.Models.Marker;
import ABD.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HibernateMarkersRepo extends CrudRepository<Marker,Integer> {
    List<Marker> findAll();
    List<Marker> findByUser(int user_id);
}
