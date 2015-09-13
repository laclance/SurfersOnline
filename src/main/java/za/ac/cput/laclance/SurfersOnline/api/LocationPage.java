package za.ac.cput.laclance.SurfersOnline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.laclance.SurfersOnline.domain.*;
import za.ac.cput.laclance.SurfersOnline.domain.Location;
import za.ac.cput.laclance.SurfersOnline.model.LocationResource;
import za.ac.cput.laclance.SurfersOnline.services.LocationService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/**")
public class LocationPage {
    @Autowired
    private LocationService service;

    //-------------------Retrieve All Locations--------------------------------------------------------

    @RequestMapping(value = "/locations/", method = RequestMethod.GET)
    public ResponseEntity<List<Location>> listAllLocations() {
        List<Location> Locations = service.findAll();
        if(Locations.isEmpty()){
            return new ResponseEntity<List<Location>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<Location>>(Locations, HttpStatus.OK);
    }


    //-------------------Retrieve Single Location--------------------------------------------------------

    @RequestMapping(value = "/location/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Location> getLocation(@PathVariable("id") long id) {
        System.out.println("Fetching Location with id " + id);
        Location Location = service.findById(id);
        if (Location == null) {
            System.out.println("Location with id " + id + " not found");
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Location>(Location, HttpStatus.OK);
    }


    //-------------------Create a Location--------------------------------------------------------

    @RequestMapping(value = "/location/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createLocation(@RequestBody Location location, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating Location " + location.getBasicInfo().getName());

/*      if (LocationService.isLocationExist(Location)) {
            System.out.println("A Location with name " + Location.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/

        service.save(location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/location/{id}").buildAndExpand(location.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a Location --------------------------------------------------------

    @RequestMapping(value = "/location/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Location> updateLocation(@PathVariable("id") long id, @RequestBody Location location) {
        System.out.println("Updating Location " + id);

        Location currentLocation = service.findById(id);

        if (currentLocation==null) {
            System.out.println("Location with id " + id + " not found");
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }

        Location updatedLocation = new Location
                .Builder(currentLocation.getBasicInfo())
                .copy(currentLocation)
                .build();
        service.update(updatedLocation);
        return new ResponseEntity<Location>(updatedLocation, HttpStatus.OK);
    }

    //------------------- Delete a Location --------------------------------------------------------

    @RequestMapping(value = "/location/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Location> deleteLocation(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting Location with id " + id);

        Location location = service.findById(id);
        if (location == null) {
            System.out.println("Unable to delete. Location with id " + id + " not found");
            return new ResponseEntity<Location>(HttpStatus.NOT_FOUND);
        }

        service.delete(location);
        return new ResponseEntity<Location>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}/waves", method=RequestMethod.GET)
    Weather getLocationWeather(@PathVariable Long id) {
        return service.findWeather(id);
    }

    @RequestMapping(value="/{id}/surfSpots", method=RequestMethod.GET)
    List<SurfSpot> getLocationSurfSpots(@PathVariable Long id) {
        return service.findAllSurfSpots(id);
    }
}
