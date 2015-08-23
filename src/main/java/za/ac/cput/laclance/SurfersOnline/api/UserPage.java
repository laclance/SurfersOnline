package za.ac.cput.laclance.SurfersOnline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.services.UserService;

import java.util.List;

@RestController
@RequestMapping(value="/api/user/**")
public class UserPage {
    @Autowired
    UserService service;

    @RequestMapping(value="/all", method= RequestMethod.GET)
    public List<User> getUsers() {
        return service.getAllUsers();
    }

    @RequestMapping(value="/{id}", method= RequestMethod.GET)
    public User getGroup(@PathVariable Long id) {
        return service.getUser(id);
    }
}
