package za.ac.cput.laclance.SurfersOnline.domain;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import za.ac.cput.laclance.SurfersOnline.conf.factory.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AreaTest {
    private BasicInfo basics;
    private List<Location> locations;

    @Before
    public void setUp() throws Exception {
        basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        locations = new ArrayList();
        locations.add(LocationFactory.createLocation(basics, null, null));
        locations.add(LocationFactory.createLocation(basics, null, null));
    }

    @Test
    public void testArea() throws Exception {
        Area area = AreaFactory.createArea(basics, locations);
        Assert.assertEquals("West Coast", area.getBasicInfo().getName());
    }
}
