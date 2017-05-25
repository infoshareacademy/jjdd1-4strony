package isacademy.jjdd1.czterystrony.webapp.persistance.repositories;

import isacademy.jjdd1.czterystrony.webapp.persistance.model.PensionFund;
import isacademy.jjdd1.czterystrony.webapp.persistance.model.PensionFundRating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class PensionFundRatingRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void add(PensionFundRating pensionFundRating, String ofeId) {
        PensionFund pensionFund = entityManager.find(PensionFund.class, ofeId);
        pensionFund.getPensionFundRatings().add(pensionFundRating);
    }

    public List<PensionFundRating> getByOfeAndDate(PensionFund pensionFund, LocalDate date) {
        Query query = entityManager.createNamedQuery("PensionFundRating.getByPensionFundAndDate");
        query.setParameter("date", date);
        query.setParameter("pensionFund", pensionFund);
        return query.getResultList();
    }

    public void insertDataFromCsv(String filePath) {
        Query query = entityManager.createNamedQuery("PensionFundRating.insertDataFromCsv");
        query.setParameter("filePath", filePath);
        query.executeUpdate();
    }
}