package com.isacademy.jjdd1.czterystrony.repository;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class InvestFundRepository {

    private static Logger log = LoggerFactory.getLogger(InvestFundRepository.class);

    @PersistenceContext
    private EntityManager entityManager;

    public void add(InvestFund investFund) {
        entityManager.persist(investFund);
        log.info("Added new invest fund: {}", investFund.getId());
    }

    public void update(InvestFund investFund) {
        InvestFund retrievedInvestFund = queryById(investFund.getId());
        retrievedInvestFund.setFullName(investFund.getFullName());
        retrievedInvestFund.setLastRatingDate(investFund.getLastRatingDate());
        retrievedInvestFund.setPriority(investFund.getPriority());
        entityManager.persist(retrievedInvestFund);
        log.info("Updated invest fund: {}", retrievedInvestFund.getId());
    }

    public InvestFund queryById(String id) {
        return entityManager.find(InvestFund.class, id);
    }

    public List<InvestFund> getAll() {
        Query query = entityManager.createQuery("SELECT f FROM InvestFund f");
        return query.getResultList();
    }
}