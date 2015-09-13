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
public class LocationCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    private BasicInfo basics;
    private Weather weather;
    private Waves waves;
    private List<SurfSpot> surfSpots;
    private List<Comment> comments;

    @Autowired
    private LocationRepository repository;

    @BeforeClass
    public void setUp() throws Exception {
        comments = new ArrayList<>();
        Map values = new HashMap<>();
        values.put("comment","hello");
        values.put("username","laclance");
        basics = BasicInfoFactory.createBasicInfo("Blouberg", "warm");
        weather = WeatherFactory.createWeather("Cloudy", 0, 1, 1, 1);
        waves = WavesFactory.createWaves("Good", 1, "s", 1, "s", "s", 1);

        Comment comment = CommentFactory.createComment(values, "10/10/2015");
        comments.add(comment);

        surfSpots = new ArrayList();
        surfSpots.add(SurfSpotFactory.createSurfSpot(basics, waves, comments));
    }

    @AfterClass
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void create() throws Exception {
        Location location = LocationFactory.createLocation(basics, weather, surfSpots);
        repository.save(location);
        id = location.getId();
        Assert.assertNotNull(location.getId());
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception {
        Location location = repository.findOne(id);
        Assert.assertNotNull(location);
        Assert.assertEquals(location.getBasicInfo().getName(), "Blouberg");
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception {
        Location location = repository.findOne(id);
        
        basics = BasicInfoFactory.createBasicInfo("Blouberg", "cold");
        Location newLocation = new Location.Builder(basics)
                .copy(location)
                .basicInfo(basics)
                .build();

        repository.save(newLocation);

        Location updatedLocation = repository.findOne(id);
        Assert.assertEquals("cold", updatedLocation.getBasicInfo().getDescription());
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        Location location = repository.findOne(id);
        repository.delete(location);

        Location deletedLocation = repository.findOne(id);
        Assert.assertNull(deletedLocation);
    }

}
