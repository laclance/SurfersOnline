package za.ac.cput.laclance.SurfersOnline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.laclance.SurfersOnline.domain.Area;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.services.AreaService;
import java.util.List;

@RestController
@RequestMapping("/api/**")
public class AreaPage {
    @Autowired
    private AreaService service;

    //-------------------Retrieve All Areas--------------------------------------------------------

    @RequestMapping(value = "/areas/", method = RequestMethod.GET)
    public ResponseEntity<List<Area>> listAllAreas() {
        List<Area> Areas = service.findAll();
        if(Areas.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(Areas, HttpStatus.OK);
    }

    //-------------------Retrieve Single Area--------------------------------------------------------

    @RequestMapping(value = "/area/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Area> getArea(@PathVariable("id") long id) {
        System.out.println("Fetching Area with id " + id);
        Area Area = service.findById(id);
        if (Area == null) {
            System.out.println("Area with id " + id + " not found");
            return new ResponseEntity<Area>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Area>(Area, HttpStatus.OK);
    }



    //-------------------Create a Area--------------------------------------------------------

    @RequestMapping(value = "/area/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createArea(@RequestBody Area area, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Area " + area.getBasicInfo().getName());

/*      if (AreaService.isAreaExist(Area)) {
            System.out.println("A Area with name " + Area.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/

        service.save(area);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/area/{id}").buildAndExpand(area.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Area --------------------------------------------------------

    @RequestMapping(value = "/area/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Area> updateArea(@PathVariable("id") long id, @RequestBody Area area) {
        System.out.println("Updating Area " + id);

        Area currentArea = service.findById(id);

        if (currentArea==null) {
            System.out.println("Area with id " + id + " not found");
            return new ResponseEntity<Area>(HttpStatus.NOT_FOUND);
        }

        Area updatedArea = new Area
                .Builder(currentArea.getBasicInfo())
                .copy(currentArea)
                .build();
        service.update(updatedArea);
        return new ResponseEntity<Area>(updatedArea, HttpStatus.OK);
    }

    //------------------- Delete a Area --------------------------------------------------------

    @RequestMapping(value = "/area/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Area> deleteArea(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Area with id " + id);

        Area area = service.findById(id);
        if (area == null) {
            System.out.println("Unable to delete. Area with id " + id + " not found");
            return new ResponseEntity<Area>(HttpStatus.NOT_FOUND);
        }

        service.delete(area);
        return new ResponseEntity<Area>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}/locations", method=RequestMethod.GET)
    List<Location> getAreaLocations(@PathVariable Long id) {
        return service.findAllLocations(id);
    }
}
