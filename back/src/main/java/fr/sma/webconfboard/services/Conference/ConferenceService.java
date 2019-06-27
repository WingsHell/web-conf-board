package fr.sma.webconfboard.services.Conference;

import fr.sma.webconfboard.entities.Conference;

import java.util.List;
import java.util.Map;

public interface ConferenceService {

    Conference getConferenceById(Long id);

    Conference findByTitle(String title);

    List<Conference> getAllConferences();

    List<Conference> getConferenceByTitle(String title);

    List<Conference> getConferenceIsVote(Boolean vote);

    List<Conference> getTop10ConfByRate();

    Conference save(Conference conference);

    Map<String, Boolean> delete(Long id);

    void deleteAllConferences();

    void updateConference(Conference conference);

    boolean isConferenceExist(Conference conference);
}
