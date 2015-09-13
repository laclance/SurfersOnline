package za.ac.cput.laclance.SurfersOnline.api.integrationtests;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;
import za.ac.cput.laclance.SurfersOnline.App;
import za.ac.cput.laclance.SurfersOnline.conf.factory.BasicInfoFactory;
import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;
import za.ac.cput.laclance.SurfersOnline.domain.Location;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class LocationAPITest {
    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    @Test
    public void testRead() throws Exception {
        ResponseEntity<String> response =template.getForEntity(BASE_URL+"api/location/readall", String.class);

        System.out.println("The response is "+response.getBody());
    }

    public static final String REST_SERVICE_URI = "http://localhost:8080/api";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllLocations(){
        System.out.println("Testing listAllLocations API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> LocationsMap = restTemplate.getForObject(REST_SERVICE_URI+"/locations/", List.class);

        if(LocationsMap!=null){
            for(LinkedHashMap<String, Object> map : LocationsMap){
                System.out.println("Location : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No Location exist----------");
        }
    }

    /* GET */
    private static void getLocation(){
        System.out.println("Testing getLocation API----------");
        RestTemplate restTemplate = new RestTemplate();
        Location location = restTemplate.getForObject(REST_SERVICE_URI+"/location/1", Location.class);
        System.out.println(location);
    }

    /* POST */
    private static void createLocation() {
        BasicInfo basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        System.out.println("Testing create Location API----------");
        RestTemplate restTemplate = new RestTemplate();
        Location location = new Location.Builder(basics).build();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/location/create/", location, Location.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateLocation() {
        BasicInfo basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        System.out.println("Testing update Location API----------");
        RestTemplate restTemplate = new RestTemplate();
        Location location = new Location.Builder(basics).build();
        restTemplate.put(REST_SERVICE_URI+"/location/update/1", location);
        System.out.println(location);
    }

    /* DELETE */
    private static void deleteLocation() {
        System.out.println("Testing delete Location API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/location/delete/3");
    }


    /* DELETE */
    private static void deleteAllLocations() {
        System.out.println("Testing all delete Locations API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/location/");
    }
}
