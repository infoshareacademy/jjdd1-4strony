package isacademy.jjdd1.czterystrony.webapp.persistence.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistence.model.PensionFund;
import isacademy.jjdd1.czterystrony.webapp.persistence.model.PensionFundRating;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class PensionFundRatingRepository {

    @PersistenceContext
    EntityManager entityManager;

    @Inject
    PensionFundRepository repository;

    public void add(PensionFundRating pensionFundRating, String ofeId) {
        PensionFund pensionFund = entityManager.find(PensionFund.class, ofeId);
        pensionFund.getRatings().add(pensionFundRating);
    }

    public List<PensionFundRating> getByFundAndDate(String id, LocalDate date) {
        Query query = entityManager.createNamedQuery("PensionFundRating.getByPensionFundAndDate");
        query.setParameter("date", date);
        query.setParameter("pensionFund", getFundById(id));
        return query.getResultList();
    }

    public void insertDataFromCsv(String filePath) {
        Query query = entityManager.createNamedQuery("PensionFundRating.insertDataFromCsv");
        query.setParameter("filePath", filePath);
        query.executeUpdate();
    }

    private PensionFund getFundById(String id) {
        return repository.getById(id);
    }
}