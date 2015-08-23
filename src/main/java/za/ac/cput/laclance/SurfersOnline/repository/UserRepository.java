package za.ac.cput.laclance.SurfersOnline.repository;

import za.ac.cput.laclance.SurfersOnline.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User,Long>{
    //public User findByUserName(String code);
}
