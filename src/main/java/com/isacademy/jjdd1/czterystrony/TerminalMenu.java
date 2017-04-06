package com.isacademy.jjdd1.czterystrony;

public class TerminalMenu {

    public static void main(String[] args) {
        InvestFundDAO investFundDAO = new InvestFundDAO();

//        Set<Map.Entry<String, InvestFund>> investFundsSet = investFundDAO.getAllInvestFunds().entrySet();
//
//        for (Map.Entry<String, InvestFund> investFund : investFundsSet) {
//            InvestFund investFund1 = investFund.getValue();
//        }
//
//        for (String investFundName : investFundDAO.getAllInvestFunds().keySet()) {
//            System.out.println(investFundName);
//        }

        findExtrema(investFundDAO.getInvestFund("AIP003"));
    }

    public static void findExtrema(InvestFund investFund) {
        GlobalExtremaFinder globalExtremaFinder = new GlobalExtremaFinder(investFund);

        System.out.println("Extrema lokalne:\n\nMaksima lokalne:");
        for (Rating rating : globalExtremaFinder.getMaximumExtrema()) {
            System.out.println(rating);
        }

        System.out.println("\nMinima lokalne:");
        for (Rating rating : globalExtremaFinder.getMinimumExtrema()) {
            System.out.println(rating);
        }

    }
}