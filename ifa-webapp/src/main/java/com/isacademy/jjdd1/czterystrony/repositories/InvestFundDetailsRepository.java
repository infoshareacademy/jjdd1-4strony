package com.isacademy.jjdd1.czterystrony.repositories;

import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class InvestFundDetailsRepository {

    @PersistenceContext
    EntityManager entityManager;

    public InvestFundDetails getById(String id) {
        Query query = entityManager.createNamedQuery("InvestFund.getByIdWithDetails");
        query.setParameter("id", id);
        return (InvestFundDetails) query.getResultList().get(0);
    }

    public List<InvestFundDetails> getAll() {
        Query query = entityManager.createNamedQuery("InvestFund.getAllWithDetails");
        return query.getResultList();
    }
}