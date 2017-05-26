package com.isacademy.jjdd1.czterystrony.beanparameters;

import javax.ws.rs.PathParam;
import java.time.LocalDate;
import java.time.Month;

public class YearParam implements PeriodParam {

    @PathParam("year")
    protected int year;

    @Override
    public LocalDate startDate() {
        return LocalDate.of(year, Month.JANUARY, 1);
    }

    @Override
    public LocalDate endDate() {
        return LocalDate.of(year, Month.DECEMBER, 31);
    }
}
