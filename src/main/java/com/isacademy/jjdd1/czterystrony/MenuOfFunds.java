package com.isacademy.jjdd1.czterystrony;

import java.util.*;

public class MenuOfFunds {
    public int menuOfFunds() {
        InvestFundsDao investFundsDao = new InvestFundsDaoTxt();
        System.out.println("Wybierz interesujący Cię fundusz: ");

        for (InvestFund investFund : investFundsDao.getAllByName()) {
            System.out.println(investFund.getName());
        }

        Scanner choice = new Scanner(System.in);
        int i = choice.nextInt();
        return i;
    }
}