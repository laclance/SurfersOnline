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
import za.ac.cput.laclance.SurfersOnline.repository.AreaRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class AreaServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private AreaService service;

    @Autowired
    private AreaRepository repository;

    private Long id;
    private BasicInfo basics;
    private Weather weather;
    private Waves waves;
    private List<Location> locations;
    private List<SurfSpot> surfSpots;

    @BeforeMethod
    public void setUp() throws Exception {
        basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        weather = WeatherFactory.createWeather("Cloudy", 0, 1, 1, 1);
        waves = WavesFactory.createWaves("Good", 1, "s", 1, "s", "s", 1);

        surfSpots = new ArrayList();
        surfSpots.add(SurfSpotFactory.createSurfSpot(basics, waves));

        locations = new ArrayList();
        locations.add(LocationFactory.createLocation(basics, weather, surfSpots));
        locations.add(LocationFactory.createLocation(basics, weather, surfSpots));
    }

    @AfterClass
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void create() throws Exception {
        Area area = AreaFactory.createArea(basics, locations);
        repository.save(area);
        id = area.getId();
        Assert.assertNotNull(area.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetArea() throws Exception {
        Area area = service.getArea(id);
        Assert.assertNotNull(area);
    }

    @Test(dependsOnMethods = "testGetArea")
    public void testGetAreas() throws Exception {
        List<Area> areas = service.getAllAreas();
        Assert.assertTrue(areas.size() == 1);
    }

    @Test(dependsOnMethods = "testGetAreas")
    public void testGetAreaLocations() throws Exception {
        List<Location> locations = service.getLocations(id);
        Assert.assertTrue(locations.size() == 2);
    }
}
