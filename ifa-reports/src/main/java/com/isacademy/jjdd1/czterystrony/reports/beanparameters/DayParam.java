package com.isacademy.jjdd1.czterystrony.reports.beanparameters;

import javax.ws.rs.PathParam;
import java.time.LocalDate;

public class DayParam implements PeriodParam {

    @PathParam("day")
    private int day;

    @PathParam("month")
    private int month;

    @PathParam("year")
    private int year;

    @Override
    public LocalDate startDate() {
        return LocalDate.of(year, month, day);
    }

    @Override
    public LocalDate endDate() {
        return startDate();
    }
}
