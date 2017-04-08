package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RatingFactory {
    public static Rating create(String stringRow) throws ParseException {
        String[] items = stringRow.split(",");
        Date date = new SimpleDateFormat("yyyyMMdd").parse(items[1]);
        BigDecimal closeValue = new BigDecimal(Double.parseDouble(items[5])).setScale(2, RoundingMode.HALF_UP);
        return new Rating(date, closeValue);
    }
}
