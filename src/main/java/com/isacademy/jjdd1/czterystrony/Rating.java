package com.isacademy.jjdd1.czterystrony;

import java.time.LocalDate;
import java.util.Date;

public class Rating {
    private LocalDate date;
    private Double closeValue;
    /*
    private Double volume;
    private Double high;
    private Double low;
    */

    public Rating(LocalDate date, Double closeValue) {
        this.date = date;
        this.closeValue = closeValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Double getCloseValue() {
        return closeValue;
    }

    public void setCloseValue(Double closeValue) {
        this.closeValue = closeValue;
    }

    /*
    public Double getHigh() {
        return high;
    }

    public void setHigh(Double high) {
        this.high = high;
    }

    public Double getLow() {
        return low;
    }

    public void setLow(Double low) {
        this.low = low;
    }

    public Double getVolume() {
        return volume;
    }

    public void setVolume(Double volume) {
        this.volume = volume;
    }
    */

    @Override
    public String toString() {
        return "Data: " + date + " | Close value: " + closeValue;
    }
}
