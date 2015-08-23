package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;
import java.util.List;

public interface UserGroupService {
    UserGroup getGroup(Long id);
    List<UserGroup> getAllGroups();
    List<User> getUsers(Long id);
}
