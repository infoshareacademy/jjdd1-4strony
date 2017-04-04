package com.isacademy.jjdd1.czterystrony;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TerminalMenu {

    public static void main(String[] args) {
        FileReader newTestFileReader = new FileReader("AGI001.txt");
        InvestFund investFund = new InvestFund("AGI001");

        try {
            List<String> content = newTestFileReader.getContentList();

            for (String row : content) {
                String[] items = row.split(",");
                DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyyMMdd");
                LocalDate date = LocalDate.parse(items[1], format);
                Double closeValue = Double.parseDouble(items[5]);
                Rating rating = new Rating(date, closeValue);
                investFund.addRating(rating);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Rating rating : investFund.getRatings()) {
            System.out.println(rating);
        }
    }
}