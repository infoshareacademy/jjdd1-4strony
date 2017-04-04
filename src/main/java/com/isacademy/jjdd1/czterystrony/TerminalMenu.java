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
                Rating rating = new Rating(LocalDate.parse(items[1], format), Double.parseDouble(items[5]));
                investFund.addRating(rating);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (LocalDate localDate : investFund.getRatings().keySet()) {
            System.out.println(investFund.getRatings().get(localDate));
        }
    }
}