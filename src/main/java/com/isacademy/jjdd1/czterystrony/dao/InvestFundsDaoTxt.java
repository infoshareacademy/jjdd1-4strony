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
    private static final String RATINGS_DATA_FILE_EXTENSION = ".txt";
    private static final int BEGIN_OF_ID_IN_LST = 33;
    private static final int END_OF_ID_IN_LST = 39;
    private static final int BEGIN_OF_INVEST_FUND_NAME_IN_LST = 51;

    @Override
    public InvestFund get(String fullName) {
        Map<String, String> ratingsDataFileToName = ratingsDataFileToInvestFundName();
        return ratingsDataFileToName.entrySet().stream()
                .filter(s -> s.getValue().matches(fullName))
                .map(s -> InvestFundFactory.create(s.getKey(), fullName))
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
                .filter(s -> recordContainsDataFileExtension(s))
                .filter(s -> ratingsDataFileExistsFor(findIdInRecord(s)))
                .collect(Collectors.toMap(s -> joinIdWithDataFileExtension(s), s -> findNameInRecord(s)));
    }

    private Boolean recordContainsDataFileExtension(String record) {
        return record.matches("(.*)" + RATINGS_DATA_FILE_EXTENSION + "(.*)");
    }

    private Boolean ratingsDataFileExistsFor(String investFundID) {
        File investFundDataFile = new File(INVEST_FUNDS_DATA_FOLDER_DIRECTORY + "/" + investFundID + RATINGS_DATA_FILE_EXTENSION);
        return investFundDataFile.getAbsoluteFile().exists();
    }

    private String findIdInRecord(String record) {
        return record.substring(BEGIN_OF_ID_IN_LST, END_OF_ID_IN_LST);
    }

    private String joinIdWithDataFileExtension(String record) {
        return findIdInRecord(record) + RATINGS_DATA_FILE_EXTENSION;
    }

    private String findNameInRecord(String record) {
        return record.substring(BEGIN_OF_INVEST_FUND_NAME_IN_LST).trim();
    }
}