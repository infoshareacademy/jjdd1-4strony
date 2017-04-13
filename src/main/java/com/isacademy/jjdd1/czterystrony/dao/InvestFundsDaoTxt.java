package com.isacademy.jjdd1.czterystrony.dao;

import com.isacademy.jjdd1.czterystrony.instruments.InvestFund;
import com.isacademy.jjdd1.czterystrony.instruments.InvestFundFactory;
import com.isacademy.jjdd1.czterystrony.utilities.TextFileReader;
import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InvestFundsDaoTxt implements InvestFundsDao {
    private static final int BEGIN_RATINGS_DATA_FILE = 33;
    private static final int END_RATINGS_DATA_FILE = 43;
    private static final int BEGIN_OF_INVEST_FUND_NAME_IN_LST = 51;

    @Override
    public InvestFund get(String investFundName) {
        Map<String, String> ratingsDataFileToName = ratingsDataFileToInvestFundName();
        return ratingsDataFileToName.entrySet().stream()
                .filter(s -> s.getValue().matches(investFundName))
                .map(s -> InvestFundFactory.create(s.getKey(), investFundName))
                .findFirst()
                .get();
    }

    @Override
    public List<InvestFund> getAllByName() {
        Map<String, String> ratingsDataFileToName = ratingsDataFileToInvestFundName();
        return ratingsDataFileToName.entrySet().stream()
                .map(s -> InvestFundFactory.create(s.getKey(), s.getValue()))
                .sorted(Comparator.comparing(InvestFund::getName))
                .collect(Collectors.toList());
    }

    @Override
    public List<InvestFund> getAllByPriority() {
        Map<String, String> ratingsDataFileToName = ratingsDataFileToInvestFundName();

        return ratingsDataFileToName.entrySet().stream()
                .map(s -> InvestFundFactory.create(s.getKey(), s.getValue()))
                .sorted(Comparator.comparing(InvestFund::getPriority))
                .collect(Collectors.toList());
    }

    private Map<String, String> ratingsDataFileToInvestFundName() {
        File fileWithInvestFundsList = new File(INVEST_FUNDS_LIST_DIRECTORY);
        TextFileReader textFileReader = new TextFileReader(fileWithInvestFundsList);

        return textFileReader.getContent().stream()
                .filter(s -> s.matches("(.*)txt(.*)"))
                .filter(s -> ratingsDataFileExists(s.substring(BEGIN_RATINGS_DATA_FILE, END_RATINGS_DATA_FILE)))
                .collect(Collectors.toMap(s -> s.substring(BEGIN_RATINGS_DATA_FILE, END_RATINGS_DATA_FILE), s -> s.substring(BEGIN_OF_INVEST_FUND_NAME_IN_LST).trim()));
    }

    private Boolean ratingsDataFileExists(String ratingsDataFileName) {
        File investFundDataFile = new File(INVEST_FUNDS_DATA_FOLDER_DIRECTORY + "/" + ratingsDataFileName);
        return investFundDataFile.getAbsoluteFile().exists();
    }
}