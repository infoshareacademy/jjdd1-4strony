package com.isacademy.jjdd1.czterystrony;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.Map;
import java.util.TreeMap;

public class InvestFundDAO implements StockExchangeDAO<InvestFund> {
    public static final String DATA_DIRECTORY = "./src/main/resources/data/stockexchange/investfunds";
    private Map<String, InvestFund> investFunds = new TreeMap<>();

    public InvestFundDAO() {
        File investFundsDataDirectory = new File(DATA_DIRECTORY);
        Instrument instrument = new InvestFund().name;
        for (File file : investFundsDataDirectory.listFiles()) {
            parse(file);
        }
    }

    private void parse(File file){
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

    public boolean add(InvestFund investFund) {
        return false;
    }
    public Map<String, InvestFund> getAll() {
        return investFunds;
    }

    public InvestFund get(String investFundName) {
        return investFunds.get(investFundName);
    }
}
