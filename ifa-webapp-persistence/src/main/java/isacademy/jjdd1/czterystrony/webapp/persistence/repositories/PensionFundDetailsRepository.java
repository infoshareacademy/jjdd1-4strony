package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.PensionFundDetails;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PensionFundDetailsRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<PensionFundDetails> getAll() {
        Query query = entityManager.createNamedQuery("PensionFund.getAllWithDetails");
        return query.getResultList();
    }
}
