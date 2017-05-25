package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.PensionFund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PensionFundRepository {

    private static Logger log = LoggerFactory.getLogger(PensionFundRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    public void add(PensionFund pensionFund) {
        entityManager.persist(pensionFund);
        log.info("Added new pensionFund: {}", pensionFund.getId());
    }

    public PensionFund getById(String id) {
        return entityManager.find(PensionFund.class, id);
    }

    public List<PensionFund> getAll() {
        Query query = entityManager.createQuery("SELECT p FROM PensionFund p");
        return query.getResultList();
    }
}
