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
import za.ac.cput.laclance.SurfersOnline.domain.SurfSpot;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class SurfSpotAPITest {
    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    @Test
    public void testRead() throws Exception {
        ResponseEntity<String> response =template.getForEntity(BASE_URL+"api/surfSpot/readall", String.class);

        System.out.println("The response is "+response.getBody());
    }

    public static final String REST_SERVICE_URI = "http://localhost:8080/api";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllSurfSpots(){
        System.out.println("Testing listAllSurfSpots API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> SurfSpotsMap = restTemplate.getForObject(REST_SERVICE_URI+"/surfSpots/", List.class);

        if(SurfSpotsMap!=null){
            for(LinkedHashMap<String, Object> map : SurfSpotsMap){
                System.out.println("SurfSpot : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No SurfSpot exist----------");
        }
    }

    /* GET */
    private static void getSurfSpot(){
        System.out.println("Testing getSurfSpot API----------");
        RestTemplate restTemplate = new RestTemplate();
        SurfSpot surfSpot = restTemplate.getForObject(REST_SERVICE_URI+"/surfSpot/1", SurfSpot.class);
        System.out.println(surfSpot);
    }

    /* POST */
    private static void createSurfSpot() {
        BasicInfo basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        System.out.println("Testing create SurfSpot API----------");
        RestTemplate restTemplate = new RestTemplate();
        SurfSpot surfSpot = new SurfSpot.Builder(basics).build();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/surfSpot/create/", surfSpot, SurfSpot.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateSurfSpot() {
        BasicInfo basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        System.out.println("Testing update SurfSpot API----------");
        RestTemplate restTemplate = new RestTemplate();
        SurfSpot surfSpot = new SurfSpot.Builder(basics).build();
        restTemplate.put(REST_SERVICE_URI+"/surfSpot/update/1", surfSpot);
        System.out.println(surfSpot);
    }

    /* DELETE */
    private static void deleteSurfSpot() {
        System.out.println("Testing delete SurfSpot API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/surfSpot/delete/3");
    }


    /* DELETE */
    private static void deleteAllSurfSpots() {
        System.out.println("Testing all delete SurfSpots API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/surfSpot/");
    }
}
