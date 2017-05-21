package com.isacademy.jjdd1.czterystrony.reports.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public enum Instrument {
    @JsonProperty("investfunds")
    INVEST_FUND(InvestFundsPopularity.class);

    private Class type;

    Instrument(Class type) {
        this.type = type;
    }

    public Class getType() {
        return type;
    }
}
