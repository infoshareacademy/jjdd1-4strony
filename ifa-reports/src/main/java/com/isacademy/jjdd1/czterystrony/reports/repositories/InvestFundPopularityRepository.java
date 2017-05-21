package com.isacademy.jjdd1.czterystrony.reports.repositories;

import com.isacademy.jjdd1.czterystrony.reports.model.InvestFundsPopularity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class InvestFundPopularityRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void add(InvestFundsPopularity investFundsPopularity) {
        entityManager.persist(investFundsPopularity);
    }

    public void add(List<InvestFundsPopularity> list) {
        list.forEach(r -> entityManager.persist(r));
    }

    public List<InvestFundsPopularity> getInTimeRange(LocalDate from, LocalDate to) {
        Query query = entityManager.createQuery("SELECT s FROM InvestFundsPopularity s");
//                .createNamedQuery("Popularity.getInTimeRange");
//        query.setParameter("startDate", from);
//        query.setParameter("endDate", to);
        return query.getResultList();
    }
}
