package za.ac.cput.laclance.SurfersOnline.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.ac.cput.laclance.SurfersOnline.conf.factory.*;

import java.util.HashMap;
import java.util.Map;

public class UserTest {
    private UserBasics basics;
    private UserContact contact;
    private UserExtras extras;
    private Map<String,String> values;

    @Before
    public void setUp() throws Exception {
        values = new HashMap<>();
        values.put("firstName","Lance");
        values.put("lastName", "Coe");
        values.put("username","laclance");
        values.put("password", "123");

        basics = UserBasicsFactory.createUserBasics(values, 'm', "04/06/91");
        contact = UserContactFactory.createUserContact("l", "55");
        extras = UserExtrasFactory.createUserExtras("a", "a", "a");
    }

    @Test
    public void testUser() throws Exception {
        User user = UserFactory.createUser(values, basics, contact, extras);
        Assert.assertEquals("laclance", user.getUsername());
    }
}
