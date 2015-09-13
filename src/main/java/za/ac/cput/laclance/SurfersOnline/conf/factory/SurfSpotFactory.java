package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.*;
import java.util.List;

public class SurfSpotFactory {
    public static SurfSpot createSurfSpot(BasicInfo basicInfo,
                                          Waves waves,
                                          List<Comment> comments) {
        SurfSpot surfSpot = new SurfSpot
                .Builder(basicInfo)
                .waves(waves)
                .comments(comments)
                .build();
        return surfSpot;
    }
}
