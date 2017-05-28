package isacademy.jjdd1.czterystrony.reports.persistence.repositories;

import isacademy.jjdd1.czterystrony.reports.persistence.model.ZigzagReport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class ZigzagReportRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<ZigzagReport> getAll() {
        Query query = entityManager.createNamedQuery("ZigzagReport");
        return query.getResultList();
    }
}
