package com.isacademy.jjdd1.czterystrony.repositories;

import com.isacademy.jjdd1.czterystrony.model.StatisticsDetails;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StatisticsDetailsRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<StatisticsDetails> getAll() {
        Query query = entityManager.createNamedQuery("Statistics.getAll");
        return query.getResultList();
    }
}
