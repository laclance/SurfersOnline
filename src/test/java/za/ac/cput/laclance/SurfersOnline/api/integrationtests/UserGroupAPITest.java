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
import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class UserGroupAPITest {
    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    @Test
    public void testRead() throws Exception {
        ResponseEntity<String> response =template.getForEntity(BASE_URL+"api/userGroup/readall", String.class);

        System.out.println("The response is "+response.getBody());
    }

    public static final String REST_SERVICE_URI = "http://localhost:8080/api";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllUserGroups(){
        System.out.println("Testing listAllUserGroups API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> UserGroupsMap = restTemplate.getForObject(REST_SERVICE_URI+"/userGroups/", List.class);

        if(UserGroupsMap!=null){
            for(LinkedHashMap<String, Object> map : UserGroupsMap){
                System.out.println("UserGroup : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            System.out.println("No UserGroup exist----------");
        }
    }

    /* GET */
    private static void getUserGroup(){
        System.out.println("Testing getUserGroup API----------");
        RestTemplate restTemplate = new RestTemplate();
        UserGroup userGroup = restTemplate.getForObject(REST_SERVICE_URI+"/userGroup/1", UserGroup.class);
        System.out.println(userGroup);
    }

    /* POST */
    private static void createUserGroup() {
        BasicInfo basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        System.out.println("Testing create UserGroup API----------");
        RestTemplate restTemplate = new RestTemplate();
        UserGroup userGroup = new UserGroup.Builder(basics).build();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/userGroup/create/", userGroup, UserGroup.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateUserGroup() {
        BasicInfo basics = BasicInfoFactory.createBasicInfo("West Coast", "");
        System.out.println("Testing update UserGroup API----------");
        RestTemplate restTemplate = new RestTemplate();
        UserGroup userGroup = new UserGroup.Builder(basics).build();
        restTemplate.put(REST_SERVICE_URI+"/userGroup/update/1", userGroup);
        System.out.println(userGroup);
    }

    /* DELETE */
    private static void deleteUserGroup() {
        System.out.println("Testing delete UserGroup API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/userGroup/delete/3");
    }


    /* DELETE */
    private static void deleteAllUserGroups() {
        System.out.println("Testing all delete UserGroups API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/userGroup/");
    }
}
