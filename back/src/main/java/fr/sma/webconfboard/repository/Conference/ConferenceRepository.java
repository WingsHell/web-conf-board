package fr.sma.webconfboard.repository.Conference;

import fr.sma.webconfboard.entities.Conference;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

@RepositoryRestResource
@CrossOrigin(origins = "http://localhost:4200")
public interface ConferenceRepository extends JpaRepository<Conference, Long> {

    Conference getConferenceById(Long id);
}
