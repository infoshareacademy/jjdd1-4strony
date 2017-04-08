package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RatingFactory {
    public static Rating create(String record) throws ParseException {
        String[] splittedByComaRecord = record.split(",");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(splittedByComaRecord[1], dateTimeFormatter);
        BigDecimal closeValue = new BigDecimal(Double.parseDouble(splittedByComaRecord[5])).setScale(2, RoundingMode.HALF_UP);
        return new Rating(date, closeValue);
    }
}
