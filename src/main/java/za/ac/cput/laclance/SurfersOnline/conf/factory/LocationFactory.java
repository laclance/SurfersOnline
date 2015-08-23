package za.ac.cput.laclance.SurfersOnline.conf.factory;

import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Weather;

import java.util.List;

public class LocationFactory {
    public static Location createLocation(BasicInfo basicInfo,
                                       Weather weather,
                                       List<SurfSpot> surfSpots) {
        Location location = new Location
                .Builder(basicInfo)
                .weather(weather)
                .surfSpots(surfSpots)
                .build();
        return location;
    }
}
