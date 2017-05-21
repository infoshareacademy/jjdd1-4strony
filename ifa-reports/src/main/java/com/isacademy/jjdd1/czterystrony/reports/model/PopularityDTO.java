package com.isacademy.jjdd1.czterystrony.reports.model;

public class PopularityDTO {
    private String instrumentId;
    private String instrumentName;
    private int clicks;

    public PopularityDTO(String instrumentId, String instrumentName, int clicks) {
        this.instrumentId = instrumentId;
        this.instrumentName = instrumentName;
        this.clicks = clicks;
    }

    public String getInstrumentId() {
        return instrumentId;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public int getClicks() {
        return clicks;
    }
}
