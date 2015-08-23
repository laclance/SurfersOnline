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
import java.util.List;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class AreaCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    private BasicInfo basics;
    private Weather weather;
    private Waves waves;
    private List<Location> locations;
    private List<SurfSpot> surfSpots;

    @Autowired
    private AreaRepository repository;

    @BeforeClass
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
    public void read() throws Exception {
        Area area = repository.findOne(id);
        Assert.assertNotNull(area);
        Assert.assertEquals(area.getBasicInfo().getName(), "West Coast");
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception {
        Area area = repository.findOne(id);

        basics = BasicInfoFactory.createBasicInfo("West Coast", "cold");
        Area newArea = new Area.Builder(basics)
                .copy(area)
                .basicInfo(basics)
                .build();

        repository.save(newArea);

        Area updatedArea = repository.findOne(id);
        Assert.assertEquals("cold", updatedArea.getBasicInfo().getDescription());
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        Area area = repository.findOne(id);
        repository.delete(area);

        Area deletedArea = repository.findOne(id);
        Assert.assertNull(deletedArea);
    }
}
