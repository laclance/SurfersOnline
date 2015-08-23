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
public class SurfSpotCrudTest extends AbstractTestNGSpringContextTests {
    private Long id;

    private BasicInfo basics;
    private Waves waves;

    @Autowired
    private SurfSpotRepository repository;

    @BeforeClass
    public void setUp() throws Exception {
        basics = BasicInfoFactory.createBasicInfo("Big Bay", "warm");
        waves = WavesFactory.createWaves("Good", 1, "s", 1, "s", "s", 1);
    }

    @AfterClass
    public void tearDown() throws Exception {
        repository.deleteAll();
    }

    @Test
    public void create() throws Exception {
        SurfSpot surfSpot = SurfSpotFactory.createSurfSpot(basics, waves);
        repository.save(surfSpot);
        id = surfSpot.getId();
        Assert.assertNotNull(surfSpot.getId());
    }

    @Test(dependsOnMethods = "create")
    public void read() throws Exception {
        SurfSpot surfSpot = repository.findOne(id);
        Assert.assertNotNull(surfSpot);
        Assert.assertEquals(surfSpot.getBasicInfo().getName(), "Big Bay");
    }

    @Test(dependsOnMethods = "read")
    public void update() throws Exception {
        SurfSpot surfSpot = repository.findOne(id);

        basics = BasicInfoFactory.createBasicInfo("Big Bay", "cold");
        SurfSpot newSurfSpot = new SurfSpot.Builder(basics)
                .copy(surfSpot)
                .basicInfo(basics)
                .build();

        repository.save(newSurfSpot);

        SurfSpot updatedSurfSpot = repository.findOne(id);
        Assert.assertEquals("cold", updatedSurfSpot.getBasicInfo().getDescription());
    }

    @Test(dependsOnMethods = "update")
    public void delete() throws Exception {
        SurfSpot surfSpot = repository.findOne(id);
        repository.delete(surfSpot);

        SurfSpot deletedSurfSpot = repository.findOne(id);
        Assert.assertNull(deletedSurfSpot);
    }
}
