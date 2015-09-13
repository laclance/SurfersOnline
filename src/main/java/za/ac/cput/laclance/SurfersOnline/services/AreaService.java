package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.Area;
import za.ac.cput.laclance.SurfersOnline.domain.Location;

import java.util.List;

public interface AreaService extends Services<Area,Long>{
    List<Location> findAllLocations(Long id);
}
