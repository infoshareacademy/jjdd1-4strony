package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public class TerminalMenu {

    public static void main(String[] args) {
        findExtremaForGivenInvestFound("AIG006");
//        findExtremaInAllInvestFunds();
    }

    public static void findExtremaForGivenInvestFound(String investFundName) {
        InvestFundDAO investFundDAO = new InvestFundDAO();
        InvestFund investFund = investFundDAO.getInvestFund(investFundName);
        ExtremaFinder extremaFinder = new GlobalExtremaFinder(investFund);
        List<Rating> maximumExtremaRatings = extremaFinder.getMaximumExtremaRatings();

        System.out.println("Local maximum extrema (found " + maximumExtremaRatings.size() + " ratings):");
        for (Rating rating : maximumExtremaRatings) {
            System.out.println(rating);
        }
    }

    public static void findExtremaInAllInvestFunds() {
        InvestFundDAO investFundDAO = new InvestFundDAO();

        for (String investFundName : investFundDAO.getAllInvestFunds().keySet()) {
            InvestFund investFund = investFundDAO.getInvestFund(investFundName);
            ExtremaFinder extremaFinder = new GlobalExtremaFinder(investFund);
            List<Rating> maximumExtremaRatings = extremaFinder.getMaximumExtremaRatings();

            System.out.println(investFundName);
            System.out.println("Local maximum extrema (found " + maximumExtremaRatings.size() + " ratings):");
            for (Rating rating : maximumExtremaRatings) {
                System.out.println(rating);
            }
        }
    }

}