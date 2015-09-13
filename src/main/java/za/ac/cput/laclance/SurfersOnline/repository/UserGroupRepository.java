package za.ac.cput.laclance.SurfersOnline.repository;

import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;
import org.springframework.data.repository.CrudRepository;

public interface UserGroupRepository extends CrudRepository<UserGroup,Long>{
    //public UserGroup findByName(String name);
}
