package fr.sma.webconfboard.controllers;

import fr.sma.webconfboard.entities.User;
import fr.sma.webconfboard.services.User.UserServiceImpl;
import fr.sma.webconfboard.util.CustomErrorType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "*") // http://localhost:4200
public class UserController {

    private UserServiceImpl userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userService) { this.userService = userService; }

    // ------------------- Retrieve All Users ---------------------------------------------

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<List<User>> getAllUsers() {
        logger.info("Fetching all Users");

        List<User> userList = new ArrayList<User>();
        userList = userService.getAllUsers();
        if(userList.isEmpty()){
            logger.error("Unable to fetch an empty list");
            return new ResponseEntity(new CustomErrorType("Users not found"), HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        //logger.info(clientList.toString());

        return new ResponseEntity<List<User>>(userList, HttpStatus.OK);
    }

    // ------------------- Retrieve Single User ------------------------------------------

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<User> getUserById(@PathVariable final Long id) {
        logger.info("Fetching User with id {}", id);
        User user = userService.getUserById(id);

        if(user == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // ------------------- Create a Client -------------------------------------------

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<User> create(@RequestBody User user) {
        logger.info("Creating User : {}", user);

        if(userService.isUserExist(user)) {
            logger.error("Unable to create. A User with username {} already exist",user.getUsername());
            return new ResponseEntity(new CustomErrorType("Unable to create. A User with username " +
                    user.getUsername() + " already exist."),HttpStatus.CONFLICT);
        } else if( user == null ) {
            return ResponseEntity.notFound().build();
        } else {
            userService.save(user);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(user);
        }
    }

    // ------------------- Update a User ------------------------------------------------

    @PutMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<User> updateClient(@PathVariable final Long id, @RequestBody User user) {
        logger.info("Updating Client with id {}", id);

        User currentUser = userService.getUserById(id);

        if(currentUser == null) {
            logger.error("Unable to update. User with id {} not found", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        BeanUtils.copyProperties(user, currentUser);
        userService.updateUser(currentUser);

        return new ResponseEntity<User>(user, HttpStatus.OK);
    }

    // ------------------- Delete a User -----------------------------------------

    @DeleteMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<User> delete(@PathVariable final Long id) {
        logger.info("Fetching & Deleting User with id {}", id);

        User user = userService.getUserById(id);

        if(user == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. User with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        userService.delete(id);
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete all Client -----------------------------------------

    @DeleteMapping(value= "", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<User> deleteAll() {
        logger.info("Deleting all Users");

        userService.deleteAllUsers();
        return new ResponseEntity<User>(HttpStatus.NO_CONTENT);
    }
}
