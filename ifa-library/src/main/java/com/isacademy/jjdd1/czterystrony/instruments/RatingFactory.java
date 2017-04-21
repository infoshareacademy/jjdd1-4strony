package com.isacademy.jjdd1.czterystrony.instruments;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class RatingFactory {
    private static final int DATE_COLUMN_INDEX = 1;
    private static final int CLOSE_COLUMN_INDEX = 5;
    private final static Logger LOGGER = LoggerFactory.getLogger(InvestFundFactory.class);
    public static Rating create(String record) {
        String[] splitByComaRecord = record.split(",");
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        LocalDate date = LocalDate.parse(splitByComaRecord[DATE_COLUMN_INDEX], dateTimeFormatter);
        BigDecimal closeValue = new BigDecimal(Double.parseDouble(splitByComaRecord[CLOSE_COLUMN_INDEX])).setScale(2, RoundingMode.HALF_UP);
        LOGGER.trace("Method create() is initialized with parameter: " + record.toString());
        return new Rating(date, closeValue);
    }
}