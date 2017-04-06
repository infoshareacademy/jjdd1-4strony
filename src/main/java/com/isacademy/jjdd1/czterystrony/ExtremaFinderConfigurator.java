package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;

public class ExtremaFinderConfigurator {
    private int backwardDaysSensitivity;
    private int forwardDaysSensitivity;
    private BigDecimal lowerCloseValueSensitivity;
    private BigDecimal upperCloseValueSensitivity;

    public ExtremaFinderConfigurator(int backwardDaysSensitivity, int forwardDaysSensitivity, BigDecimal lowerCloseValueSensitivity, BigDecimal upperCloseValueSensitivity) {
        this.backwardDaysSensitivity = backwardDaysSensitivity;
        this.forwardDaysSensitivity = forwardDaysSensitivity;
        this.lowerCloseValueSensitivity = lowerCloseValueSensitivity;
        this.upperCloseValueSensitivity = upperCloseValueSensitivity;
    }

    public int getBackwardDaysSensitivity() {
        return backwardDaysSensitivity;
    }

//    public void setBackwardDaysSensitivity(Long backwardDaysSensitivity) {
//        this.backwardDaysSensitivity = backwardDaysSensitivity;
//    }

    public int getForwardDaysSensitivity() {
        return forwardDaysSensitivity;
    }

//    public void setForwardDaysSensitivity(Long forwardDaysSensitivity) {
//        this.forwardDaysSensitivity = forwardDaysSensitivity;
//    }

    public BigDecimal getLowerCloseValueSensitivity() {
        return lowerCloseValueSensitivity;
    }

//    public void setLowerCloseValueSensitivity(BigDecimal lowerCloseValueSensitivity) {
//        this.lowerCloseValueSensitivity = lowerCloseValueSensitivity;
//    }

    public BigDecimal getUpperCloseValueSensitivity() {
        return upperCloseValueSensitivity;
    }

//    public void setUpperCloseValueSensitivity(BigDecimal upperCloseValueSensitivity) {
//        this.upperCloseValueSensitivity = upperCloseValueSensitivity;
//    }
}
