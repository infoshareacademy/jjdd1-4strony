package com.isacademy.jjdd1.czterystrony.reports.model.reports;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.serializers.JsonDateSerializer;

import java.time.LocalDate;

public abstract class Popularity {

    private String instrumentId;
    private String instrumentName;
    private String instrumentType;
    private int clicks;

    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDate date;


    public String getInstrumentId() {
        return instrumentId;
    }

    public void setInstrumentId(String instrumentId) {
        this.instrumentId = instrumentId;
    }

    public String getInstrumentName() {
        return instrumentName;
    }

    public void setInstrumentName(String instrumentName) {
        this.instrumentName = instrumentName;
    }

    public int getClicks() {
        return clicks;
    }

    public void setClicks(int clicks) {
        this.clicks = clicks;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Popularity{" +
                "instrumentId='" + instrumentId + '\'' +
                ", instrumentName='" + instrumentName + '\'' +
                ", clicks=" + clicks +
                ", date=" + date +
                '}';
    }
}