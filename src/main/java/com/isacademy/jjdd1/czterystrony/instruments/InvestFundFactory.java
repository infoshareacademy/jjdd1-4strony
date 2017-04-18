package com.isacademy.jjdd1.czterystrony.instruments;

import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDao;
import com.isacademy.jjdd1.czterystrony.dao.InvestFundsDaoTxt;
import com.isacademy.jjdd1.czterystrony.utilities.TextFileReader;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

public class InvestFundFactory {
    private static final int RECORDS_TO_SKIP = 1;

    public static InvestFund create(String dataFileNameWithExtension, String name) {

        String id = dataFileNameWithExtension.replaceFirst("(\\w+).*", "$1");
        String company = name.replaceAll(" .+$", "");
        InputStream stream = InvestFundsDaoTxt.class.getResourceAsStream("/data/stockexchange/investfunds/" + dataFileNameWithExtension);

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
//
//    private static List<Rating> getRatings(List<String> records) {
//        return records.stream()
//                .skip(RECORDS_TO_SKIP)
//                .map(RatingFactory::create)
//                .sorted()
//                .collect(Collectors.toList());
//    }
}