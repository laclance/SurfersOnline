package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.Area;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.domain.User;

import java.util.List;

public interface AreaService {
    Area getArea(Long id);
    List<Area> getAllAreas();
    List<Location> getLocations(Long id);
}
