package com.isacademy.jjdd1.czterystrony;

import java.util.List;

public class TerminalMenu {

    public static void main(String[] args) {
        InvestFundDAO investFundDAO = new InvestFundDAO();

        findExtrema(investFundDAO.getInvestFund("AIG006"));
    }

    public static void findExtrema(InvestFund investFund) {
        ExtremaFinder extremaFinder = new GlobalExtremaFinder(investFund);
        List<Rating> maximumExtremaRatings = extremaFinder.getMaximumExtremaRatings();
        System.out.println("Local maximum extrema (found " + maximumExtremaRatings.size() + " ratings):");
        for (Rating rating : maximumExtremaRatings) {
            System.out.println(rating);
        }
    }
}