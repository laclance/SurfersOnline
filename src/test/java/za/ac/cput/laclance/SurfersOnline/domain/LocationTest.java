package za.ac.cput.laclance.SurfersOnline.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.ac.cput.laclance.SurfersOnline.conf.factory.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LocationTest {
    private BasicInfo basics;
    private Weather weather;
    private Waves waves;
    private List<SurfSpot> surfspots;
    private List<Comment> comments;

    @Before
    public void setUp() throws Exception {
        comments = new ArrayList<>();
        Map values = new HashMap<>();
        values.put("comment","hello");
        values.put("username","laclance");
        basics = BasicInfoFactory.createBasicInfo("Blouberg", "");
        weather = WeatherFactory.createWeather("Cloudy", 1, 1, 1, 1);

        Comment comment = CommentFactory.createComment(values, "10/10/2015");
        comments.add(comment);

        surfspots = new ArrayList();
        waves = WavesFactory.createWaves("Good", 1, "s", 1, "s", "s", 1);
        surfspots.add(SurfSpotFactory.createSurfSpot(basics, waves, comments));
        waves = WavesFactory.createWaves("bad", 1, "s", 1, "s", "s", 1);
        surfspots.add(SurfSpotFactory.createSurfSpot(basics, waves, comments));
    }

    @Test
    public void testCreateLocation() throws Exception {
        Location location = LocationFactory.createLocation(basics, weather, surfspots);
        Assert.assertEquals("Blouberg", location.getBasicInfo().getName());
    }

   /* @Test
    public void testUpdateStore() throws Exception {
        List<User> users = new ArrayList<User>();

        values.put("code", "NDP2015");
        values.put("name", "National Diploma");

        Store course =CourseFactory
                .createCourse(25, values, lecturers);

        Store newcourse = new Store
                .Builder(course.getCode())
                .copy(course)
                .offering(20).build();

        Assert.assertEquals("NDP2015",newcourse.getCode());
        Assert.assertEquals("National Diploma",newcourse.getName());
        Assert.assertEquals(20,newcourse.getOffering());
        Assert.assertEquals(25,course.getOffering());
    }*/
}
