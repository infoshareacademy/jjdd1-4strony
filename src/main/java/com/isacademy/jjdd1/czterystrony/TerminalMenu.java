package com.isacademy.jjdd1.czterystrony;

import java.util.Map;
import java.util.Set;

public class TerminalMenu {

    public static void main(String[] args) {
        InvestFundDAO investFundDAO = new InvestFundDAO();

        Set<Map.Entry<String, InvestFund>> investFundsSet = investFundDAO.getAllInvestFunds().entrySet();

        for (Map.Entry<String, InvestFund> investFund : investFundsSet) {
            InvestFund investFund1 = investFund.getValue();
        }

        for (String investFundName : investFundDAO.getAllInvestFunds().keySet()) {
            System.out.println(investFundName);
        }

        //LocalExtremaFinder as = new LocalExtremaFinder();
    }
}