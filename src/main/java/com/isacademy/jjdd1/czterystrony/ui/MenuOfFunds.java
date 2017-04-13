package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;

public class MenuOfFunds {

    private final static Logger LOGGER = LoggerFactory.getLogger(MenuOfFunds.class);
    public int menuOfFunds; {
        InvestFundsDao investFundDao = new InvestFundsDaoTxt();

        System.out.println("Lista funduszy: ");
        for (InvestFund investFund : investFundDao.getAllByName()) {
            System.out.println(investFund.getName());
        }

        System.out.println("Wpisz nazwę wybranego funduszu, aby przejść dalej:");
        Scanner choice = new Scanner(System.in);
        String fund = choice.nextLine();
        InvestFund investFund = investFundDao.get(fund);
        LOGGER.info("Chosen fund: " + investFund);
        MenuOfExtreme menuExtreme = new MenuOfExtreme(investFund);
//        investFund.getAllRatings();
//
//
//        for (String fundChoice : allInvestFunds.keySet()) {
//            System.out.println();
//        }
    }
}