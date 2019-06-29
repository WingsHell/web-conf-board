package fr.sma.webconfboard.controllers;

import fr.sma.webconfboard.entities.ApiResponse;
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
    public ApiResponse<List<Conference>> getAllConferences() {
        logger.info("Fetching all Conference");
        List<Conference> conferenceList = new ArrayList<Conference>();
        conferenceList = conferenceService.findAll();
        if(conferenceList.isEmpty()){
            logger.error("Unable to fetch an empty list");
            return new ApiResponse<List<Conference>>(HttpStatus.OK.value(),"Unable to fetch an empty list.",conferenceService.findAll());
        }
        return new ApiResponse<List<Conference>>(HttpStatus.OK.value(),"Conferences list fetched successfully.",conferenceService.findAll());
    }

    // ------------------- Retrieve Single Conference ------------------------------------------

    @GetMapping(value = "/{id}", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<Conference> getConferenceById(@PathVariable final Long id) {
        logger.info("Fetching Conference with id {}", id);
        Conference conference = conferenceService.findById(id);
        if(conference == null) {
            logger.error("Conference with id {} not found.", id);
            return new ApiResponse<Conference>(HttpStatus.NOT_FOUND.value(),"Conference with id " + id
                    + " not found.",conferenceService.findById(id));
        }
        return new ApiResponse<Conference>(HttpStatus.OK.value(),"Conference with id " + id
                + "fetched successfully.",conferenceService.findById(id));
    }

    // ------------------- Retrieve Conference By Title ------------------------------------------

    @GetMapping(value = "/titles/{titles}", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ApiResponse<Conference> getConferenceByTitle(@PathVariable final String title) {
        logger.info("Fetching Conference with title {}", title);
        Conference conference = conferenceService.findByTitle(title);
        if(conference == null) {
            logger.error("Conference with title {} not found.", title);
            return new ApiResponse<Conference>(HttpStatus.NOT_FOUND.value(),"Conference with title " + title
                    + " not found.",conferenceService.findByTitle(title));
        }
        return new ApiResponse<Conference>(HttpStatus.OK.value(),"Conference with title " + title
                + "fetched successfully.",conferenceService.findByTitle(title));
    }

    // ------------------- Retrieve Conferences By Vote ------------------------------------------

    @GetMapping(value = "/voted/{voted}", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ApiResponse<List<Conference>> getConferenceIsVoted(@PathVariable final Boolean voted) {
        logger.info("Fetching Conferences with isVoted {}", voted);
        List<Conference> conferences = conferenceService.getConferenceIsVote(voted);

        if(conferences == null) {
            logger.error("Conference with isVoted {} not found.", voted);

            return new ApiResponse<List<Conference>>(HttpStatus.NOT_FOUND.value(),"Conferences with isVotes " + voted
                    + " not found.",conferenceService.getConferenceIsVote(voted));
        }
        return new ApiResponse<List<Conference>>(HttpStatus.OK.value(),"Conferences with isVotes " + voted
                + "fetched successfully.",conferenceService.getConferenceIsVote(voted));
    }

    // ------------------- Retrieve Conferences top 10 by Rate---------------------------------------------

    @GetMapping(value = "/top10", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<List<Conference>> getTop10ConfByRate() {
        logger.info("Fetching top10 Conferences by rate order DESC");
        List<Conference> conferenceTop10 = new ArrayList<Conference>();
        conferenceTop10 = conferenceService.getTop10ConfByRate();
        if(conferenceTop10.isEmpty()){
            logger.error("Unable to fetch an empty list");
            return new ApiResponse<List<Conference>>(HttpStatus.NOT_FOUND.value(),"Conferences not found",conferenceService.getTop10ConfByRate());
        }
        return new ApiResponse<List<Conference>>(HttpStatus.OK.value(),"Conference Top 10 by rate list fetched successfully.",conferenceService.getTop10ConfByRate());
    }


    // ------------------- Create a Conference -------------------------------------------

    @PostMapping(value = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<Conference> create(@RequestBody Conference conference) {
        logger.info("Creating Conference : {}", conference);

        if(conferenceService.isConferenceExist(conference)) {
            logger.error("Unable to create. A Conference with title {} already exist",conference.getTitle());
            return new ApiResponse<Conference>(HttpStatus.CONFLICT.value(), "Unable to create. A Conference with title " +
                    conference.getTitle() + " already exist.",conferenceService.save(conference));

        } else if( conference == null ) {
            return new ApiResponse<Conference>(HttpStatus.NOT_FOUND.value(), "Unable to create. Conference undefined.",conferenceService.save(conference));
        } else {
            conferenceService.save(conference);
            return new ApiResponse<Conference>(HttpStatus.OK.value(), "Conference saved successfully.",conferenceService.save(conference));

        }
    }

    // ------------------- Update a Conference ------------------------------------------------

    @PutMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<Conference> updateConference(@PathVariable final Long id, @RequestBody Conference conference) {
        logger.info("Updating Conference with id {}", id);
        Conference currentConference = conferenceService.findById(id);
        if(currentConference == null) {
            logger.error("Unable to update. Conference with id {} not found", id);
            return new ApiResponse<Conference>(HttpStatus.NOT_FOUND.value(), "Unable to update. Conference with id " + id + " not found.",conferenceService.findById(id));
        }

        BeanUtils.copyProperties(conference, currentConference);
        conferenceService.updateConference(currentConference);

        return new ApiResponse<Conference>(HttpStatus.OK.value(), "Conference updated successfully.",conferenceService.updateConference(currentConference));
    }
    // ------------------- Delete a Conference -----------------------------------------

    @DeleteMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
    @ResponseBody
    public ApiResponse<Conference> delete(@PathVariable final Long id) {
        logger.info("Fetching & Deleting Conference with id {}", id);
        Conference conference = conferenceService.findById(id);

        if(conference == null) {
            logger.error("Unable to delete. Conference with id {} not found.", id);
            return new ApiResponse<Conference>(HttpStatus.NOT_FOUND.value(), "Unable to delete. Conference with id " + id + " not found.",conferenceService.findById(id));
        }
        conferenceService.delete(id);
        return new ApiResponse<Conference>(HttpStatus.NO_CONTENT.value(), "Conference delated successfully.",conferenceService.findById(id));
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
