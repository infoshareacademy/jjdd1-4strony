package com.isacademy.jjdd1.czterystrony;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class InvestFundRepository implements StockExchangeRepository<InvestFund> {
    public static final String DATA_DIRECTORY = "./src/main/resources/data/stockexchange/investfunds";
    private Map<String, InvestFund> investFunds = new TreeMap<>();

    public InvestFundRepository() {
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

    @Override
    public void add(InvestFund investFund) {
        investFunds.put(investFund.getName(), investFund);
    }

    @Override
    public void delete(InvestFund instrument) {
        investFunds.remove(instrument.getName());
    }

    @Override
    public InvestFund get(String investFundName) {
        return investFunds.get(investFundName);
    }

    @Override
    public List<InvestFund> getAll() {
        return new ArrayList<>(investFunds.values());
    }

    public List<InvestFund> getAllByPriority() {

    }
}
