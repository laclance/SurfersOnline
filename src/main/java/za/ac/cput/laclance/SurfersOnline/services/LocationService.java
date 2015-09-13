package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Weather;

import java.util.List;

public interface LocationService extends Services<Location,Long> {
    Weather findWeather(Long id);
    List<SurfSpot> findAllSurfSpots(Long id);
}
