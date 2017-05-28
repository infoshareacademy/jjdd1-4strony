package isacademy.jjdd1.czterystrony.reports.persistence.repositories;

import isacademy.jjdd1.czterystrony.reports.persistence.model.InvestFundZigzagReport;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class InvestFundZigzagReportRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<InvestFundZigzagReport> getAll() {
        Query query = entityManager.createNamedQuery("ZigzagReport.getAll");
        return query.getResultList();
    }

    public List<InvestFundZigzagReport> getInTimeRange(LocalDate from, LocalDate to) {
        Query query = entityManager.createNamedQuery("ZigzagReport.getInTimeRange");
        query.setParameter("startDateTime", from.atStartOfDay());
        query.setParameter("endDateTime", to.plusDays(1).atStartOfDay());
        return query.getResultList();
    }
}
