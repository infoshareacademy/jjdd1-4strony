package com.isacademy.jjdd1.czterystrony.analysis;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public abstract class MovingAverage {
    final int DIGITS_AFTER_COMMA = 2;
    List<BigDecimal> window = new LinkedList<>();
    int period;
    BigDecimal sum = BigDecimal.ZERO;

    MovingAverage(int period) {
        if (period <= 0) {
            throw new IllegalArgumentException("Period must be a positive integer");
        }
        this.period = period;
    }

    public abstract void add(BigDecimal value);

    public abstract BigDecimal getAverage();

    abstract BigDecimal getDivisor();
}