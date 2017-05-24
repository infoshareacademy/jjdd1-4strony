package com.isacademy.jjdd1.czterystrony.technicalanalysis;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.isacademy.jjdd1.czterystrony.serializers.JsonDateSerializer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimeRange {

    @JsonSerialize(using = JsonDateSerializer.class)
    private LocalDate start;

    @JsonSerialize(using = JsonDateSerializer.class)
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
