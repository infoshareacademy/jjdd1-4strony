package com.isacademy.jjdd1.czterystrony;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class InvestFundParser {
    public static InvestFund parse(String dataFileName) throws IOException, ParseException{
        InvestFund investFund = new InvestFund(dataFileName);
        List<String> content = new TxtFileReader(InvestFundDAO.DATA_DIRECTORY, dataFileName).getContentList();

        for (String row : content) {
            investFund.addRating(RatingParser.parse(row));
        }

        return investFund;
    }
}
