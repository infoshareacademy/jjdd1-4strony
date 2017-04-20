package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class MenuOfFunds {


    private final static Logger LOGGER = LoggerFactory.getLogger(MenuOfFunds.class);
    private final int PROMOTED_VALUE = 99;
    private final String FUND_TO_PROMOTE = "AVIVA Obligacji";

    public MenuOfFunds () {
        InvestFundsDaoTxt investFundDao = new InvestFundsDaoTxt();
        List<InvestFund> allByName = investFundDao.getAllByName();


        System.out.println("Lista funduszy: ");
        for (InvestFund investFund : allByName) {
            if (investFund.getName().equals(FUND_TO_PROMOTE)) {
                investFund.promote(PROMOTED_VALUE);
                LOGGER.trace("Prmoted fund "+ FUND_TO_PROMOTE + " is set with priority: " + PROMOTED_VALUE);
            }
        }
        if (investFundDao.getAllByName().size() > 0) {
            LOGGER.trace("Succesfully loaded " + +investFundDao.getAllByName().size() + " funds.");
        } else {
            LOGGER.error("There is no funds available.");
        }
        for (InvestFund investFund : allByName.stream()
                .sorted(Comparator.comparing(InvestFund::getPriority))
                .collect(Collectors.toList())) {
            System.out.println(investFund.getName());
        }
        System.out.println("Wpisz nazwę wybranego funduszu, aby przejść dalej:");
        Scanner choice = new Scanner(System.in);
        String fund = choice.nextLine();
        InvestFund investFund = investFundDao.get(fund);
        LOGGER.debug("Chosen fund: " + investFund.getName());
        MenuOfExtreme menuExtreme = new MenuOfExtreme(investFund);
    }
}