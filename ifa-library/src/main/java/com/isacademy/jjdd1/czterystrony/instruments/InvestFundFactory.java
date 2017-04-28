package com.isacademy.jjdd1.czterystrony.instruments;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.stream.Collectors;

public class InvestFundFactory {
    private static final int RECORDS_TO_SKIP = 1;

    public static InvestFund create(String id, String name) {
        String company = name.replaceAll(" .+$", "");
        InputStream stream = InvestFundsDaoTxt.class.getResourceAsStream(
                InvestFundsDaoTxt.INVEST_FUNDS_DATA_FOLDER_DIRECTORY + id +
                        InvestFundsDaoTxt.RATINGS_DATA_FILE_EXTENSION);

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(stream));

        List<Rating> ratings = bufferedReader.lines()
                .skip(RECORDS_TO_SKIP)
                .map(RatingFactory::create)
                .sorted()
                .collect(Collectors.toList());

        return new InvestFund.Builder()
                .withId(id)
                .withName(name)
                .withCompany(company)
                .withRatings(ratings)
                .build();
    }
}