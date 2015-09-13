package za.ac.cput.laclance.SurfersOnline.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.ac.cput.laclance.SurfersOnline.conf.factory.BasicInfoFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.CommentFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.SurfSpotFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.WavesFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurfSpotTest {
    private BasicInfo basics;
    private Waves waves;
    private List<Comment> comments;
    Map values;

    @Before
    public void setUp() throws Exception {
        comments = new ArrayList<>();
        values = new HashMap<>();
        values.put("comment","hello");
        values.put("username","laclance");

        basics = BasicInfoFactory.createBasicInfo("Big Bay", "Sand Bay");
        waves = WavesFactory.createWaves("Good", 1, "s", 1, "s", "s", 1);
        Comment comment = CommentFactory.createComment(values, "10/10/2015");
        comments.add(comment);
    }

    @Test
    public void testSurfSpot() throws Exception {
        SurfSpot surfSpot = SurfSpotFactory.createSurfSpot(basics, waves, comments);
        Assert.assertEquals("Big Bay", surfSpot.getBasicInfo().getName());
    }
}
