package za.ac.cput.laclance.SurfersOnline.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import za.ac.cput.laclance.SurfersOnline.domain.Comment;
import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;
import za.ac.cput.laclance.SurfersOnline.domain.User;
import za.ac.cput.laclance.SurfersOnline.domain.UserGroup;
import za.ac.cput.laclance.SurfersOnline.model.UserGroupResource;
import za.ac.cput.laclance.SurfersOnline.services.UserGroupService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/**")
public class UserGroupPage {
    @Autowired
    private UserGroupService service;

    //-------------------Retrieve All UserGroups--------------------------------------------------------

    @RequestMapping(value = "/userGroups/", method = RequestMethod.GET)
    public ResponseEntity<List<UserGroup>> listAllUserGroups() {
        List<UserGroup> UserGroups = service.findAll();
        if(UserGroups.isEmpty()){
            return new ResponseEntity<List<UserGroup>>(HttpStatus.NO_CONTENT);//You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<UserGroup>>(UserGroups, HttpStatus.OK);
    }


    //-------------------Retrieve Single UserGroup--------------------------------------------------------

    @RequestMapping(value = "/userGroup/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserGroup> getUserGroup(@PathVariable("id") long id) {
        System.out.println("Fetching UserGroup with id " + id);
        UserGroup UserGroup = service.findById(id);

        if (UserGroup == null) {
            System.out.println("UserGroup with id " + id + " not found");
            return new ResponseEntity<UserGroup>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<UserGroup>(UserGroup, HttpStatus.OK);
    }



    //-------------------Create a UserGroup--------------------------------------------------------

    @RequestMapping(value = "/userGroup/create", method = RequestMethod.POST)
    public ResponseEntity<Void> createUserGroup(@RequestBody UserGroup userGroup, UriComponentsBuilder ucBuilder) {
        System.out.println("Creating UserGroup " + userGroup.getBasicInfo().getName());

/*      if (UserGroupService.isUserGroupExist(UserGroup)) {
            System.out.println("A UserGroup with name " + UserGroup.getName() + " already exist");
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }*/

        service.save(userGroup);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/userGroup/{id}").buildAndExpand(userGroup.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    //------------------- Update a UserGroup --------------------------------------------------------

    @RequestMapping(value = "/userGroup/update/{id}", method = RequestMethod.PUT)
    public ResponseEntity<UserGroup> updateUserGroup(@PathVariable("id") long id, @RequestBody UserGroup userGroup) {
        System.out.println("Updating UserGroup " + id);

        UserGroup currentUserGroup = service.findById(id);

        if (currentUserGroup==null) {
            System.out.println("UserGroup with id " + id + " not found");
            return new ResponseEntity<UserGroup>(HttpStatus.NOT_FOUND);
        }

        UserGroup updatedUserGroup = new UserGroup
                .Builder(currentUserGroup.getBasicInfo())
                .copy(currentUserGroup)
                .build();
        service.update(updatedUserGroup);
        return new ResponseEntity<UserGroup>(updatedUserGroup, HttpStatus.OK);
    }

    //------------------- Delete a UserGroup --------------------------------------------------------

    @RequestMapping(value = "/userGroup/delete/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<UserGroup> deleteUserGroup(@PathVariable("id") long id) {
        System.out.println("Fetching & Deleting UserGroup with id " + id);

        UserGroup userGroup = service.findById(id);
        if (userGroup == null) {
            System.out.println("Unable to delete. UserGroup with id " + id + " not found");
            return new ResponseEntity<UserGroup>(HttpStatus.NOT_FOUND);
        }

        service.delete(userGroup);
        return new ResponseEntity<UserGroup>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value="/{id}/comments", method=RequestMethod.GET)
    List<Comment> getSurfSpotsComments(@PathVariable Long id) {
        return service.findAllComments(id);
    }

    @RequestMapping(value="/{id}/users", method=RequestMethod.GET)
    List<User> getSurfSpotsUsers(@PathVariable Long id) {
        return service.findAllUsers(id);
    }
}
