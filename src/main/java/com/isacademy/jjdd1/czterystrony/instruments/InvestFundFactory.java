package com.isacademy.jjdd1.czterystrony.instruments;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.utilities.TextFileReader;
import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class InvestFundFactory {
    public static InvestFund create(String ratingsDataFileName, String investFundName) {
        String company = investFundName.replaceAll(" .+$", "");
        File investFundDataFile = new File(InvestFundsDao.INVEST_FUNDS_DATA_FOLDER_DIRECTORY + "/" + ratingsDataFileName);
        TextFileReader textFileReader = new TextFileReader(investFundDataFile);
        List<String> records = textFileReader.getContent();
        List<Rating> ratings = getRatings(records);

        return new InvestFund.Builder()
                .withId(ratingsDataFileName)
                .withName(investFundName)
                .withCompany(company)
                .withRatings(ratings)
                .build();
    }

    private static List<Rating> getRatings(List<String> records) {
        return records.stream()
                .skip(1)
                .map(RatingFactory::create)
                .sorted()
                .collect(Collectors.toList());
    }
}