package com.isacademy.jjdd1.czterystrony.instruments;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RatingFactory {
    private static final int DATE_COLUMN_INDEX = 1;
    private static final int CLOSE_COLUMN_INDEX = 5;

    public static Rating create(String record) {
        String[] splitByComaRecord = record.split(",");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(splitByComaRecord[DATE_COLUMN_INDEX], dateTimeFormatter);
        BigDecimal closeValue = new BigDecimal(Double.parseDouble(splitByComaRecord[CLOSE_COLUMN_INDEX])).setScale(2, RoundingMode.HALF_UP);
        return new Rating(date, closeValue);
    }
}