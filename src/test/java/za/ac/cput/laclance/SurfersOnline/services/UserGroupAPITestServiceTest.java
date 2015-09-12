package za.ac.cput.laclance.SurfersOnline.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.test.context.web.WebAppConfiguration;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import za.ac.cput.laclance.SurfersOnline.App;
import za.ac.cput.laclance.SurfersOnline.conf.factory.*;
import za.ac.cput.laclance.SurfersOnline.domain.*;
import za.ac.cput.laclance.SurfersOnline.repository.UserGroupRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class UserGroupAPITestServiceTest extends AbstractTestNGSpringContextTests{
    @Autowired
    private UserGroupService service;

    @Autowired
    private UserGroupRepository repository;

    private Long id;

    private List<Comment> comments;
    private List<User> users;
    private Map<String,String> values;
    private UserBasics userBasics;
    private UserContact contact;
    private UserExtras extras;
    private BasicInfo basics;

    @BeforeMethod
    public void setUp() throws Exception {
        comments = new ArrayList<>();
        values = new HashMap<>();
        values.put("comment","hello");
        values.put("firstName","Lance");
        values.put("lastName", "Coe");
        values.put("username","laclance");
        values.put("password", "123");

        userBasics = UserBasicsFactory.createUserBasics(values, 'm', "04/06/91");
        contact = UserContactFactory.createUserContact("l", "55");
        extras = UserExtrasFactory.createUserExtras("a", "a", "a");

        users = new ArrayList<>();

        User user1 = UserFactory.createUser(values, userBasics, contact, extras);
        values.put("username","ryan");
        values.put("password", "456");
        User user2 = UserFactory.createUser(values, userBasics, contact, extras);

        users.add(user1);
        users.add(user2);

        Comment comment = CommentFactory.createComment(values);
        comments.add(comment);

        basics = BasicInfoFactory.createBasicInfo("Old School Riders", "Riding Old School");
    }

    @AfterClass
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void create() throws Exception {
        UserGroup group = UserGroupFactory.createGroup(basics, comments, users);
        repository.save(group);
        id=group.getId();
        Assert.assertNotNull(group.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetGroup() throws Exception {
        UserGroup userGroup = service.findById(id);
        Assert.assertNotNull(userGroup);
    }

    @Test(dependsOnMethods = "testGetGroup")
    public void testGetGroups() throws Exception {
        List<UserGroup> userGroup = service.findAll();
        Assert.assertTrue(userGroup.size() == 1);
    }

    @Test(dependsOnMethods = "testGetGroups")
    public void testGetGroupUsers() throws Exception {
        List<User> users = service.findAllUsers(id);
        Assert.assertTrue(users.size() == 2);
    }
}
