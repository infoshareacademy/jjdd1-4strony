package com.isacademy.jjdd1.czterystrony.dao;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFundFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InvestFundsDaoTxt implements InvestFundsDao {
    private final String INVEST_FUNDS_LIST_FILE = "omegafun.lst";
    public static final String INVEST_FUNDS_DATA_FOLDER_DIRECTORY = "investfunds/";
    public static final String RATINGS_DATA_FILE_EXTENSION = ".txt";
    private final String PROMOTED_INVEST_FUNDS_FILE = "promoted-invest-funds.txt";
    private final int BEGIN_OF_ID_IN_LST = 33;
    private final int END_OF_ID_IN_LST = 39;
    private final int BEGIN_OF_INVEST_FUND_NAME_IN_LST = 51;
    private List<InvestFund> investFunds;
    private Map<String, Integer> promotedInvestFundIdToPriority;

    public InvestFundsDaoTxt() {
        this.promotedInvestFundIdToPriority = promotedInvestFundIdToPriority();
        this.investFunds = loadInvestFunds();
    }

    @Override
    public InvestFund get(String id) {
        return investFunds.stream()
                .filter(s -> s.getId().equals(id))
                .reduce((a, b) -> {
                    throw new IllegalStateException("Found more than 1 Invest Fund: " + a + ", " + b);
                })
                .get();
    }

    @Override
    public List<InvestFund> getAllByName() {
        return investFunds.stream()
                .sorted(Comparator.comparing(InvestFund::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvestFund> getAllByPriority() {
        return investFunds.stream()
                .sorted(Comparator.comparing(InvestFund::getPriority).thenComparing(InvestFund::getName))
                .collect(Collectors.toList());
    }

    private Map<String, Integer> promotedInvestFundIdToPriority() {
        InputStream stream = InvestFundsDaoTxt.class.getResourceAsStream(PROMOTED_INVEST_FUNDS_FILE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        return bufferedReader.lines()
                .filter(s -> !s.isEmpty())
                .map(s -> s.split(","))
                .collect(Collectors.toMap(s -> s[0], s -> Integer.parseInt(s[1])));
    }

    private List<InvestFund> loadInvestFunds() {
        Map<String, String> investFundIdToName = investFundIdToName();
        return investFundIdToName.entrySet().stream()
                .map(s -> InvestFundFactory.create(s.getKey(), s.getValue()))
                .map(s -> checkPromotionAndPromote(s))
                .collect(Collectors.toList());
    }

    private Map<String, String> investFundIdToName() {
        InputStream stream = InvestFundsDaoTxt.class.getResourceAsStream(INVEST_FUNDS_LIST_FILE);
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));
        return bufferedReader.lines()
                .filter(s -> containsDataFileExtension(s))
                .filter(s -> ratingsDataFileExistsFor(findIdInRecord(s)))
                .collect(Collectors.toMap(s -> findIdInRecord(s), s -> findNameInRecord(s)));
    }

    private Boolean containsDataFileExtension(String record) {
        return record.matches("(.*)" + RATINGS_DATA_FILE_EXTENSION + "(.*)");
    }

    private Boolean ratingsDataFileExistsFor(String investFundID) {
        InputStream stream = InvestFundsDaoTxt.class.getResourceAsStream(INVEST_FUNDS_DATA_FOLDER_DIRECTORY + investFundID + RATINGS_DATA_FILE_EXTENSION);
        try {
            new BufferedReader(new InputStreamReader(stream));
            return true;
        } catch (NullPointerException e) {
            return false;
        }
    }

    private String findIdInRecord(String record) {
        return record.substring(BEGIN_OF_ID_IN_LST, END_OF_ID_IN_LST);
    }

    private String findNameInRecord(String record) {
        return record.substring(BEGIN_OF_INVEST_FUND_NAME_IN_LST).trim();
    }

    private InvestFund checkPromotionAndPromote(InvestFund investFund) {
        String id = investFund.getId();
        if (promotedInvestFundIdToPriority.containsKey(id)) {
            int priority = promotedInvestFundIdToPriority.get(id);
            investFund.promote(priority);
        }
        return investFund;
    }
}