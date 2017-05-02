package com.isacademy.jjdd1.czterystrony.repositories;

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
        retrievedInvestFund.setName(investFund.getName());
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

    public List<Object[]> getAllWithCurrentRating() {
        //language=MySQL
        String sql = "SELECT f.name, f.id, f.priority, tab2.date, tab2.close, tab2.diff " +
                "FROM InvestFund AS f JOIN (SELECT r1.investFund_id, r1.date, r1.close, " +
                "ROUND((r1.close - r2.close)/r2.close*100, 2) AS diff " +
                "FROM (SELECT r.investFund_id, r.date, r.close, r.row_number " +
                "FROM(SELECT Rating. *, (@i:=if(@fund = Rating.investFund_id,@i +1, " +
                "if(@fund:=Rating.investFund_id, 1, 1))) AS row_number " +
                "FROM Rating CROSS JOIN (SELECT @i :=0, @fund :=NULL)c " +
                "ORDER BY Rating.investFund_id, Rating.date DESC) AS r " +
                "WHERE r.row_number <= 2) AS r1 " +
                "JOIN (SELECT r.investFund_id, r.date, r.close, r.row_number " +
                "FROM (SELECT Rating. *, (@i:=if(@fund = Rating.investFund_id,@i +1, " +
                "if(@fund:=Rating.investFund_id, 1, 1))) AS row_number " +
                "FROM Rating CROSS JOIN (SELECT @i:=0, @fund:=NULL) c " +
                "ORDER BY Rating.investFund_id, Rating.date DESC) AS r " +
                "WHERE r.row_number <= 2) AS r2 ON r1.investFund_id = r2.investFund_id " +
                "WHERE r1.row_number = 1 AND r2.row_number = 2) tab2 " +
                "ON f.id = tab2.investFund_id";

//        Query query = entityManager.createQuery("SELECT f, r.close FROM InvestFund f JOIN Rating r ON f.id = r.investFund WHERE r.date = f.lastRatingDate");
        Query query = entityManager.createNativeQuery(sql);
        return query.getResultList();
    }

    public List<Object[]> getPromotedWithCurrentRating() {
        Query query = entityManager.createQuery("SELECT f, r.close FROM InvestFund f JOIN Rating r ON f.id = r.investFund WHERE r.date = f.lastRatingDate AND f.priority > 0 ORDER BY f.priority DESC, f.name ASC");
        return query.getResultList();
    }

    public List<Object[]> getNotPromotedWithCurrentRating() {
        Query query = entityManager.createQuery("SELECT f, r.close FROM InvestFund f JOIN Rating r ON f.id = r.investFund WHERE r.date = f.lastRatingDate AND f.priority = 0 ORDER BY f.name DESC");
        return query.getResultList();
    }
}