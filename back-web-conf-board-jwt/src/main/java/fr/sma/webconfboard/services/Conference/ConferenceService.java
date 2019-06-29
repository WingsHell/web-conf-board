package fr.sma.webconfboard.services.Conference;

import fr.sma.webconfboard.entities.Conference;

import java.util.List;
import java.util.Map;

public interface ConferenceService {

    Conference findById(Long id);

    Conference findByTitle(String title);

    List<Conference> findAll();

    List<Conference> getConferencesByTitle(String title);

    List<Conference> getConferenceIsVote(Boolean vote);

    List<Conference> getTop10ConfByRate();

    Conference save(Conference conference);

    void delete(Long id);

    void deleteAllConferences();

    Conference updateConference(Conference conference);

    boolean isConferenceExist(Conference conference);
}
