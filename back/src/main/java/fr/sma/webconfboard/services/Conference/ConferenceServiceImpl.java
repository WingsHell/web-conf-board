package fr.sma.webconfboard.services.Conference;

import fr.sma.webconfboard.entities.Conference;
import fr.sma.webconfboard.repository.Conference.ConferenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.*;

@Service
public class ConferenceServiceImpl implements ConferenceService{

    private ConferenceRepository conferenceRepository;

    private static Collator COLLATOR = Collator.getInstance(Locale.FRENCH);

    @Autowired
    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) { this.conferenceRepository = conferenceRepository;}

    @Override
    public Conference getConferenceById(Long id) { return this.conferenceRepository.getConferenceById(id);}

    @Override
    public List<Conference> getAllConferences() {
        List<Conference> conferenceList = new ArrayList<>();

        conferenceRepository.findAll().forEach(e -> conferenceList.add(e));

        return conferenceList;
    }

    @Override
    public List<Conference> getConferenceByTitle(String title) {
        List<Conference> conferences = new ArrayList<>();
        List<Conference> conferenceList = new ArrayList<>();
        conferenceRepository.findAll().forEach(e -> conferences.add(e));

        for(Conference conference : conferences){
            if(conference.getTitle().equals(title)){
                conferenceList.add(conference);
            }
        }
        return conferenceList;
    }

    @Override
    public List<Conference> getConferenceIsVote(Boolean voted) {
        List<Conference> conferences = new ArrayList<>();
        List<Conference> conferenceVoted = new ArrayList<>();
        conferenceRepository.findAll().forEach(e -> conferences.add(e));

        for(Conference conference : conferences){
            if(conference.getIsVoted().equals(voted)){
                conferenceVoted.add(conference);
            }
        }
        return conferenceVoted;
    }

    @Override
    public List<Conference> getTop10ConfByRate() {
        List<Conference> top10Conf = new ArrayList<>();
        conferenceRepository.getTop10ConfByRate().forEach(e -> top10Conf.add(e));
        return top10Conf;
    }

    @Override
    public Conference findByTitle(String title) {
        List<Conference> conferences = new ArrayList<>();
        conferenceRepository.findAll().forEach(e -> conferences.add(e));

        for(Conference conference : conferences) {
            if(conference.getTitle().equalsIgnoreCase(title)) {
                return conference;
            }
        }
        return null;
    }

    @Override
    public Conference save(Conference conference) { return conferenceRepository.save(conference); }

    @Override
    public Map<String, Boolean> delete(Long id) throws ResourceNotFoundException {
        Conference conference = conferenceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Conference not found for this id :: " + id));
        conferenceRepository.delete(conference);

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return response;
    }

    @Override
    public void deleteAllConferences(){
        List<Conference> conferences = new ArrayList<>();
        conferenceRepository.findAll().forEach(e -> conferences.add(e));
        conferences.forEach(conference -> conferenceRepository.delete(conference));
    }

    @Override
    public void updateConference(Conference conference) {
        conferenceRepository.save(conference);
    }


    public boolean isConferenceExist(Conference conference) {
        return findByTitle(conference.getTitle())!=null;
    }

}
