package com.isacademy.jjdd1.czterystrony.analysis;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.stream.IntStream;

public class ExponentialMovingAverage extends MovingAverage {

    public ExponentialMovingAverage(int period) {
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

    private BigDecimal getSummand(int summandIndex) {
        return getWeight(summandIndex).multiply(window.get(window.size() - summandIndex));
    }

    private BigDecimal getWeight(int summandIndex) {
        BigDecimal powerBase = BigDecimal.valueOf(1D - (2D / (summandIndex + 1D)));
        return powerBase.pow(summandIndex - 1);
    }

    @Override
    public BigDecimal getAverage() {
        if (window.isEmpty()) return BigDecimal.ZERO;
        return sum.divide(getDivisor(), DIGITS_AFTER_COMMA, RoundingMode.HALF_UP);
    }

    @Override
    BigDecimal getDivisor() {
        return IntStream.rangeClosed(1, window.size())
                .mapToObj(t -> getWeight(t))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }
}