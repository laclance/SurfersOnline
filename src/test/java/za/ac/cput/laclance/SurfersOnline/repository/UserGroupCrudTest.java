package za.ac.cput.laclance.SurfersOnline.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.*;
import za.ac.cput.laclance.SurfersOnline.App;
import za.ac.cput.laclance.SurfersOnline.conf.factory.*;
import za.ac.cput.laclance.SurfersOnline.domain.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class UserGroupCrudTest extends AbstractTestNGSpringContextTests{
    private Long id;

    private List<Comment> comments;
    private List<User> users;
    private Map<String, String> values;
    private BasicInfo basics;
    private UserBasics userBasics;
    private UserContact contact;
    private UserExtras extras;

    @Autowired
    private UserGroupRepository repository;

    @BeforeClass
    public void setUp() throws Exception {
        comments = new ArrayList<>();
        Map values = new HashMap<>();
        values.put("comment","hello");
        users = new ArrayList<>();
        values = new HashMap<>();
        values.put("firstName","Lance");
        values.put("lastName", "Coe");
        values.put("username","laclance");
        values.put("password", "123");

        userBasics = UserBasicsFactory.createUserBasics(values, 'm', "gf");
        contact = UserContactFactory.createUserContact("l", "55");
        extras = UserExtrasFactory.createUserExtras("a", "a", "a");

        User user1 = UserFactory.createUser(values, userBasics, contact, extras);
        values.put("username","ryan");
        values.put("password", "456");
        User user2 = UserFactory.createUser(values, userBasics, contact, extras);

        users.add(user1);
        users.add(user2);

        Comment comment = CommentFactory.createComment(values, "10/10/2015");
        comments.add(comment);

        basics = BasicInfoFactory.createBasicInfo("Low Riders", "Riding low");
    }

    @AfterClass
    public void tearDown() throws Exception {
       repository.deleteAll();
    }

    @Test
    public void create() throws Exception{
        UserGroup group = UserGroupFactory.createGroup(basics, comments, users);
        repository.save(group);
        id = group.getId();
        Assert.assertNotNull(group.getId());
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception {
        UserGroup group = repository.findOne(id);
        Assert.assertNotNull(group.getId());
        Assert.assertEquals("Low Riders", group.getBasicInfo().getName());
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception {
        UserGroup group = repository.findOne(id);

        BasicInfo basics = BasicInfoFactory.createBasicInfo("High Riders", "Riding High");

        UserGroup newGroup = new UserGroup.Builder(basics)
                .copy(group)
                .basicInfo(basics)
                .build();

        repository.save(newGroup);

        UserGroup updatedGroup = repository.findOne(id);
        Assert.assertEquals("High Riders", updatedGroup.getBasicInfo().getName());
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        UserGroup group = repository.findOne(id);
        repository.delete(group);

        UserGroup deletedGroup = repository.findOne(id);
        Assert.assertNull(deletedGroup);
    }
}
