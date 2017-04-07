package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;

public class ExtremaFinderConfigurator {
    private int backwardDaysSensitivity;
    private int forwardDaysSensitivity;
    private BigDecimal maximumExistenceSensitivity;
    private BigDecimal minimumExistenceSensitivity;

    public ExtremaFinderConfigurator(int backwardDaysSensitivity, int forwardDaysSensitivity, BigDecimal lowerCloseValueSensitivity, BigDecimal upperCloseValueSensitivity) {
        this.backwardDaysSensitivity = backwardDaysSensitivity;
        this.forwardDaysSensitivity = forwardDaysSensitivity;
        this.maximumExistenceSensitivity = lowerCloseValueSensitivity;
        this.minimumExistenceSensitivity = upperCloseValueSensitivity;
    }

    public int getBackwardDaysSensitivity() {
        return backwardDaysSensitivity;
    }

    public int getForwardDaysSensitivity() {
        return forwardDaysSensitivity;
    }

    public BigDecimal getMaximumExistenceSensitivity() {
        return maximumExistenceSensitivity;
    }

    public BigDecimal getMinimumExistenceSensitivity() {
        return minimumExistenceSensitivity;
    }
}
