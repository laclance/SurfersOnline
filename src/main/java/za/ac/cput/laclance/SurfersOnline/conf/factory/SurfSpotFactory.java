package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.*;

public class SurfSpotFactory {
    public static SurfSpot createSurfSpot(BasicInfo basicInfo,
                                          Waves waves) {
        SurfSpot surfSpot = new SurfSpot
                .Builder(basicInfo)
                .waves(waves)
                .build();
        return surfSpot;
    }
}
