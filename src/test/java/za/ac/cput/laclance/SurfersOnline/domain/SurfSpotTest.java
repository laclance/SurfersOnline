package za.ac.cput.laclance.SurfersOnline.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.ac.cput.laclance.SurfersOnline.conf.factory.BasicInfoFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.SurfSpotFactory;
import za.ac.cput.laclance.SurfersOnline.conf.factory.WavesFactory;

public class SurfSpotTest {
    private BasicInfo basics;
    private Waves waves;

    @Before
    public void setUp() throws Exception {
        basics = BasicInfoFactory.createBasicInfo("Big Bay", "Sand Bay");
        waves = WavesFactory.createWaves("Good", 1, "s", 1, "s", "s", 1);
    }

    @Test
    public void testSurfSpot() throws Exception {
        SurfSpot surfSpot = SurfSpotFactory.createSurfSpot(basics, waves);
        Assert.assertEquals("Big Bay", surfSpot.getBasicInfo().getName());
    }
}
