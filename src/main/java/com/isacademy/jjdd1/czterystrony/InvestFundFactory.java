package com.isacademy.jjdd1.czterystrony;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class InvestFundFactory {
    public static InvestFund getInvestFund(String id, String name) throws IOException, ParseException {
        TextFileReader textFileReader = new TextFileReader()
        String id = textFileReader.getFileNameWithoutExtension();

        String company = investFundName.substring(0, 2);
        List<String> records = textFileReader.getContentList();
        Set<Rating> ratings = getRatings(records);

        InvestFund investFund = new InvestFund.Builder()
                .withId(id)
                .withName(name)
                .withCompany(company)
                .withRatings(ratings)
                .build();

        return investFund;
    }

    private static Set<Rating> getRatings(List<String> records) throws ParseException{
        Set<Rating> ratings = new TreeSet<>();
        for (String record : records) {
            Rating rating = RatingFactory.create(record);
            ratings.add(rating);
        }
        return ratings;
    }
}