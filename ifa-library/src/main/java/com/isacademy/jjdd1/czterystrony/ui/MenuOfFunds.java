package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.database.Statistics;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.*;
import java.util.stream.Collectors;

public class MenuOfFunds {

    private static EntityManager entityManager;
    public int menuOfFunds;

    {
        InvestFundsDaoTxt investFundDao = new InvestFundsDaoTxt();
        List<InvestFund> allByName = investFundDao.getAllByName();

        System.out.println("Lista funduszy: ");
        for (InvestFund investFund : allByName.stream()
                .sorted(Comparator.comparing(InvestFund::getPriority))
                .collect(Collectors.toList())) {
            System.out.println(investFund.getId() + " | " + investFund.getName());

//        System.out.println("Lista funduszy: ");
//        for (InvestFund investFund : allByName) {
//            if (investFund.getName().equals("AVIVA Obligacji")) {
//                investFund.promote(99);
//            }
        }
        for (InvestFund investFund : allByName.stream()
                .sorted(Comparator.comparing(InvestFund::getPriority))
                .collect(Collectors.toList())) {
            System.out.println(investFund.getName());
        }
        System.out.println("Wpisz nazwę wybranego funduszu, aby przejść dalej:");
        Scanner choice = new Scanner(System.in);
        String fund = choice.nextLine();//fundusz wybrany przez użytkownika
        InvestFund investFund = investFundDao.get(fund);


        Statistics statistics = new Statistics();
        statistics.setFund(fund);

        instertStatistics(statistics);

        MenuOfExtreme menuExtreme = new MenuOfExtreme(investFund);


    }

    private static void instertStatistics(Statistics statistics) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("example");
        entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        entityManager.persist(statistics);
        entityManager.getTransaction().commit();

    }
}