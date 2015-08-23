package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Waves;

import java.util.List;

public interface SurfSpotService {
    SurfSpot getSurfSpot(Long id);
    List<SurfSpot> getAllSurfSpots();
    Waves getWaves(Long id);
}
