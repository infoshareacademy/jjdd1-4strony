package com.isacademy.jjdd1.czterystrony;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;
import com.isacademy.jjdd1.czterystrony.utilities.GlobalExtremaProvider;
import com.isacademy.jjdd1.czterystrony.utilities.LocalExtremaProvider;
import com.isacademy.jjdd1.czterystrony.utilities.TimeRange;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        findLocalExtremaForGivenInvestFundTest("AGI001");
        findLocalExtremaOfAllInvestFundsTest();
        printAllInvestFundsByNameTest();
        printAllInvestFundsByPriorityTest();
        findGlobalExtremaForGivenInvestFundTest("AGI001");
    }

    public static void findLocalExtremaForGivenInvestFundTest(String investFundName) {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();
        InvestFund investFund = investFundsDao.get(investFundName);

        TimeRange timeRange = new TimeRange(LocalDate.parse("2010-01-01"), LocalDate.parse("2014-01-01"));
        LocalExtremaProvider localExtremaProvider = new LocalExtremaProvider(investFund, timeRange);

        List<Rating> extremaRatings = localExtremaProvider.findExtrema(5);

        System.out.println("Local maximum extrema (found " + extremaRatings.size() + " ratings):");
        for (Rating rating : extremaRatings) {
            System.out.println(rating);
        }
    }

    public static void findLocalExtremaOfAllInvestFundsTest() {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();

        for (InvestFund investFund : investFundsDao.getAllByName()) {
            LocalExtremaProvider localExtremaProvider = new LocalExtremaProvider(investFund);
            List<Rating> extremaRatings = localExtremaProvider.findExtrema(15);

            System.out.println("\n" + investFund.getName() + " || Local maximum extrema (found " + extremaRatings.size() + " ratings):");
            for (Rating rating : extremaRatings) {
                System.out.println(rating);
            }
        }
    }

    public static void findGlobalExtremaForGivenInvestFundTest(String investFundName) {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();
        InvestFund investFund = investFundsDao.get(investFundName);

        GlobalExtremaProvider globalExtremaProvider = new GlobalExtremaProvider(investFund);

        System.out.println("\n\n" + investFund.getName() + " || Global extrema:");
        System.out.println("Maximum: " + globalExtremaProvider.getGlobalMaximum());
        System.out.println("Minimum: " + globalExtremaProvider.getGlobalMinimum());
    }

    public static void printAllInvestFundsByNameTest() {
        InvestFundsDaoTxt investFundsDao = new InvestFundsDaoTxt();

        System.out.println("\n\nInvest funds sorted by name:");
        for (InvestFund investFund : investFundsDao.getAllByName()) {
            System.out.println("ID: " + investFund.getId() + " | Name: " +investFund.getName());
        }
    }

    public static void printAllInvestFundsByPriorityTest() {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();

        System.out.println("\n\nInvest funds sorted by priority:");
        for (InvestFund investFund : investFundsDao.getAllByPriority()) {
            System.out.println(investFund.getId() + " " + -investFund.getPriority());
        }
    }
}