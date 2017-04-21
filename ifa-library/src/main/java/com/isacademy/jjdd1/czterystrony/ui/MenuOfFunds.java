package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MenuOfFunds {
    public int menuOfFunds;

    {
        InvestFundsDaoTxt investFundDao = new InvestFundsDaoTxt();
        List<InvestFund> allByName = investFundDao.getAllByName();


        System.out.println("Lista funduszy: ");
        for (InvestFund investFund : allByName) {
            if (investFund.getName().equals("AVIVA Obligacji")) {
                investFund.promote(99);
            }
        }
        for (InvestFund investFund : allByName.stream()
                .sorted(Comparator.comparing(InvestFund::getPriority))
                .collect(Collectors.toList())) {
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