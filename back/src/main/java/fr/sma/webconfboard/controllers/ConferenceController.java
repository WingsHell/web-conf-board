package fr.sma.webconfboard.controllers;

import fr.sma.webconfboard.entities.Conference;
import fr.sma.webconfboard.services.Conference.ConferenceServiceImpl;
import fr.sma.webconfboard.util.CustomErrorType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.util.*;

@RestController
@RequestMapping("/conferences")
@CrossOrigin(origins = "*") // http://localhost:4200
public class ConferenceController {

    private ConferenceServiceImpl conferenceService;

    Logger logger = LoggerFactory.getLogger(ConferenceController.class);

    @Autowired
    public ConferenceController(ConferenceServiceImpl conferenceService) { this.conferenceService = conferenceService; }

    // ------------------- Retrieve All Conferences ---------------------------------------------

    @GetMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<List<Conference>> getAllConferences() {
        logger.info("Fetching all Conference");

        List<Conference> conferenceList = new ArrayList<Conference>();
        conferenceList = conferenceService.getAllConferences();
        if(conferenceList.isEmpty()){
            logger.error("Unable to fetch an empty list");
            return new ResponseEntity(new CustomErrorType("Conferences not found"), HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }

        //logger.info(clientList.toString());

        return new ResponseEntity<List<Conference>>(conferenceList, HttpStatus.OK);
    }

    // ------------------- Retrieve Single Conference ------------------------------------------

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Conference> getConferenceById(@PathVariable final Long id) {
        logger.info("Fetching Conference with id {}", id);
        Conference conference = conferenceService.getConferenceById(id);

        if(conference == null) {
            logger.error("Conference with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Conference with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Conference>(conference, HttpStatus.OK);
    }

    // ------------------- Retrieve Conference By Title ------------------------------------------

    @GetMapping(value = "/title=/{title}", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<List<Conference>> getConferenceByTitle(@PathVariable final String title) {
        logger.info("Fetching Conference with title {}", title);
        List<Conference> conferences = conferenceService.getConferenceByTitle(title);

        if(conferences == null) {
            logger.error("Conference with title {} not found.", title);
            return new ResponseEntity(new CustomErrorType("Conferences with title " + title
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(conferences, HttpStatus.OK);
    }

    // ------------------- Create a Conference -------------------------------------------

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Conference> create(@RequestBody Conference conference) {
        logger.info("Creating Conference : {}", conference);

        if(conferenceService.isConferenceExist(conference)) {
            logger.error("Unable to create. A Conference with title {} already exist",conference.getTitle());
            return new ResponseEntity(new CustomErrorType("Unable to create. A Conference with title " +
                    conference.getTitle() + " already exist."),HttpStatus.CONFLICT);
        } else if( conference == null ) {
            return ResponseEntity.notFound().build();
        } else {
            conferenceService.save(conference);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                    .path("/{id}")
                    .buildAndExpand(conference.getId())
                    .toUri();

            return ResponseEntity.created(uri)
                    .body(conference);
        }
    }

    // ------------------- Update a Conference ------------------------------------------------

    @PutMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Conference> updateConference(@PathVariable final Long id, @RequestBody Conference conference) {
        logger.info("Updating Conference with id {}", id);

        Conference currentConference = conferenceService.getConferenceById(id);

        if(currentConference == null) {
            logger.error("Unable to update. Conference with id {} not found", id);
            return new ResponseEntity(new CustomErrorType("Unable to update. Conference with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }

        BeanUtils.copyProperties(conference, currentConference);
        conferenceService.updateConference(currentConference);

        return new ResponseEntity<Conference>(conference, HttpStatus.OK);
    }
    // ------------------- Delete a Conference -----------------------------------------

    @DeleteMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Conference> delete(@PathVariable final Long id) {
        logger.info("Fetching & Deleting Conference with id {}", id);

        Conference conference = conferenceService.getConferenceById(id);

        if(conference == null) {
            logger.error("Unable to delete. Conference with id {} not found.", id);
            return new ResponseEntity(new CustomErrorType("Unable to delete. Conference with id " + id + " not found."), HttpStatus.NOT_FOUND);
        }
        conferenceService.delete(id);
        return new ResponseEntity<Conference>(HttpStatus.NO_CONTENT);
    }

    // ------------------- Delete all Conference -----------------------------------------

    @DeleteMapping(value= "", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ResponseEntity<Conference> deleteAll() {
        logger.info("Deleting all Conferences");

        conferenceService.deleteAllConferences();
        return new ResponseEntity<Conference>(HttpStatus.NO_CONTENT);
    }
}
