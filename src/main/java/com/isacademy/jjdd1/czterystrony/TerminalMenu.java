package com.isacademy.jjdd1.czterystrony;

import java.util.List;
import java.util.Map;

public class TerminalMenu {

    public static void main(String[] args) {
        findExtremaForGivenInvestFound("AIG006");
        findExtremaInAllInvestFunds();
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

        for (Map.Entry<String, InvestFund> entry : investFundDAO.getAllInvestFunds().entrySet()) {
            ExtremaFinder extremaFinder = new GlobalExtremaFinder(entry.getValue());
            List<Rating> maximumExtremaRatings = extremaFinder.getMaximumExtremaRatings();

            System.out.println(entry.getKey());
            System.out.println("Local maximum extrema (found " + maximumExtremaRatings.size() + " ratings):");
            for (Rating rating : maximumExtremaRatings) {
                System.out.println(rating);
            }
        }
    }

}