package com.isacademy.jjdd1.czterystrony.repositories;

import com.isacademy.jjdd1.czterystrony.model.StatisticDetails;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
public class StatisticDetailsRepository {

    @PersistenceContext
    EntityManager entityManager;

    public List<StatisticDetails> getAll() {
        Query query = entityManager.createNamedQuery("Statistics.getAll");
        return query.getResultList();
    }
}
