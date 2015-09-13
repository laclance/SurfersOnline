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
import za.ac.cput.laclance.SurfersOnline.conf.factory.UserBasicsFactory;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.BasicInfo;
import za.ac.cput.laclance.SurfersOnline.domain.UserBasics;

import java.net.URI;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = App.class)
@WebAppConfiguration
@IntegrationTest({"server.port=8080"})
public class UserAPITest {
    final String BASE_URL = "http://localhost:8080/";
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    @Test
    public void testRead() throws Exception {
        ResponseEntity<String> response = template.getForEntity(BASE_URL+"api/user/readall", String.class);

        System.out.println("The response is "+response.getBody());
    }

    public static final String REST_SERVICE_URI = "http://localhost:8080/api";

    /* GET */
    @SuppressWarnings("unchecked")
    private static void listAllusers(){
        System.out.println("Testing listAllusers API-----------");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(REST_SERVICE_URI+"/users/", List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                System.out.println("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));
            }
        }else{
            System.out.println("No User exist----------");
        }
    }

    /* GET */
    private static void getUser(){
        System.out.println("Testing getUser API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(REST_SERVICE_URI+"/user/1", User.class);
        System.out.println(user);
    }

    /* POST */
    private static void createUser() {
        Map values = new HashMap<>();
        values.put("firstName","Lance");
        values.put("lastName", "Coe");
        values.put("username","laclance");
        values.put("password", "123");

        UserBasics basics = UserBasicsFactory.createUserBasics(values, 'm', "04/06/91");
        System.out.println("Testing create User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User.Builder(values).userbasics(basics).build();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/user/create/", user, User.class);
        System.out.println("Location : "+uri.toASCIIString());
    }

    /* PUT */
    private static void updateUser() {
        Map values = new HashMap<>();
        values.put("firstName","Lance");
        values.put("lastName", "Coe");
        values.put("username","laclance");
        values.put("password", "123");

        UserBasics basics = UserBasicsFactory.createUserBasics(values, 'm', "04/06/91");
        System.out.println("Testing update User API----------");
        RestTemplate restTemplate = new RestTemplate();
        User user = new User.Builder(values).userbasics(basics).build();
        restTemplate.put(REST_SERVICE_URI+"/user/update/1", user);
        System.out.println(user);
    }

    /* DELETE */
    private static void deleteUser() {
        System.out.println("Testing delete User API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI+"/user/delete/3");
    }


    /* DELETE */
    private static void deleteAllusers() {
        System.out.println("Testing all delete users API----------");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(REST_SERVICE_URI + "/user/");
    }
}
