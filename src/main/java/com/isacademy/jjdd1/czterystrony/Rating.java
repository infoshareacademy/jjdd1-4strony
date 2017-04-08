package com.isacademy.jjdd1.czterystrony;

import java.math.BigDecimal;
import java.util.Date;

public class Rating implements Comparable<Rating> {
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
    public int compareTo(Rating rating) {
        return date.compareTo(rating.date);
    }

    @Override
    public String toString() {
        return "Data: " + date + " | Close value: " + closeValue;
    }
}
//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//
//        Rating rating = (Rating) o;
//
//        if (date != null ? !date.equals(rating.date) : rating.date != null) return false;
//        return closeValue != null ? closeValue.equals(rating.closeValue) : rating.closeValue == null;
//    }
//
//    @Override
//    public int hashCode() {
//        int result = date != null ? date.hashCode() : 0;
//        result = 31 * result + (closeValue != null ? closeValue.hashCode() : 0);
//        return result;
//    }
