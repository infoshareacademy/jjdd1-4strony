package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;

public class LocalExtremaFinderConfigurator {
    private int backwardDaysSensitivity;
    private int forwardDaysSensitivity;
    private BigDecimal maximumExistenceSensitivity;
    private BigDecimal minimumExistenceSensitivity;

    public LocalExtremaFinderConfigurator(int backwardRatingsSensitivity, int forwardRatingsSensitivity, BigDecimal lowerCloseValueSensitivity, BigDecimal upperCloseValueSensitivity) {
        this.backwardDaysSensitivity = backwardRatingsSensitivity;
        this.forwardDaysSensitivity = forwardRatingsSensitivity;
        this.maximumExistenceSensitivity = lowerCloseValueSensitivity;
        this.minimumExistenceSensitivity = upperCloseValueSensitivity;
    }

    public int getBackwardRatingsSensitivity() {
        return backwardDaysSensitivity;
    }

    public int getForwardRatingsSensitivity() {
        return forwardDaysSensitivity;
    }

    public BigDecimal getMaximumExistenceSensitivity() {
        return maximumExistenceSensitivity;
    }

    public BigDecimal getMinimumExistenceSensitivity() {
        return minimumExistenceSensitivity;
    }
}