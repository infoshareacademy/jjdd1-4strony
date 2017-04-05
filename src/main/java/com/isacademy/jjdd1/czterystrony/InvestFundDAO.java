package com.isacademy.jjdd1.czterystrony;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

public class InvestFundDAO {
    public static final String DATA_DIRECTORY = "./src/main/resources/data/omegafun";
    private Map<String, InvestFund> investFunds = new HashMap<>();

    public InvestFundDAO() {
        File investFundsDataDirectory = new File(DATA_DIRECTORY);

        for (File file : investFundsDataDirectory.listFiles()) {
            String dataFileName = file.getName();
            int dotPosition = dataFileName.lastIndexOf(".");
            if (dotPosition > 0) {
                dataFileName = dataFileName.substring(0, dotPosition);
            }
            try {
                investFunds.put(dataFileName, InvestFundParser.parse(dataFileName));
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public Map<String, InvestFund> getAllInvestFunds() {
        return investFunds;
    }

    public InvestFund getInvestFund(String investFundName) {
        return investFunds.get(investFundName);
    }

}