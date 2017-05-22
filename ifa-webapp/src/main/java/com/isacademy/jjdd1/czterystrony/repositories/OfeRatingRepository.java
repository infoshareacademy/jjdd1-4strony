package com.isacademy.jjdd1.czterystrony.repositories;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Ofe;
import com.isacademy.jjdd1.czterystrony.model.OfeRating;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.TimeRange;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class OfeRatingRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void add(OfeRating ofeRating, String ofeId) {
        Ofe ofe = entityManager.find(Ofe.class, ofeId);
        ofe.getOfeRatings().add(ofeRating);
    }

    public List<Rating> getByOfeAndDate(Ofe ofe, LocalDate date) {
        Query query = entityManager.createNamedQuery("OfeRating.getByFundAndDate");
        query.setParameter("date", date);
        query.setParameter("ofe", ofe);
        return query.getResultList();
    }
//
//    public List<Rating> getByFundInTimeRange(InvestFund investFund, TimeRange timeRange) {
//        Query query = entityManager.createNamedQuery("Rating.getByFundInTimeRange");
//        query.setParameter("startDate", timeRange.getStart());
//        query.setParameter("endDate", timeRange.getEnd());
//        query.setParameter("investFund", investFund);
//        return query.getResultList();
//    }
//
//    public Rating getOldestForFund(InvestFund investFund) {
//        Query query = entityManager.createNamedQuery("Rating.getOldestForFund");
//        query.setParameter("investFund", investFund);
//        return (Rating) query.getResultList().get(0);
//    }
//
//    public Rating getNewestForFund(InvestFund investFund) {
//        Query query = entityManager.createNamedQuery("Rating.getNewestForFund");
//        query.setParameter("investFund", investFund);
//        return (Rating) query.getResultList().get(0);
//    }

    public List<Rating> getAllByFund(Ofe ofe) {
        Query query = entityManager.createNamedQuery("OfeRating.getAllByFund");
        query.setParameter("ofe", ofe);
        return query.getResultList();
    }

    public void insertDataFromCsv(String filePath) {
        Query query = entityManager.createNamedQuery("Rating.insertDataFromCsv");
        query.setParameter("filePath", filePath);
        query.executeUpdate();
    }
}