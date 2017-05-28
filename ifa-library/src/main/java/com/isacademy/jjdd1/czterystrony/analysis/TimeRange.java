package com.isacademy.jjdd1.czterystrony.analysis;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimeRange {

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate start;

    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate end;

    @JsonIgnore
    private long differenceInDays;

    public TimeRange() {
    }

    public TimeRange(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
        this.differenceInDays = ChronoUnit.DAYS.between(start, end);
    }

    public long getDifferenceInDays() {
        return differenceInDays;
    }

    public LocalDate getStart() {
        return start;
    }

    public LocalDate getEnd() {
        return end;
    }
}
