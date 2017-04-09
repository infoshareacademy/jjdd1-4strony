package com.isacademy.jjdd1.czterystrony;

import java.util.*;
import java.util.function.Function;

import static com.isacademy.jjdd1.czterystrony.ChoosingDateMenu.choosingDateMenu;


public class MenuOfFunds {

    public int menuOfFunds; {


        InvestFundDAO investFundDAO = new InvestFundDAO();
        System.out.println("Lista funduszy: ");

        Map<String, InvestFund> allInvestFunds = investFundDAO.getAllInvestFunds();
        for (String investFundName : allInvestFunds.keySet()) {
            System.out.println(investFundName);

        }
        System.out.println("Wpisz nazwę wybranego funduszu, aby przejść dalej:");

        Scanner choice = new Scanner(System.in);
        String fund = choice.nextLine().toUpperCase();//fundusz wybrany przez użytkownika
        InvestFund investFund = allInvestFunds.get(fund);
        MenuOfExtreme menuExtreme = new MenuOfExtreme(investFund);
//        investFund.getAllRatings();
//
//
//        for (String fundChoice : allInvestFunds.keySet()) {
//            System.out.println();
//        }
    }
}