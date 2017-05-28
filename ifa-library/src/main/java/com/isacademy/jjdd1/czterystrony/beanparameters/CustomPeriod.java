package com.isacademy.jjdd1.czterystrony.beanparameters;

import java.time.LocalDate;

public class CustomPeriod implements PeriodParam {

    private LocalDate start;
    private LocalDate end;

    public CustomPeriod(LocalDate start, LocalDate end) {
        this.start = start;
        this.end = end;
    }

    @Override
    public LocalDate startDate() {
        return start;
    }

    @Override
    public LocalDate endDate() {
        return end;
    }
}
