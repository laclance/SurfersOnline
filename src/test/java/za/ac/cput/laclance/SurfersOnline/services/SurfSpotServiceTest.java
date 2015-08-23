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
import za.ac.cput.laclance.SurfersOnline.conf.factory.BasicInfoFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.SurfSpotFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.WavesFactory;
import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Waves;
import za.ac.cput.laclance.SurfersOnline.repository.SurfSpotRepository;

import java.util.List;

@SpringApplicationConfiguration(classes= App.class)
@WebAppConfiguration
public class SurfSpotServiceTest extends AbstractTestNGSpringContextTests {
    @Autowired
    private SurfSpotService service;
    @Autowired
    private SurfSpotRepository repository;

    private Long id;
    private BasicInfo basics;
    private Waves waves;

    @BeforeMethod
    public void setUp() throws Exception {
        waves = WavesFactory.createWaves("Good", 1, "s", 1, "s", "s", 1);
        basics = BasicInfoFactory.createBasicInfo("Big Bay", "Big Waves");
    }

    @AfterClass
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void create() throws Exception {
        SurfSpot surfSpot = SurfSpotFactory.createSurfSpot(basics, waves);
        repository.save(surfSpot);
        id=surfSpot.getId();
        Assert.assertNotNull(surfSpot.getId());
    }

    @Test(dependsOnMethods = "create")
    public void testGetSurfSpot() throws Exception {
        SurfSpot surfSpot = service.getSurfSpot(id);
        Assert.assertNotNull(surfSpot);
    }

    @Test(dependsOnMethods = "testGetSurfSpot")
    public void testGetSurfSpots() throws Exception {
        List<SurfSpot> surfSpots = service.getAllSurfSpots();
        Assert.assertTrue(surfSpots.size() == 1);
    }

    @Test(dependsOnMethods = "testGetSurfSpots")
    public void testGetSurfSpotWaves() throws Exception {
        Waves waves = service.getWaves(id);
        Assert.assertNotNull(waves);
    }
}
