package com.isacademy.jjdd1.czterystrony;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class InvestFundsDaoTxt implements InvestFundsDao {

    @Override
    public InvestFund get(String investFundId) {
        List<String> records = getListOfInvestFunds();
        InvestFund investFund = new InvestFund.Builder().build();

        for (String record : records) {
            if (record.substring(BEGIN_OF_INVEST_FUND_ID, END_OF_INVEST_FUND_ID).equals(investFundId)) {
                String investFundName = record.substring(BEGIN_OF_INVEST_FUND_NAME).trim();

                try {
                    investFund = InvestFundFactory.getInvestFund(investFundId, investFundName);
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                }
                break;
            }
        }
        return investFund;
    }

    @Override
    public List<InvestFund> getAllByName() {
        List<String> records = getListOfInvestFunds();
        List<InvestFund> investFunds = new ArrayList<>();

        for (String record : records) {
            if (record.matches("(.*)txt(.*)")) {
                String investFundId = record.substring(BEGIN_OF_INVEST_FUND_ID, END_OF_INVEST_FUND_ID);
                String investFundName = record.substring(BEGIN_OF_INVEST_FUND_NAME).trim();

                try {
                    InvestFund investFund = InvestFundFactory.getInvestFund(investFundId, investFundName);
                    investFunds.add(investFund);
                } catch (ParseException | IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return investFunds;
    }

    @Override
    public List<InvestFund> getAllByPriority() {
        List<InvestFund> investFundsByPriority = getAllByName();
        investFundsByPriority.sort(Comparator.comparing(InvestFund::getPriority));
        return investFundsByPriority;
    }

    private List<String> getListOfInvestFunds() {
        File investFundsList = new File(INVEST_FUNDS_LIST_DIRECTORY);
        TextFileReader textFileReader = new TextFileReader(investFundsList);
        return textFileReader.getContentList();
    }
}