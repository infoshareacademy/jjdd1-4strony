package isacademy.jjdd1.czterystrony.reports.persistence.repositories;

import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundPopularity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class InvestFundPopularityRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<InvestFundPopularity> getAll() {
        Query query = entityManager.createNamedQuery("InvestFundPopularity.getAll");
        return query.getResultList();
    }

    public List<InvestFundPopularity> getInTimeRange(LocalDate from, LocalDate to) {
        Query query = entityManager.createNamedQuery("InvestFundPopularity.getInTimeRange");
        query.setParameter("startDateTime", from.atStartOfDay());
        query.setParameter("endDateTime", to.plusDays(1).atStartOfDay());
        return query.getResultList();
    }
}
