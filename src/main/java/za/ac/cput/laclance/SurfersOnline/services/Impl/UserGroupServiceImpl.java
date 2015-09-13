package za.ac.cput.laclance.SurfersOnline.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;
import za.ac.cput.laclance.SurfersOnline.domain.Comment;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;
import za.ac.cput.laclance.SurfersOnline.repository.UserGroupRepository;
import za.ac.cput.laclance.SurfersOnline.services.UserGroupService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserGroupServiceImpl implements UserGroupService
{
    @Autowired
    UserGroupRepository repository;

    @Override
    public UserGroup findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public UserGroup save(UserGroup entity) {
        return repository.save(entity);
    }

    @Override
    public UserGroup update(UserGroup entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(UserGroup entity) {
        repository.delete(entity);

    }

    @Override
    public List<UserGroup> findAll() {
        List<UserGroup> allGroups = new ArrayList<>();

        Iterable<UserGroup> groups = repository.findAll();
        for (UserGroup group : groups) {
            allGroups.add(group);
        }
        return allGroups;
    }

    @Override
    public List<Comment> findAllComments(Long id) {return repository.findOne(id).getComments();}

    @Override
    public List<User> findAllUsers(Long id) {
        return repository.findOne(id).getUsers();
    }
}

