package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.time.LocalDate;
import java.time.Period;

public class TimeRange {
    private LocalDate start;
    private LocalDate end;

    public TimeRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public int getDifferenceInDays() {
        return Period.between(start, end).getDays();
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
