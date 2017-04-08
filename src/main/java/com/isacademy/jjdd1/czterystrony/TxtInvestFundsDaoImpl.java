package com.isacademy.jjdd1.czterystrony;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class TxtInvestFundsDaoImpl implements InvestFundsDao {
    private Map<String, InvestFund> investFunds = new TreeMap<>();

    public TxtInvestFundsDaoImpl() {
        File investFundsDataDirectory = new File(DATA_DIRECTORY);
        for (File file : investFundsDataDirectory.listFiles()) {
            parse(file);
        }
        String i = DATA_DIRECTORY;
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
    public InvestFund get(String investFundName) {
        return investFunds.get(investFundName);
    }

    @Override
    public List<InvestFund> getAllByName() {
        List<InvestFund> investFundsByName = getAllByPriority();
        Collections.sort(investFundsByName, Comparator.comparing(InvestFund::getName));
        return investFundsByName;
    }

    @Override
    public List<InvestFund> getAllByPriority() {
        return new ArrayList<>(investFunds.values());
    }
}
