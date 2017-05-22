package com.isacademy.jjdd1.czterystrony.repositories;


import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Ofe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

public class OfeRepository {
    @Stateless
    public class InvestFundRepository {

        private static Logger log = LoggerFactory.getLogger(OfeRepository.class);

        @PersistenceContext
        EntityManager entityManager;

        public void add(Ofe ofe) {
            entityManager.persist(ofe);
            log.info("Added new ofe: {}", ofe.getId());
        }

        public InvestFund getById(String id) {
            return entityManager.find(InvestFund.class, id);
        }

        public List<InvestFund> getAll() {
            Query query = entityManager.createQuery("SELECT o FROM Ofe o");
            return query.getResultList();
        }
    }
}
