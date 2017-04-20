package com.isacademy.jjdd1.czterystrony.ui;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class MenuOfFunds {

    private final static Logger LOGGER = LoggerFactory.getLogger(MenuOfFunds.class);
    public int menuOfFunds;

    {
        InvestFundsDaoTxt investFundDao = new InvestFundsDaoTxt();
        List<InvestFund> allByName = investFundDao.getAllByName();
        int promoteValue = 99;
        String hardCodedFundToPromote = "AVIVA Obligacji";


        System.out.println("Lista funduszy: ");
        for (InvestFund investFund : allByName) {
            if (investFund.getName().equals(hardCodedFundToPromote)) {
                investFund.promote(promoteValue);
                LOGGER.trace("Prmoted fund "+ hardCodedFundToPromote + " is set with priority: " + promoteValue);
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