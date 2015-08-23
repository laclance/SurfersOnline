package za.ac.cput.laclance.SurfersOnline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import za.ac.cput.laclance.SurfersOnline.domain.Area;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.model.AreaResource;
import za.ac.cput.laclance.SurfersOnline.services.AreaService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value="/api/area/**")
public class AreaPage {
    @Autowired
    private AreaService service;

    @RequestMapping(value="/{id}/", method= RequestMethod.GET)
    public Area getArea(@PathVariable Long id) {
        return service.getArea(id);
    }

    @RequestMapping(value="/{id}/locations", method= RequestMethod.GET)
    public List<Location> getAreaLocations(@PathVariable Long id) {
        return service.getLocations(id);
    }

    @RequestMapping(value="/all", method= RequestMethod.GET)
    public List<AreaResource> getAreas() {
        List<AreaResource> hateos = new ArrayList<>();
        List<Area> areas = service.getAllAreas();

        for (Area area : areas) {
            AreaResource res = new AreaResource
                    .Builder(area.getBasicInfo())
                    .locations(area.getLocations())
                    .resid(area.getId())
                    .build();

            Link locations = new
                    Link("http://localhost:8080/area/"+res.getResid().toString())
                    .withRel("locations");
            res.add(locations);
            hateos.add(res);
        }
        return hateos;
    }
}
