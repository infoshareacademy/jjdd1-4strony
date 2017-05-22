package com.isacademy.jjdd1.czterystrony.repositories;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;
import com.isacademy.jjdd1.czterystrony.model.Ofe;
import com.isacademy.jjdd1.czterystrony.model.OfeRating;
import com.isacademy.jjdd1.czterystrony.model.Rating;
import com.isacademy.jjdd1.czterystrony.technicalanalysis.TimeRange;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDate;
import java.util.List;

@Stateless
public class OfeRatingRepository {

    @PersistenceContext
    EntityManager entityManager;

    public void add(OfeRating ofeRating, String ofeId) {
        Ofe ofe = entityManager.find(Ofe.class, ofeId);
        ofe.getOfeRatings().add(ofeRating);
    }

    public List<Rating> getByOfeAndDate(Ofe ofe, LocalDate date) {
        Query query = entityManager.createNamedQuery("OfeRating.getByOfeAndDate");
        query.setParameter("date", date);
        query.setParameter("ofe", ofe);
        return query.getResultList();
    }

    public void insertDataFromCsv(String filePath) {
        Query query = entityManager.createNamedQuery("OfeRating.insertDataFromCsv");
        query.setParameter("filePath", filePath);
        query.executeUpdate();
    }
}