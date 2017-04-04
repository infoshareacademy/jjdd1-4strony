package com.isacademy.jjdd1.czterystrony;

import java.time.LocalDate;
import java.util.Date;

public class Rating {
    private LocalDate date;
    private Double closeValue;

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

    @Override
    public String toString() {
        return "Data: " + date + " | Close value: " + closeValue;
    }
}
