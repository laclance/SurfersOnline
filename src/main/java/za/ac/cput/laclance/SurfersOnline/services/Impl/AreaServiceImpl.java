package za.ac.cput.laclance.SurfersOnline.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.laclance.SurfersOnline.domain.Area;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.repository.AreaRepository;
import za.ac.cput.laclance.SurfersOnline.services.AreaService;

import java.util.ArrayList;
import java.util.List;

@Service
public class AreaServiceImpl implements AreaService {
    @Autowired
    AreaRepository repository;

    @Override
    public Area findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public Area save(Area entity) {
        return repository.save(entity);
    }

    @Override
    public Area update(Area entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(Area entity) {
        repository.delete(entity);

    }

    @Override
    public List<Area> findAll() {
        List<Area> allAreas = new ArrayList<>();
        Iterable<Area> areas = repository.findAll();
        for (Area area : areas) {
            allAreas.add(area);
        }
        return allAreas;
    }

    @Override
    public List<Location> findAllLocations(Long id) {
        return repository.findOne(id).getLocations();
    }
}
