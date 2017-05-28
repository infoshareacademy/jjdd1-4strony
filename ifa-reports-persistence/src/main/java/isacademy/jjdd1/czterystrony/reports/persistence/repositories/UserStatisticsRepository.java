package isacademy.jjdd1.czterystrony.reports.persistence.repositories;

import isacademy.jjdd1.czterystrony.reports.persistence.model.statistics.UserStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class UserStatisticsRepository {

    private static Logger log = LoggerFactory.getLogger(UserStatisticsRepository.class);

    @PersistenceContext
    EntityManager entityManager;

    public Long add(UserStatistics userStatistics) {
        entityManager.persist(userStatistics);
        log.info("Added new user statistics record for user {}", userStatistics.getEmail());
        return userStatistics.getId();
    }

    public UserStatistics getById(Long id) {
        return entityManager.find(UserStatistics.class, id);
    }

    public List<UserStatistics> getInTimeRange(LocalDate from, LocalDate to) {
        //language=MySQL
        String sql = "SELECT u FROM UserStatistics u WHERE u.LOGIN_DATE_TIME >= :startDateTime AND p.dateTime <= :endDateTime";
        Query query = entityManager.createQuery(sql);
        query.setParameter("startDateTime", from.atStartOfDay());
        query.setParameter("endDateTime", to.plusDays(1).atStartOfDay());
        return query.getResultList();
    }
}
