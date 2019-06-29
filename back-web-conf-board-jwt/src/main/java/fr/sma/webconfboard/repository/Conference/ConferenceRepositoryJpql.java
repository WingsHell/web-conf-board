package fr.sma.webconfboard.repository.Conference;

import fr.sma.webconfboard.entities.Conference;

import java.util.List;

public interface ConferenceRepositoryJpql {

    List<Conference> getTop10ConfByRate();
}
