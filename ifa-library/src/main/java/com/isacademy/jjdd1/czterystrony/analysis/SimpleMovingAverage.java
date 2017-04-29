package com.isacademy.jjdd1.czterystrony.analysis;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class SimpleMovingAverage extends MovingAverage {

    public SimpleMovingAverage(int period) {
        super(period);
    }

    @Override
    public void add(BigDecimal value) {
        window.add(value);

        if (window.size() > period) {
            sum = sum.subtract(window.remove(0));
        }

        sum = sum.add(value);
    }

    @Override
    public BigDecimal getAverage() {
        if (window.isEmpty()) return BigDecimal.ZERO;
        return sum.divide(getDivisor(), DIGITS_AFTER_COMMA, RoundingMode.HALF_UP);
    }

    @Override
    BigDecimal getDivisor() {
        return BigDecimal.valueOf(window.size());
    }
}