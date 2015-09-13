package za.ac.cput.laclance.SurfersOnline.services;

import za.ac.cput.laclance.SurfersOnline.domain.Comment;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Waves;

import java.util.List;

public interface SurfSpotService extends Services<SurfSpot,Long> {
    Waves findWaves(Long id);
    List<Comment> findAllComments(Long id);
}
