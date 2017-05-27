package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.InvestFundRating;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class InvestFundRatingRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    InvestFundRepository repository;

    public void add(InvestFundRating rating, String investFundId) {
        InvestFund investFund = entityManager.find(InvestFund.class, investFundId);
        investFund.getRatings().add(rating);
    }

    public List<InvestFundRating> getByFundAndDate(String id, LocalDate date) {
        Query query = entityManager.createNamedQuery("Rating.getByFundAndDate");
        query.setParameter("date", date);
        query.setParameter("investFund", getFundById(id));
        return query.getResultList();
    }

    public List<InvestFundRating> getByFundInTimeRange(String id, TimeRange timeRange) {
        Query query = entityManager.createNamedQuery("Rating.getByFundInTimeRange");
        query.setParameter("startDate", timeRange.getStart());
        query.setParameter("endDate", timeRange.getEnd());
        query.setParameter("investFund", getFundById(id));
        return query.getResultList();
    }

    public InvestFundRating getOldestForFund(String id) {
        Query query = entityManager.createNamedQuery("Rating.getOldestForFund");
        query.setParameter("investFund", getFundById(id));
        return (InvestFundRating) query.getResultList().get(0);
    }

    public InvestFundRating getNewestForFund(String id) {
        Query query = entityManager.createNamedQuery("Rating.getNewestForFund");
        query.setParameter("investFund", getFundById(id));
        return (InvestFundRating) query.getResultList().get(0);
    }

    public List<InvestFundRating> getAllByFund(String id) {
        Query query = entityManager.createNamedQuery("Rating.getAllByFund");
        query.setParameter("investFund", getFundById(id));
        return query.getResultList();
    }

    public void insertDataFromCsv(String filePath) {
        Query query = entityManager.createNamedQuery("Rating.insertDataFromCsv");
        query.setParameter("filePath", filePath);
        query.executeUpdate();
    }

    private InvestFund getFundById(String id) {
        return repository.getById(id);
    }
}