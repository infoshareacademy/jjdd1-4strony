package com.isacademy.jjdd1.czterystrony.repository;

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
}