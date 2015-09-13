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
import za.ac.cput.laclance.SurfersOnline.conf.factory.*;
import za.ac.cput.laclance.SurfersOnline.domain.*;
import za.ac.cput.laclance.SurfersOnline.repository.LocationRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class LocationServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private LocationService service;
    @Autowired
    private LocationRepository repository;

    private Long id;
    private BasicInfo basics;
    private Weather weather;
    private Waves waves;
    private List<SurfSpot> surfspots;
    private List<Comment> comments;

    @BeforeClass
    public void setUp() throws Exception {
        comments = new ArrayList<>();
        Map values = new HashMap<>();
        values.put("comment","hello");
        values.put("username","laclance");
        basics = BasicInfoFactory.createBasicInfo("Blouberg", "");
        weather = WeatherFactory.createWeather("Cloudy", 1, 1, 1, 1);
        waves = WavesFactory.createWaves("Good", 1, "s", 1, "s", "s", 1);

        Comment comment = CommentFactory.createComment(values, "10/10/2015");
        comments.add(comment);

        surfspots = new ArrayList();
        surfspots.add(SurfSpotFactory.createSurfSpot(basics, waves, comments));
        surfspots.add(SurfSpotFactory.createSurfSpot(basics, waves, comments));
    }

    @AfterClass
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void create() throws Exception {
        Location location = LocationFactory.createLocation(basics, weather, surfspots);
        repository.save(location);
        id= location.getId();
        Assert.assertNotNull(location.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetLocation() throws Exception {
        Location location = service.findById(id);
        Assert.assertNotNull(location);
    }

    @Test(dependsOnMethods = "testGetLocation")
    public void testGetLocations() throws Exception {
        List<Location> locations = service.findAll();
        Assert.assertTrue(locations.size() == 1);
    }

    @Test(dependsOnMethods = "testGetLocations")
    public void testGetLocationSurfSpots() throws Exception {
        List<SurfSpot> surfSpots = service.findAllSurfSpots(id);
        Assert.assertTrue(surfSpots.size() == 2);
    }
}
