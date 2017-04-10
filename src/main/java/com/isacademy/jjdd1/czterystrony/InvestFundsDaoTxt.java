package com.isacademy.jjdd1.czterystrony;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class InvestFundsDaoTxt implements InvestFundsDao {

//    private Map<String, InvestFund> investFunds = new TreeMap<>();
//    public InvestFundsDaoTxt() {
//        File investFundsList = new File(INVEST_FUNDS_LIST_DIRECTORY);
//
//        File[] textFiles = new File(INVEST_FUNDS_DATA_FOLDER_DIRECTORY).listFiles();
//        if (textFiles != null) {
//            collectInvestFunds(textFiles);
//        }
//    }
//    private void collectInvestFunds(File[] textFiles) {
//        for (File textFile : textFiles) {
//            TextFileReader textFileReader = new TextFileReader(textFile);
//            try {
//                InvestFund investFund = InvestFundFactory.getInvestFund(textFileReader);
////                if (investFund.getName().equals("AIP021")) {
////                    investFund.promote(100);
////                }
//                investFunds.put(investFund.getName(), investFund);
//            } catch (ParseException | IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    @Override
    public InvestFund get(String investFundId) {
        File investFundsList = new File(INVEST_FUNDS_LIST_DIRECTORY);
        TextFileReader textFileReader = new TextFileReader(investFundsList);

        List<String> records = textFileReader.getContentList();

        for (String record : records) {
            if (record.substring(BEGIN_OF_INVEST_FUND_ID, END_OF_INVEST_FUND_ID).equals(investFundId)) {
                String investFundName = record.substring(BEGIN_OF_INVEST_FUND_NAME).trim()
                InvestFund investFund = InvestFundFactory.getInvestFund(investFundId, investFundName);
                break;
            }
        }

        return investFund;
    }
n
    @Override
    public List<InvestFund> getAllByName() {
        return new ArrayList<>(investFunds.values());
    }

    @Override
    public List<InvestFund> getAllByPriority() {
        List<InvestFund> investFundsByPriority = getAllByName();
        investFundsByPriority.sort(Comparator.comparing(InvestFund::getPriority));
        return investFundsByPriority;
    }
}