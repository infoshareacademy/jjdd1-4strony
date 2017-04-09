package com.isacademy.jjdd1.czterystrony;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class InvestFundFactory {
    public static InvestFund create(TextFileReader textFileReader) throws IOException, ParseException{
        String investFundName = textFileReader.getFileNameWithoutExtension();
        String companyName = investFundName.substring(0,2);
        List<String> records = textFileReader.getContentList();

        InvestFund investFund = new InvestFund(investFundName, companyName);

        for (String record : records) {
            Rating rating = RatingFactory.create(record);
            investFund.addRating(rating);
        }
        return investFund;
    }
}