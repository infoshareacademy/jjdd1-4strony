package com.isacademy.jjdd1.czterystrony;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class PromotedInvestFundRepository {

    @PersistenceContext
    EntityManager em;

    public void add(PromotedInvestFund promotedInvestFund) {
        em.persist(promotedInvestFund);
        em.createQuery("SELECT i FROM PromotedInvestFund i").getResultList().forEach(System.out::println);

    }
}
