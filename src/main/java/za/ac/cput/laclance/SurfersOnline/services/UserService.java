package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.User;

import java.util.List;

public interface UserService {
    User getUser(Long id);
    List<User> getAllUsers();
}
