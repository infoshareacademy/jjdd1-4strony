package com.isacademy.jjdd1.czterystrony;

public class TerminalMenu {

    public static void main(String[] args) {
        InvestFundDAO investFundDAO = new InvestFundDAO();

        for (String investFundName : investFundDAO.getAllInvestFunds().keySet()) {
            System.out.println(investFundName);
        }

        MainMenu menu = new MainMenu();
    }
}