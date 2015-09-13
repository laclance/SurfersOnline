package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.Comment;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;
import java.util.List;

public interface UserGroupService extends Services<UserGroup,Long> {
    List<Comment> findAllComments(Long id);
    List<User> findAllUsers(Long id);
}
