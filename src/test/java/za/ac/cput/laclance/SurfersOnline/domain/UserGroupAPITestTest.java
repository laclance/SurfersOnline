package za.ac.cput.laclance.SurfersOnline.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.ac.cput.laclance.SurfersOnline.conf.factory.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserGroupAPITestTest {
    private BasicInfo basics;
    private List<User> users;
    private UserBasics userBasics;
    private UserContact contact;
    private UserExtras extras;
    private List<Comment> comments;

    @Before
    public void setUp() throws Exception {
        Map<String,String> values = new HashMap<>();
        values.put("firstName","Lance");
        values.put("lastName", "Coe");
        values.put("username","laclance");
        values.put("password", "123");
        values.put("comment","hello");
        values.put("username","laclance");

        basics = BasicInfoFactory.createBasicInfo("Old School Riders", "Riding Old School");
        userBasics = UserBasicsFactory.createUserBasics(values, 'm', "04/06/91");
        contact = UserContactFactory.createUserContact("l", "55");
        extras = UserExtrasFactory.createUserExtras("a", "a", "a");
        users = new ArrayList<>();

        User user = UserFactory.createUser(values, userBasics, contact, extras);
        users.add(user);

        Comment comment = CommentFactory.createComment(values, "10/10/2015");
        comments.add(comment);
    }

    @Test
    public void testCreateGroup() throws Exception {
        UserGroup Group = UserGroupFactory.createGroup(basics, comments, users);
        Assert.assertEquals("Old School Riders", Group.getBasicInfo().getName());
    }
}
