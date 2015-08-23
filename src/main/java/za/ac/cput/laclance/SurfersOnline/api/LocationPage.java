package za.ac.cput.laclance.SurfersOnline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.model.LocationResource;
import za.ac.cput.laclance.SurfersOnline.services.LocationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/location/**")
public class LocationPage {
    @Autowired
    private LocationService service;

    @RequestMapping(value="/{id}/", method= RequestMethod.GET)
    public Location getLocation(@PathVariable Long id) {
        return service.getLocation(id);
    }

    @RequestMapping(value="/{id}/surfspots", method= RequestMethod.GET)
    public List<SurfSpot> getSurfSpots(@PathVariable Long id) {
        return service.getSurfSpots(id);
    }

    @RequestMapping(value="/all", method= RequestMethod.GET)
    public List<LocationResource> getLocations() {
        List<LocationResource> hateos = new ArrayList<>();
        List<Location> locations = service.getAllLocations();

        for (Location location : locations) {
            LocationResource res = new LocationResource
                    .Builder(location.getBasicInfo())
                    .weather(location.getWeather())
                    .surfSpots(location.getSurfSpots())
                    .resid(location.getId())
                    .build();

            Link surfspots = new
                    Link("http://localhost:8080/location/"+res.getResid().toString())
                    .withRel("locations");
            res.add(surfspots);
            hateos.add(res);
        }
        return hateos;
    }
}
