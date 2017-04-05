package com.isacademy.jjdd1.czterystrony;

import java.util.Date;

public class Rating {
    private Date date;
    private Double closeValue;

    public Rating(Date date, Double closeValue) {
        this.date = date;
        this.closeValue = closeValue;
    }

    public Date getDate() {
        return date;
    }

    public Double getCloseValue() {
        return closeValue;
    }

    @Override
    public String toString() {
        return "Data: " + date + " | Close value: " + closeValue;
    }
}
