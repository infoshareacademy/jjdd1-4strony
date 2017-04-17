package com.isacademy.jjdd1.czterystrony.instruments;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.utilities.TextFileReader;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class InvestFundFactory {
    private static final int RECORDS_TO_SKIP = 1;

    public static InvestFund create(String dataFileNameWithExtension, String name) {
        String id = dataFileNameWithExtension.replaceFirst("(\\w+).*", "$1");
        String company = name.replaceAll(" .+$", "");
        File dataFile = new File(InvestFundsDao.INVEST_FUNDS_DATA_FOLDER_DIRECTORY + dataFileNameWithExtension);
        TextFileReader textFileReader = new TextFileReader(dataFile);
        List<String> records = textFileReader.getContent();
        List<Rating> ratings = getRatings(records);

        return new InvestFund.Builder()
                .withId(id)
                .withName(name)
                .withCompany(company)
                .withRatings(ratings)
                .build();
    }

    private static List<Rating> getRatings(List<String> records) {
        return records.stream()
                .skip(RECORDS_TO_SKIP)
                .map(RatingFactory::create)
                .sorted()
                .collect(Collectors.toList());
    }
}