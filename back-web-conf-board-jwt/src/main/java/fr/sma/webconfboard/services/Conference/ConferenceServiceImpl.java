package fr.sma.webconfboard.services.Conference;

import fr.sma.webconfboard.entities.Conference;
import fr.sma.webconfboard.repository.Conference.ConferenceRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service(value = "conferenceService")
public class ConferenceServiceImpl implements ConferenceService{

    @Autowired
    private ConferenceRepository conferenceRepository;

    @Autowired
    public ConferenceServiceImpl(ConferenceRepository conferenceRepository) { this.conferenceRepository = conferenceRepository;}

    @Override
    public Conference findById(Long id) {
        Optional<Conference> optionalUser = conferenceRepository.findById(id);
        return optionalUser.isPresent() ? optionalUser.get() : null;
    }

    @Override
    public List<Conference> findAll() {
        List<Conference> conferenceList = new ArrayList<>();
        conferenceRepository.findAll().iterator().forEachRemaining(conferenceList::add);
        return conferenceList;
    }

    @Override
    public List<Conference> getConferencesByTitle(String title) {
        List<Conference> conferences = new ArrayList<>();
        List<Conference> conferenceList = new ArrayList<>();
        conferenceRepository.findAll().iterator().forEachRemaining(conferences::add);

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
        System.out.println("------------------------------");
        System.out.println("voted = " + voted);
        System.out.println("list all : " + conferences.size());
        System.out.println("test isVoted dd'un item : " + conferences.get(1).getIsVoted());
        System.out.println("------------------------------");
        for(Conference conference : conferences){
            if(conference.getIsVoted().equals(voted)){
                conferenceVoted.add(conference);
                System.out.println("------------------------------");
                System.out.println(conference);
            }

        }
        System.out.println("--------------  ----------------");
        System.out.println(conferenceVoted);
        System.out.println("taille confVoted en fct : " + voted + " -- " +conferenceVoted.size());
        return conferenceVoted;
    }

    @Override
    public List<Conference> getTop10ConfByRate() {
        List<Conference> top10Conf = new ArrayList<>();
        conferenceRepository.getTop10ConfByRate().iterator().forEachRemaining(top10Conf::add);
        return top10Conf;
    }

    @Override
    public Conference findByTitle(String title) {
        List<Conference> conferences = new ArrayList<>();
        conferenceRepository.findAll().iterator().forEachRemaining(conferences::add);

        for(Conference conference : conferences) {
            if(conference.getTitle().equalsIgnoreCase(title)) {
                return conference;
            }
        }
        return null;
    }

    @Override
    public Conference save(Conference conference) {
        return conferenceRepository.save(conference);
    }

    @Override
    public void delete(Long id) {
        conferenceRepository.deleteById(id);
    }

    @Override
    public void deleteAllConferences(){
        List<Conference> conferences = new ArrayList<>();
        conferenceRepository.findAll().iterator().forEachRemaining(conferences::add);
        conferences.forEach(conference -> conferenceRepository.delete(conference));
    }

    @Override
    public Conference updateConference(Conference conference) {
        Conference conferenceToUpdated = findById(conference.getId());
        System.out.println(conference);
        System.out.println(conferenceToUpdated);
        if(conferenceToUpdated != null) {
            conferenceRepository.save(conferenceToUpdated);
            //BeanUtils.copyProperties(conference, conferenceToUpdated);
        }
        return conferenceToUpdated;
    }


    public boolean isConferenceExist(Conference conference) {
        return findByTitle(conference.getTitle())!=null;
    }

}
