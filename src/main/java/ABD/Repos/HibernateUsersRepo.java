package ABD.Repos;

import ABD.Models.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HibernateUsersRepo extends CrudRepository<User, Integer> {
    User findByUserName(String userName);
    User findByEmail(String email);
}
