package com.isacademy.jjdd1.czterystrony.reports.model.reports;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.reports.JsonDateSerializer;

import javax.persistence.*;
import java.time.LocalDate;

@MappedSuperclass
public class Popularity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "INSTRUMENT_ID")
    private String instrumentId;

    @Column(name = "INSTRUMENT_NAME")
    private String instrumentName;

    @Column(name = "NUMBER_OF_CLICKS")
    private int clicks;

    @Column(name = "DATE")
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