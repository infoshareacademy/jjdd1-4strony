package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.Date;

public class Rating {
    private Date date;
    private BigDecimal closeValue;

    public Rating(Date date, BigDecimal closeValue) {
        this.date = date;
        this.closeValue = closeValue;
    }

    public Date getDate() {
        return date;
    }

    public BigDecimal getCloseValue() {
        return closeValue;
    }

    @Override
    public String toString() {
        return "Data: " + date + " | Close value: " + closeValue;
    }
}
