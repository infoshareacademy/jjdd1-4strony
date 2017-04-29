package com.isacademy.jjdd1.czterystrony.analysis;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimeRange {
    private LocalDate start;
    private LocalDate end;

    public TimeRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    public long getDifferenceInDays() {
        return ChronoUnit.DAYS.between(start, end);
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}