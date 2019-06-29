package fr.sma.webconfboard.repository.Conference;

import fr.sma.webconfboard.entities.Conference;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class ConferenceRepositoryJpqlImpl implements ConferenceRepositoryJpql {

    @PersistenceContext
    private EntityManager em;

    public List<Conference> getTop10ConfByRate() {
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<Conference> query = cb.createQuery(Conference.class);
        Root<Conference> root = query.from(Conference.class);
        query.select(root);
        query.orderBy(cb.desc(root.get("rate")));
        List<Conference> list = em.createQuery(query).setMaxResults(10).getResultList();
        return list;
    }
}
