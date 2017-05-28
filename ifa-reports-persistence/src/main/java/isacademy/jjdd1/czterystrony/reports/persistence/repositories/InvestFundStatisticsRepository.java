package isacademy.jjdd1.czterystrony.reports.persistence.repositories;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.InvestFundStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class InvestFundStatisticsRepository {

    private static Logger log = LoggerFactory.getLogger(InvestFundStatisticsRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    public Long add(InvestFundStatistics investFundStatistics) {
        entityManager.persist(investFundStatistics);
        log.info("Added new statistics record for fund {}", investFundStatistics.getId());
        return investFundStatistics.getId();
    }

    public InvestFundStatistics getById(Long id) {
        return entityManager.find(InvestFundStatistics.class, id);
    }

    public List<InvestFundStatistics> getInTimeRange(LocalDate from, LocalDate to) {
        //language=MySQL
        String sql = "SELECT p FROM InvestFundStatistics p WHERE p.DATE_TIME >= :startDateTime AND p.DATE_TIME <= :endDateTime";
        Query query = entityManager.createQuery(sql);
        query.setParameter("startDateTime", from.atStartOfDay());
        query.setParameter("endDateTime", to.plusDays(1).atStartOfDay());
        return query.getResultList();
    }
}
