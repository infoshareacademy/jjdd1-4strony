package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;

import java.util.*;

public class MenuOfFunds {
    public int menuOfFunds;

    {
        InvestFundsDaoTxt investFundDao = new InvestFundsDaoTxt();

        System.out.println("Lista funduszy: ");
        for (InvestFund investFund : investFundDao.getAllByName()) {
            System.out.println(investFund.getName());
        }

        System.out.println("Wpisz nazwę wybranego funduszu, aby przejść dalej:");
        Scanner choice = new Scanner(System.in);
        String fund = choice.nextLine();//fundusz wybrany przez użytkownika
        InvestFund investFund = investFundDao.get(fund);
        MenuOfExtreme menuExtreme = new MenuOfExtreme(investFund);
//        investFund.getAllRatings();


//        for (String fundChoice : allInvestFunds.keySet()) {
//            System.out.println();
//        }
    }
}