package za.ac.cput.laclance.SurfersOnline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import za.ac.cput.laclance.SurfersOnline.App;
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserBasicsFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserContactFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserExtrasFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserFactory;
import za.ac.cput.laclance.SurfersOnline.domain.*;
import za.ac.cput.laclance.SurfersOnline.repository.UserRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class UserServiceTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private UserService service;
    @Autowired
    private UserRepository repository;

    private Long id;
    private Map<String,String> values;
    private UserBasics userBasics;
    private UserContact contact;
    private UserExtras extras;

    @BeforeClass
    public void setUp() throws Exception {
        values = new HashMap<>();
        values.put("firstName","Lance");
        values.put("lastName", "Coe");
        values.put("username","laclance");
        values.put("password", "123");

        userBasics = UserBasicsFactory.createUserBasics(values, 'm', "04/06/91");
        contact = UserContactFactory.createUserContact("l", "55");
        extras = UserExtrasFactory.createUserExtras("a", "a", "a");
    }

    @AfterClass
    public void tearDown() throws Exception {
         repository.deleteAll();
    }

    @Test
    public void create() throws Exception {
        User user = UserFactory.createUser(values, userBasics, contact, extras);
        repository.save(user);
        id=user.getId();
        Assert.assertNotNull(user.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetUser() throws Exception {
       User user = service.getUser(id);
        Assert.assertNotNull(user);
    }

    @Test(dependsOnMethods = "testGetUser")
    public void testGetUsers() throws Exception {
        List<User> users = service.getAllUsers();
        Assert.assertTrue(users.size() == 1);
    }
}
