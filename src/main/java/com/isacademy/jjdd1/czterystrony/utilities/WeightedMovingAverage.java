package com.isacademy.jjdd1.czterystrony.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class WeightedMovingAverage extends MovingAverage {

    public WeightedMovingAverage(int period) {
        super(period);
    }

    @Override
    public void add(BigDecimal value) {
        window.add(value);

        if (window.size() > period) {
            window.remove(0);
        }

        sum = BigDecimal.ZERO;
        sum = IntStream.rangeClosed(1, window.size())
                .mapToObj(t -> getSummand(t))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    private BigDecimal getSummand(int elementIndex) {
        return BigDecimal.valueOf(elementIndex).multiply(window.get(elementIndex - 1));
    }

    @Override
    public BigDecimal getAverage() {
        if (window.isEmpty()) return BigDecimal.ZERO;
        return sum.divide(getDivisor(), DIGITS_AFTER_COMMA, RoundingMode.HALF_UP);
    }

    @Override
    BigDecimal getDivisor() {
        return BigDecimal.valueOf(IntStream.rangeClosed(1, window.size()).sum());
    }
}