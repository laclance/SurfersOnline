package za.ac.cput.laclance.SurfersOnline.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.*;
import za.ac.cput.laclance.SurfersOnline.App;
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserBasicsFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserContactFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserExtrasFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserFactory;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.UserBasics;
import za.ac.cput.laclance.SurfersOnline.domain.UserContact;
import za.ac.cput.laclance.SurfersOnline.domain.UserExtras;

import java.util.HashMap;
import java.util.Map;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class UserCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    private Map<String, String> values;
    private UserBasics userBasics;
    private UserContact contact;
    private UserExtras extras;

    @Autowired
    private UserRepository repository;

    @BeforeClass
    public void setUp() throws Exception {
        values = new HashMap<>();
        values.put("firstName","Lance");
        values.put("lastName", "Coe");
        values.put("username","laclance");
        values.put("password", "123");
        userBasics = UserBasicsFactory.createUserBasics(values, 'm', "gf");
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
        id = user.getId();
        Assert.assertNotNull(user.getId());
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception {
        User user = repository.findOne(id);
        Assert.assertNotNull(user.getId());
        Assert.assertEquals(user.getUsername(), "laclance");
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception {
        User user = repository.findOne(id);

        userBasics = UserBasicsFactory.createUserBasics(values, 'f', "gf");
        User newUser = new User.Builder(values)
                .copy(user)
                .userbasics(userBasics)
                .build();

        repository.save(newUser);

        User updatedUser = repository.findOne(id);
        Assert.assertEquals('f', updatedUser.getUserBasics().getGender());
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        User user = repository.findOne(id);
        repository.delete(user);

        User deletedUser = repository.findOne(id);
        Assert.assertNull(deletedUser);
    }
}
