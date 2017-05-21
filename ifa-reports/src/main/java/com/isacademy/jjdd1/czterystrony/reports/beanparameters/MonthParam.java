package com.isacademy.jjdd1.czterystrony.reports.beanparameters;

import javax.ws.rs.PathParam;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;

public class MonthParam implements PeriodParam {

    @PathParam("month")
    private int month;

    @PathParam("year")
    private int year;

    @Override
    public LocalDate startDate() {
        return LocalDate.of(year, month, 1);
    }

    @Override
    public LocalDate endDate() {
        return startDate().with(lastDayOfMonth());
    }
}
