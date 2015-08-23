package za.ac.cput.laclance.SurfersOnline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;
import za.ac.cput.laclance.SurfersOnline.model.UserGroupResource;
import za.ac.cput.laclance.SurfersOnline.services.UserGroupService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/usergroup/**")
public class UserGroupPage {
    @Autowired
    private UserGroupService service;

    @RequestMapping(value="/{id}/", method= RequestMethod.GET)
    public  UserGroup getGroup(@PathVariable Long id) {
        return service.getGroup(id);
    }

    @RequestMapping(value="/{id}/users", method= RequestMethod.GET)
    public List<User> getGroupUsers(@PathVariable Long id) {
        return service.getUsers(id);
    }

    @RequestMapping(value="/all", method= RequestMethod.GET)
    public List<UserGroupResource> getGroups() {
        List<UserGroupResource> hateos = new ArrayList<>();
        List<UserGroup> userGroups = service.getAllGroups();

        for (UserGroup userGroup : userGroups) {
            UserGroupResource res = new UserGroupResource
                    .Builder(userGroup.getBasicInfo())
                    .users(userGroup.getUsers())
                    .resid(userGroup.getId())
                    .build();

            Link users = new
                    Link("http://localhost:8080/usergroup/"+res.getResid().toString())
                    .withRel("users");
            res.add(users);
            hateos.add(res);
        }
        return hateos;
    }
}
