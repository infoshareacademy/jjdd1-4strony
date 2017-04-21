package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.database.Statistics;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class MenuOfFunds {

    private static EntityManager entityManager;
    private final int PROMOTED_VALUE = 99;
    private final String FUND_TO_PROMOTE = "AVIVA Obligacji";

    public MenuOfFunds () {
        InvestFundsDaoTxt investFundDao = new InvestFundsDaoTxt();
        List<InvestFund> allByName = investFundDao.getAllByName();



        for (InvestFund investFund : allByName) {
            if (investFund.getName().equals(FUND_TO_PROMOTE)) {
                investFund.promote(PROMOTED_VALUE);
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
        String fund = choice.nextLine();

        Statistics statistics = new Statistics();
        statistics.setFund(fund);
        Date date = new Date();
        statistics.setDate(date);

        insertStatistics(statistics);

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