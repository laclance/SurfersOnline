package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.*;

import java.util.List;

public class AreaFactory {
    public static Area createArea(BasicInfo basicInfo,
                                   List<Location> locations) {
        Area area = new Area
                .Builder(basicInfo)
                .locations(locations)
                .build();
        return area;
    }
}
