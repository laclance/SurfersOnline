package za.ac.cput.laclance.SurfersOnline.services.Impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import za.ac.cput.laclance.SurfersOnline.domain.Comment;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
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
    public SurfSpot findById(Long id) {
        return repository.findOne(id);
    }

    @Override
    public SurfSpot save(SurfSpot entity) {
        return repository.save(entity);
    }

    @Override
    public SurfSpot update(SurfSpot entity) {
        return repository.save(entity);
    }

    @Override
    public void delete(SurfSpot entity) {
        repository.delete(entity);

    }

    @Override
    public List<SurfSpot> findAll() {
        List<SurfSpot> allSurfSpots = new ArrayList<>();

        Iterable<SurfSpot> surfSpots = repository.findAll();
        for (SurfSpot surfSpot : surfSpots) {
            allSurfSpots.add(surfSpot);
        }
        return allSurfSpots;
    }

    @Override
    public Waves findWaves(Long id) {
        return repository.findOne(id).getWaves();
    }

    @Override
    public List<Comment> findAllComments(Long id) {return repository.findOne(id).getComments();}

}
