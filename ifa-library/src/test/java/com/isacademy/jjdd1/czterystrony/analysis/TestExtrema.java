package com.isacademy.jjdd1.czterystrony.analysis;

import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestExtrema {
    public static final int START_ANGLE = 0;
    public static final int END_ANGLE = 360;
    public static final BigDecimal OFFSET = BigDecimal.valueOf(5D);
    public static final BigDecimal SIN_MAX = BigDecimal.valueOf(1D);
    public static final BigDecimal SIN_MIN = BigDecimal.valueOf(-1D);

    public static List<Rating> sinRatings() {
        return IntStream.range(START_ANGLE, END_ANGLE)
                .mapToObj(TestExtrema::mapToRating)
                .collect(Collectors.toList());
    }

    private static Rating mapToRating(int value) {
        LocalDate date = LocalDate.now().plusDays(value);
        BigDecimal closeValue = OFFSET.add(BigDecimal.valueOf(Math.sin(Math.toRadians(value))));
        return new Rating(date, closeValue);
    }
}
