package com.isacademy.jjdd1.czterystrony.repository;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class RatingRepository {

    private static Logger log = LoggerFactory.getLogger(InvestFundRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void add(Rating rating, String investFundId) {
        InvestFund investFund = entityManager.find(InvestFund.class, investFundId);
        investFund.getRatings().add(rating);
    }

    public List<Rating> queryByDateAndFund(LocalDate date, InvestFund investFund) {
        Query query = entityManager.createQuery(
                "SELECT r FROM Rating r WHERE " +
                        "r.date = :date AND r.investFund = :investFund");
        query.setParameter("date", date);
        query.setParameter("investFund", investFund);
        return query.getResultList();
    }

    public void insertDataFromCsv(String filePath) {
        String sql = "LOAD DATA LOCAL INFILE :filePath INTO TABLE Rating FIELDS TERMINATED BY ',' LINES TERMINATED BY '\r\n' IGNORE 1 LINES (investFund_id, @date, open, high, low, close, @notimported) SET date = str_to_date(@date, '%Y%m%d')";
        Query query = entityManager.createNativeQuery(sql);
        query.setParameter("filePath", filePath);
        query.executeUpdate();
        log.info("Ratings added to database from file: {}", filePath);
    }
}