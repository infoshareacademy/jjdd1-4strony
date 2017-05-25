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

    public void add(List<InvestFundPopularity> list) {
        list.forEach(r -> entityManager.persist(r));
    }

    public List<InvestFundPopularity> getAll() {
        Query query = entityManager.createQuery("SELECT p FROM InvestFundPopularity p");
        return query.getResultList();
    }

    public List<InvestFundPopularity> getInTimeRange(LocalDate from, LocalDate to) {
        Query query = entityManager.createQuery("SELECT p FROM InvestFundPopularity p WHERE p.date >= :startDate AND p.date <= :endDate");
        query.setParameter("startDate", from);
        query.setParameter("endDate", to);
        return query.getResultList();
    }
}
