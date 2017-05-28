package com.isacademy.jjdd1.czterystrony;

public enum ReportTypes {
    INVEST_FUNDS_POPULARITY("/popularity/investfunds"),
    PENSION_FUNDS_POPULARITY("/popularity/pensionfunds"),
    INVEST_FUNDS_ZIGZAG("/zigzag/investfunds");

    private String apiContext;

    ReportTypes(String apiContext) {
        this.apiContext = apiContext;
    }

    String getApiContext() {
        return apiContext;
    }
}
