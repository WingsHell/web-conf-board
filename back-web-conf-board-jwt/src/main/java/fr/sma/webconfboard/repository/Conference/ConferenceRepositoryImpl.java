package fr.sma.webconfboard.repository.Conference;

import fr.sma.webconfboard.entities.Conference;

import java.util.ArrayList;
import java.util.List;

abstract class ConferenceRepositoryImpl implements ConferenceRepository {

    private ConferenceRepository conferenceRepository;

    /*@Override
    public Conference getConferenceById(Long id) {
        List<Conference> conferenceList = new ArrayList<>();
        conferenceRepository.findAll().forEach(e -> conferenceList.add(e));
        for(Conference c : conferenceList) {
            if (c.getId() == id) return c;
        }
        return null;
    }*/
}
