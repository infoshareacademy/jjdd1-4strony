package com.isacademy.jjdd1.czterystrony.repositories;

import com.isacademy.jjdd1.czterystrony.model.InvestFundStatistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class InvestFundStatisticsRepository {

    private static Logger log = LoggerFactory.getLogger(InvestFundStatistics.class);

    @PersistenceContext
    EntityManager entityManager;

    public void add(InvestFundStatistics investFundStatistics) {
        entityManager.persist(investFundStatistics);
        log.info("Added new statistics record for fund {}", investFundStatistics.getId());
    }
}
