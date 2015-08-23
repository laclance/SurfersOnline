package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Weather;

import java.util.List;

public interface LocationService {
    Location getLocation(Long id);
    List<Location> getAllLocations();
    Weather getWeather(Long id);
    List<SurfSpot> getSurfSpots(Long id);
}
