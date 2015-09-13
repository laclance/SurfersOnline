package za.ac.cput.laclance.SurfersOnline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.laclance.SurfersOnline.domain.Comment;
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;
import za.ac.cput.laclance.SurfersOnline.domain.Waves;
import za.ac.cput.laclance.SurfersOnline.services.SurfSpotService;

import javax.jws.WebService;
import java.util.List;

@RestController
@RequestMapping("/api/**")
public class SurfSpotPage {
    @Autowired
    private SurfSpotService service;

    //-------------------Retrieve All SurfSpots--------------------------------------------------------

    @RequestMapping(value = "/surfSpots/", method = RequestMethod.GET)
    public ResponseEntity<List<SurfSpot>> listAllSurfSpots() {
        List<SurfSpot> SurfSpots = service.findAll();
        if(SurfSpots.isEmpty()){
            return new ResponseEntity<List<SurfSpot>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<SurfSpot>>(SurfSpots, HttpStatus.OK);
    }


    //-------------------Retrieve Single SurfSpot--------------------------------------------------------

    @RequestMapping(value = "/surfSpot/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<SurfSpot> getSurfSpot(@PathVariable("id") long id) {
        System.out.println("Fetching SurfSpot with id " + id);
        SurfSpot SurfSpot = service.findById(id);
        if (SurfSpot == null) {
            System.out.println("SurfSpot with id " + id + " not found");
            return new ResponseEntity<SurfSpot>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<SurfSpot>(SurfSpot, HttpStatus.OK);
    }



    //-------------------Create a SurfSpot--------------------------------------------------------

    @RequestMapping(value = "/surfSpot/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createSurfSpot(@RequestBody SurfSpot surfSpot, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating SurfSpot " + surfSpot.getBasicInfo().getName());

/*      if (SurfSpotService.isSurfSpotExist(SurfSpot)) {
            System.out.println("A SurfSpot with name " + SurfSpot.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/

        service.save(surfSpot);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/surfSpot/{id}").buildAndExpand(surfSpot.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a SurfSpot --------------------------------------------------------

    @RequestMapping(value = "/surfSpot/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<SurfSpot> updateSurfSpot(@PathVariable("id") long id, @RequestBody SurfSpot surfSpot) {
        System.out.println("Updating SurfSpot " + id);

        SurfSpot currentSurfSpot = service.findById(id);

        if (currentSurfSpot==null) {
            System.out.println("SurfSpot with id " + id + " not found");
            return new ResponseEntity<SurfSpot>(HttpStatus.NOT_FOUND);
        }

        SurfSpot updatedSurfSpot = new SurfSpot
                .Builder(currentSurfSpot.getBasicInfo())
                .copy(currentSurfSpot)
                .build();
        service.update(updatedSurfSpot);
        return new ResponseEntity<SurfSpot>(updatedSurfSpot, HttpStatus.OK);
    }

    //------------------- Delete a SurfSpot --------------------------------------------------------

    @RequestMapping(value = "/surfSpot/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<SurfSpot> deleteSurfSpot(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting SurfSpot with id " + id);

        SurfSpot surfSpot = service.findById(id);
        if (surfSpot == null) {
            System.out.println("Unable to delete. SurfSpot with id " + id + " not found");
            return new ResponseEntity<SurfSpot>(HttpStatus.NOT_FOUND);
        }

        service.delete(surfSpot);
        return new ResponseEntity<SurfSpot>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}/waves", method=RequestMethod.GET)
    Waves getSurfSpotsWaves(@PathVariable Long id) {
        return service.findWaves(id);
    }

    @RequestMapping(value="/{id}/comments", method=RequestMethod.GET)
    List<Comment> getSurfSpotsComments(@PathVariable Long id) {
        return service.findAllComments(id);
    }
}
