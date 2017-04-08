package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        findLocalExtremaForGivenInvestFundTest("TEST");
        findLocalExtremaOfAllInvestFundsTest();
        printAllInvestFundsByNameTest();
        printAllInvestFundsByPriorityTest();
    }

    public static void findLocalExtremaForGivenInvestFundTest(String investFundName) {
        InvestFundsDao investFundsDao = new TextInvestFundsDaoImpl();
        InvestFund investFund = investFundsDao.get(investFundName);

        LocalExtremaFinderConfigurator localExtremaFinderConfigurator = new LocalExtremaFinderConfigurator(1, 1, BigDecimal.valueOf(0.5D), BigDecimal.valueOf(0.5D));
        LocalExtremaFinder localExtremaFinder = new LocalExtremaFinder(investFund, localExtremaFinderConfigurator);
        List<Rating> maximumExtremaRatings = localExtremaFinder.getMaximumExtremaRatings();

        System.out.println("Local maximum extrema (found " + maximumExtremaRatings.size() + " ratings):");
        for (Rating rating : maximumExtremaRatings) {
            System.out.println(rating);
        }
    }

    public static void findLocalExtremaOfAllInvestFundsTest() {
        InvestFundsDao investFundsDao = new TextInvestFundsDaoImpl();

        for (InvestFund investFund : investFundsDao.getAllByName()) {
            LocalExtremaFinderConfigurator localExtremaFinderConfigurator = new LocalExtremaFinderConfigurator(1, 1, BigDecimal.valueOf(0.5D), BigDecimal.valueOf(0.5D));
            LocalExtremaFinder localExtremaFinder = new LocalExtremaFinder(investFund, localExtremaFinderConfigurator);
            List<Rating> maximumExtremaRatings = localExtremaFinder.getMaximumExtremaRatings();
            System.out.println("\n" + investFund.getName() + " || Local maximum extrema (found " + maximumExtremaRatings.size() + " ratings):");
            for (Rating rating : maximumExtremaRatings) {
                System.out.println(rating);
            }
        }
    }

    public static void printAllInvestFundsByNameTest() {
        InvestFundsDao investFundsDao = new TextInvestFundsDaoImpl();

        System.out.println("\n\nInvest funds sorted by name:");
        for (InvestFund investFund : investFundsDao.getAllByName()) {
            System.out.println(investFund.getName());
        }
    }

    public static void printAllInvestFundsByPriorityTest() {
        InvestFundsDao investFundsDao = new TextInvestFundsDaoImpl();

        System.out.println("\n\nInvest funds sorted by priority:");
        for (InvestFund investFund : investFundsDao.getAllByPriority()) {
            System.out.println(investFund.getName() + " " + -investFund.getPriority());
        }
    }
}