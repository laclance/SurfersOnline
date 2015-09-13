package za.ac.cput.laclance.SurfersOnline.api.integrationtests;

import za.ac.cput.laclance.SurfersOnline.App;
import za.ac.cput.laclance.SurfersOnline.conf.factory.BasicInfoFactory;
import za.ac.cput.laclance.SurfersOnline.domain.Area;
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
import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class AreaAPITest {
    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    @Test
    public void testRead() throws Exception {
        ResponseEntity<String> response =template.getForEntity(BASE_URL+"api/area/readall", String.class);

        System.out.println("The response is "+response.getBody());
    }

    public static final String REST_SERVICE_URI = "http://localhost:8080/api";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllAreas(){
        System.out.println("Testing listAllAreas API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> AreasMap = restTemplate.getForObject(REST_SERVICE_URI+"/areas/", List.class);

        if(AreasMap!=null){
            for(LinkedHashMap<String, Object> map : AreasMap){
                System.out.println("Area : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No Area exist----------");
        }
    }

    /* GET */
    private static void getArea(){
        System.out.println("Testing getArea API----------");
        RestTemplate restTemplate = new RestTemplate();
        Area area = restTemplate.getForObject(REST_SERVICE_URI+"/area/1", Area.class);
        System.out.println(area);
    }

    /* POST */
    private static void createArea() {
        BasicInfo basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        System.out.println("Testing create Area API----------");
        RestTemplate restTemplate = new RestTemplate();
        Area area = new Area.Builder(basics).build();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/area/create/", area, Area.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateArea() {
        BasicInfo basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        System.out.println("Testing update Area API----------");
        RestTemplate restTemplate = new RestTemplate();
        Area area = new Area.Builder(basics).build();
        restTemplate.put(REST_SERVICE_URI+"/area/update/1", area);
        System.out.println(area);
    }

    /* DELETE */
    private static void deleteArea() {
        System.out.println("Testing delete Area API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/area/delete/3");
    }


    /* DELETE */
    private static void deleteAllAreas() {
        System.out.println("Testing all delete Areas API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/area/");
    }
}
