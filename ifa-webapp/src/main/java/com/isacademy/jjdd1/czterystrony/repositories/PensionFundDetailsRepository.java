package com.isacademy.jjdd1.czterystrony.repositories;

import com.isacademy.jjdd1.czterystrony.model.InvestFundDetails;
import com.isacademy.jjdd1.czterystrony.model.PensionFundDetails;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class PensionFundDetailsRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<PensionFundDetails> getAll() {
        Query query = entityManager.createNamedQuery("PensionFund.getAllWithDetails");
        return query.getResultList();
    }

    public PensionFundDetails getById(String id) {
        Query query = entityManager.createNamedQuery("PensionFund.getByIdWithDetails");
        query.setParameter("id", id);
        return (PensionFundDetails) query.getResultList().get(0);
    }
}
