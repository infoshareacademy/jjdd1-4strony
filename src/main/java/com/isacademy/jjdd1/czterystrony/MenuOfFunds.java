package com.isacademy.jjdd1.czterystrony;

import java.util.*;

public class MenuOfFunds {
    public int menuOfFunds; {
        InvestFundsDao investFundDao = new InvestFundsDaoTxt();

        System.out.println("Lista funduszy: ");
        for (InvestFund investFund : investFundDao.getAllByName()) {
            System.out.println(investFund.getName());
        }

        System.out.println("Wpisz nazwę wybranego funduszu, aby przejść dalej:");
        Scanner choice = new Scanner(System.in);
        String fund = choice.nextLine().toUpperCase();//fundusz wybrany przez użytkownika
        InvestFund investFund = investFundDao.get(fund);
        MenuOfExtreme menuExtreme = new MenuOfExtreme(investFund);
//        investFund.getAllRatings();
//
//
//        for (String fundChoice : allInvestFunds.keySet()) {
//            System.out.println();
//        }
    }
}