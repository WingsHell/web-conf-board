package fr.sma.webconfboard.controllers;

import fr.sma.webconfboard.entities.ApiResponse;
import fr.sma.webconfboard.entities.User;
import fr.sma.webconfboard.services.User.UserServiceImpl;
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

    @Autowired
    private UserServiceImpl userService;

    Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    public UserController(UserServiceImpl userService) { this.userService = userService; }

    // ------------------- Retrieve All Users ---------------------------------------------

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<List<User>> getAllUsers() {
        logger.info("Fetching all Users");
        List<User> userList = new ArrayList<User>();
        userList = userService.findAll();
        if(userList.isEmpty()){
            logger.error("Unable to fetch an empty list");
            return new ApiResponse<List<User>>(HttpStatus.OK.value(),"Unable to fetch an empty list.",userService.findAll());
        }
        return new ApiResponse<List<User>>(HttpStatus.OK.value(),"User list fetched successfully.",userService.findAll());
    }

    // ------------------- Retrieve Single User ------------------------------------------

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<User> getUserById(@PathVariable final Long id) {
        logger.info("Fetching User with id {}", id);
        User user = userService.findById(id);
        if(user == null) {
            logger.error("User with id {} not found.", id);
            return new ApiResponse<User>(HttpStatus.NOT_FOUND.value(),"User with id " + id
                    + " not found.",userService.findById(id));

        }
        return new ApiResponse<User>(HttpStatus.OK.value(),"User with id " + id
                + "fetched successfully.",userService.findById(id));

    }

    // ------------------- Create a User -------------------------------------------

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<User> create(@RequestBody User user) {
        logger.info("Creating User : {}", user);

        if(userService.isUserExist(user)) {
            logger.error("Unable to create. A User with username {} already exist",user.getUsername());
            return new ApiResponse<>(HttpStatus.CONFLICT.value(), "Unable to create. A User with username " +
                    user.getUsername() + " already exist.",userService.save(user));

        } else if( user == null ) {
            return new ApiResponse<User>(HttpStatus.NOT_FOUND.value(), "Unable to create. User undefined.",userService.save(user));
        } else {
            userService.save(user);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(user.getId())
                    .toUri();
            ResponseEntity.created(uri)
                    .body(user);
            return new ApiResponse<User>(HttpStatus.OK.value(), "User saved successfully.",userService.save(user));

        }
    }

    // ------------------- Update a User ------------------------------------------------

    @PutMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<User> updateClient(@PathVariable final Long id, @RequestBody User user) {
        logger.info("Updating Client with id {}", id);
        User currentUser = userService.findById(id);

        if(currentUser == null) {
            logger.error("Unable to update. User with id {} not found", id);
            return new ApiResponse<User>(HttpStatus.NOT_FOUND.value(), "Unable to update. User with id " + id + " not found.",userService.findById(id));
        }

        BeanUtils.copyProperties(user, currentUser);
        userService.updateUser(currentUser);

        return new ApiResponse<User>(HttpStatus.OK.value(), "User updated successfully.",userService.findById(id));
    }

    // ------------------- Delete a User -----------------------------------------

    @DeleteMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<User> delete(@PathVariable final Long id) {
        logger.info("Fetching & Deleting User with id {}", id);
        User user = userService.findById(id);

        if(user == null) {
            logger.error("Unable to delete. User with id {} not found.", id);
            return new ApiResponse<User>(HttpStatus.NOT_FOUND.value(), "Unable to delete. User with id " + id + " not found.",userService.findById(id));
        }
        userService.delete(id);
        return new ApiResponse<User>(HttpStatus.NO_CONTENT.value(), "User delated successfully.",userService.findById(id));

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
