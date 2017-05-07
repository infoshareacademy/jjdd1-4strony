package com.isacademy.jjdd1.czterystrony.repositories;

import com.isacademy.jjdd1.czterystrony.analysis.TimeRange;
import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Rating;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class RatingRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void add(Rating rating, String investFundId) {
        InvestFund investFund = entityManager.find(InvestFund.class, investFundId);
        investFund.getRatings().add(rating);
    }

    public List<Rating> getByFundAndDate(InvestFund investFund, LocalDate date) {
        Query query = entityManager.createNamedQuery("Rating.getByFundAndDate");
        query.setParameter("date", date);
        query.setParameter("investFund", investFund);
        return query.getResultList();
    }

    public List<Rating> getByFundInTimeRange(InvestFund investFund, TimeRange timeRange) {
        Query query = entityManager.createNamedQuery("Rating.getByFundInTimeRange");
        query.setParameter("startDate", timeRange.getStart());
        query.setParameter("endDate", timeRange.getEnd());
        query.setParameter("investFund", investFund);
        return query.getResultList();
    }

    public Rating getOldestForFund(InvestFund investFund) {
        Query query = entityManager.createNamedQuery("Rating.getOldestForFund");
        query.setParameter("investFund", investFund);
        return (Rating) query.getResultList().get(0);
    }

    public Rating getNewestForFund(InvestFund investFund) {
        Query query = entityManager.createNamedQuery("Rating.getNewestForFund");
        query.setParameter("investFund", investFund);
        return (Rating) query.getResultList().get(0);
    }

    public List<Rating> getAllByFund(InvestFund investFund) {
        Query query = entityManager.createNamedQuery("Rating.getAllByFund");
        query.setParameter("investFund", investFund);
        return query.getResultList();
    }

    public void insertDataFromCsv(String filePath) {
        Query query = entityManager.createNamedQuery("Rating.insertDataFromCsv");
        query.setParameter("filePath", filePath);
        query.executeUpdate();
    }
}