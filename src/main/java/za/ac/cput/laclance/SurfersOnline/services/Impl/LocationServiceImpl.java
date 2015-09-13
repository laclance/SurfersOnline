package za.ac.cput.laclance.SurfersOnline.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Weather;
import za.ac.cput.laclance.SurfersOnline.repository.LocationRepository;
import za.ac.cput.laclance.SurfersOnline.services.LocationService;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocationServiceImpl implements LocationService {
    @Autowired
    LocationRepository repository;

    @Override
    public Location findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Location save(Location entity) {
        return repository.save(entity);
    }

    @Override
    public Location update(Location entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Location entity) {
        repository.delete(entity);

    }

    @Override
    public List<Location> findAll() {
        List<Location> allLocations = new ArrayList<>();

        Iterable<Location> locations = repository.findAll();
        for (Location location : locations) {
            allLocations.add(location);
        }
        return allLocations;
    }

    @Override
    public Weather findWeather(Long id) {
        return repository.findOne(id).getWeather();
    }

    @Override
    public List<SurfSpot> findAllSurfSpots(Long id) {
        return repository.findOne(id).getSurfSpots();
    }
}
