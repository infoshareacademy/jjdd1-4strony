package com.isacademy.jjdd1.czterystrony.factories;

import com.isacademy.jjdd1.czterystrony.model.InvestFund;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class InvestFundFactory {
    private static final int ID_BEGIN = 33;
    private static final int ID_END = 39;
    private static final int FULL_NAME_BEGIN = 51;
    private static final int LAST_RATING_DATE_BEGIN = 0;
    private static final int LAST_RATING_DATE_END = 10;
    private static final String DATE_PATTERN = "yyyy-MM-dd";

    public static InvestFund create(String record) {
        return new InvestFund.Builder()
                .withId(parseIdFrom(record))
                .withFulltName(parseFullNameFrom(record))
                .withLastRatingDate(parseLastRatingDateFrom(record))
                .build();
    }

    private static String parseIdFrom(String record) {
        return record.substring(ID_BEGIN, ID_END);
    }

    private static String parseFullNameFrom(String record) {
        return record.substring(FULL_NAME_BEGIN).trim();
    }

    private static LocalDate parseLastRatingDateFrom(String record) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        return LocalDate.parse(
                record.substring(LAST_RATING_DATE_BEGIN, LAST_RATING_DATE_END),
                dateTimeFormatter);
    }
}
