package za.ac.cput.laclance.SurfersOnline.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Waves;
import za.ac.cput.laclance.SurfersOnline.repository.SurfSpotRepository;
import za.ac.cput.laclance.SurfersOnline.services.SurfSpotService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SurfSpotServiceImpl implements SurfSpotService {
    @Autowired
    SurfSpotRepository repository;

    @Override
    public SurfSpot getSurfSpot(Long id) {
        return repository.findOne(id);
    }

    @Override
    public List<SurfSpot> getAllSurfSpots() {
        List<SurfSpot> allSurfSpots = new ArrayList<>();

        Iterable<SurfSpot> surfSpots = repository.findAll();
        for (SurfSpot surfSpot : surfSpots) {
            allSurfSpots.add(surfSpot);
        }
        return allSurfSpots;
    }

    @Override
    public Waves getWaves(Long id) {
        return repository.findOne(id).getWaves();
    }
}
