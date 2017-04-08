package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.time.LocalDate;

public class Rating implements Comparable<Rating> {
    private LocalDate date;
    private BigDecimal closeValue;

    public Rating(LocalDate date, BigDecimal closeValue) {
        this.date = date;
        this.closeValue = closeValue;
    }

    public LocalDate getDate() {
        return date;
    }

    public BigDecimal getCloseValue() {
        return closeValue;
    }

    @Override
    public int compareTo(Rating rating) {
        return date.compareTo(rating.date);
    }

    @Override
    public String toString() {
        return "Data: " + date + " | Close value: " + closeValue;
    }
}