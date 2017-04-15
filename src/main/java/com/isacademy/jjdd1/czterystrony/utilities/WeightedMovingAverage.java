package com.isacademy.jjdd1.czterystrony.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class WeightedMovingAverage extends MovingAverage {

    public WeightedMovingAverage(int period) {
        super(period);
    }

    public void add(BigDecimal value) {
        if (window.size() > period) window.remove(0);

        window.add(value);
        IntStream.range(0, window.size()).forEach(t -> sum.add(BigDecimal.valueOf(t+1).multiply(window.get(t))));
    }

    public BigDecimal getAverage() {
        if (window.isEmpty()) return BigDecimal.ZERO;
        BigDecimal divisor = BigDecimal.valueOf(IntStream.range(1, window.size()+1).sum());
        return sum.divide(divisor, 2, RoundingMode.HALF_UP);
    }
}
