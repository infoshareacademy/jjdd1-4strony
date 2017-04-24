package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.database.Statistics;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import java.time.LocalDate;
import java.util.*;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuOfFunds {
    public int menuOfFunds;

    private static EntityManager entityManager;
    private final static Logger LOGGER = LoggerFactory.getLogger(MenuOfFunds.class);
    private final int PROMOTED_VALUE = 99;
    private final String FUND_TO_PROMOTE = "AVIVA Obligacji";

    public MenuOfFunds () {
        InvestFundsDaoTxt investFundDao = new InvestFundsDaoTxt();
        List<InvestFund> allByName = investFundDao.getAllByName();


        if (investFundDao.getAllByName().size() > 0) {
            LOGGER.trace("Succesfully loaded " + +investFundDao.getAllByName().size() + " funds.");
        } else {
            LOGGER.error("There is no funds available.");
        }

        for (InvestFund investFund : allByName) {
            if (investFund.getName().equals(FUND_TO_PROMOTE)) {
                investFund.promote(PROMOTED_VALUE);
                LOGGER.trace("Promoted fund "+ FUND_TO_PROMOTE + " is set with priority: " + PROMOTED_VALUE);
            }
        }
        System.out.println("Lista funduszy: ");
        for (InvestFund investFund : allByName.stream()
                .sorted(Comparator.comparing(InvestFund::getPriority))
                .collect(Collectors.toList())) {
            System.out.println(investFund.getId() + " | " + investFund.getName());
        }
        System.out.println("Aby przejść dalej wpisz kod identyfikacyjny wybranego funduszu (TEN PO LEWEJ) :");
        Scanner choice = new Scanner(System.in);
        String fund = choice.nextLine().toUpperCase();

        /*MostOftenVerifiedFunds mostOftenVerifiedFunds = new MostOftenVerifiedFunds();
        mostOftenVerifiedFunds.checkAndcountVerifiedFunds(fund);*/

        Statistics statistics = new Statistics();
        statistics.setFund(fund);
        Date date = new Date();
        statistics.setDate(date);

        insertStatistics(statistics);


        try {
            InvestFund investFund = investFundDao.get(fund);
            LOGGER.debug("Chosen fund: " + investFund.getName());
            MenuOfExtreme menuExtreme = new MenuOfExtreme(investFund);
        } catch (NoSuchElementException e){
            System.out.println("Nie ma takiego elementu |" + fund + "| wybierz jeszcze raz");
            LOGGER.warn("No such ID fund");
            new MenuOfFunds();
        }

        try {
            InvestFund investFund = investFundDao.get(fund);
            MenuOfExtreme menuExtreme = new MenuOfExtreme(investFund);
        } catch (NoSuchElementException e){
            System.out.println("Nie ma takiego elementu |" + fund + "| wybierz jeszcze raz");
            new MenuOfFunds();
        }
    }

    private static void insertStatistics(Statistics statistics) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(statistics);
        entityManager.getTransaction().commit();

    }
}