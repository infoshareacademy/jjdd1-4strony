package com.isacademy.jjdd1.czterystrony.utilities;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleMovingAverage extends MovingAverage {

    public SimpleMovingAverage(int period) {
        super(period);
    }

    public void add(BigDecimal value) {
        window.add(value);

        if (window.size() > period) {
            sum = sum.subtract(window.remove(0));
        }

        sum = sum.add(value);
    }

    public BigDecimal getAverage() {
        if (window.isEmpty()) return BigDecimal.ZERO;
        BigDecimal divisor = BigDecimal.valueOf(window.size());
        return sum.divide(divisor, 2, RoundingMode.HALF_UP);
    }
}