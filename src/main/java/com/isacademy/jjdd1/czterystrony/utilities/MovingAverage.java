package com.isacademy.jjdd1.czterystrony.utilities;

import java.math.BigDecimal;
import java.util.LinkedList;
import java.util.List;

public abstract class MovingAverage {
    protected List<BigDecimal> window = new LinkedList<>();
    protected int period;
    protected BigDecimal sum = BigDecimal.ZERO;

    public MovingAverage(int period) {
        if (period <= 0) throw new IllegalArgumentException("Period must be a positive integer");
        this.period = period;
    }

    public abstract void add(BigDecimal value);

    public abstract BigDecimal getAverage();
}