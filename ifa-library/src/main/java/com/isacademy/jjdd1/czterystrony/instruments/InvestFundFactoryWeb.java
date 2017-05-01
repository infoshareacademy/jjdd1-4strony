package com.isacademy.jjdd1.czterystrony.instruments;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class InvestFundFactoryWeb {
    private static final int RECORDS_TO_SKIP = 1;

    public static InvestFund create(String id, String name) {
        String company = name.replaceAll(" .+$", "");

        List<Rating> ratings = new ArrayList<>();

        try {
            URL url = new URL(InvestFundsDaoWeb.INVEST_FUNDS_RATINGS_ZIP_URL);
            URLConnection urlConnection = url.openConnection();
            InputStream stream = urlConnection.getInputStream();
            ZipInputStream zipInputStream = new ZipInputStream(stream);

            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (entry.getName().equals(id + InvestFundsDaoWeb.RATINGS_DATA_FILE_EXTENSION)) {
                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(zipInputStream));
                    ratings = bufferedReader.lines()
                            .skip(RECORDS_TO_SKIP)
                            .map(RatingFactory::create)
                            .sorted()
                            .collect(Collectors.toList());
                }
            }

        } catch (IOException e) {

        }

        System.out.println(id);

        return new InvestFund.Builder()
                .withId(id)
                .withName(name)
                .withCompany(company)
                .withRatings(ratings)
                .build();
    }
}
