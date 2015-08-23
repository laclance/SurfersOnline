package za.ac.cput.laclance.SurfersOnline.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public UserGroup getGroup(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<UserGroup> getAllGroups() {
        List<UserGroup> allGroups = new ArrayList<>();

        Iterable<UserGroup> groups = repository.findAll();
        for (UserGroup group : groups) {
            allGroups.add(group);
        }
        return allGroups;
    }

    @Override
    public List<User> getUsers(Long id) {
        return repository.findOne(id).getUsers();
    }
}

