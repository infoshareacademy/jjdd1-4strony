package com.isacademy.jjdd1.czterystrony.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class WeightedMovingAverage extends MovingAverage {

    public WeightedMovingAverage(int period) {
        super(period);
    }

    public void add(BigDecimal value) {
        window.add(value);

        if (window.size() > period) {
            window.remove(0);
        }

        sum = BigDecimal.ZERO;
        sum = IntStream.rangeClosed(1, window.size())
                .mapToObj(t -> BigDecimal.valueOf(t).multiply(window.get(t - 1)))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public BigDecimal getAverage() {
        if (window.isEmpty()) return BigDecimal.ZERO;
        BigDecimal divisor = BigDecimal.valueOf(IntStream.rangeClosed(1, window.size()).sum());
        return sum.divide(divisor, 2, RoundingMode.HALF_UP);
    }
}