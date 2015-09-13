package za.ac.cput.laclance.SurfersOnline.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.repository.UserRepository;
import za.ac.cput.laclance.SurfersOnline.services.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserRepository repository;

    @Override
    public User findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public User save(User entity) {
        return repository.save(entity);
    }

    @Override
    public User update(User entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(User entity) {
        repository.delete(entity);

    }

    @Override
    public List<User> findAll() {
        List<User> allUsers = new ArrayList<>();
        Iterable<User> users = repository.findAll();
        for(User user : users) {
            allUsers.add(user);
        }
        return allUsers;
    }
}
