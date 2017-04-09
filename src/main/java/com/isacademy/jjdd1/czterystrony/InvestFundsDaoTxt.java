package com.isacademy.jjdd1.czterystrony;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.*;

public class InvestFundsDaoTxt implements InvestFundsDao {
    private Map<String, InvestFund> investFunds = new TreeMap<>();

    public InvestFundsDaoTxt() {
        final File[] textFiles = new File(DATA_DIRECTORY).listFiles();
        if (textFiles != null) collectInvestFunds(textFiles);
    }

    private void collectInvestFunds(File[] textFiles) {
        for (File textFile : textFiles) {
            TextFileReader textFileReader = new TextFileReader(textFile);
            try {
                InvestFund investFund = InvestFundFactory.create(textFileReader);
                if (investFund.getName().equals("AIP021")) {
                    investFund.promote(100);
                }
                investFunds.put(investFund.getName(), investFund);
            } catch (ParseException | IOException e) {
                e.printStackTrace();
            }
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
        return new ArrayList<>(investFunds.values());
    }

    @Override
    public List<InvestFund> getAllByPriority() {
        List<InvestFund> investFundsByPriority = new ArrayList<>(investFunds.values());
        investFundsByPriority.sort(Comparator.comparing(InvestFund::getPriority));
        return investFundsByPriority;
    }
}