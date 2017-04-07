package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RatingParser {
    public static Rating parse (String stringRow) throws ParseException {
        String[] items = stringRow.split(",");
        Date date = new SimpleDateFormat("yyyyMMdd").parse(items[1]);
        BigDecimal closeValue = new BigDecimal(Double.parseDouble(items[5]));
        return new Rating(date, closeValue);
    }
}
