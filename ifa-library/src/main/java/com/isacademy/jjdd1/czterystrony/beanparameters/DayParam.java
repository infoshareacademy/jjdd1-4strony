package com.isacademy.jjdd1.czterystrony.beanparameters;

import javax.ws.rs.PathParam;
import java.time.LocalDate;

public class DayParam extends MonthParam {

    @PathParam("day")
    private int day;

    @Override
    public LocalDate startDate() {
        return LocalDate.of(year, month, day);
    }

    @Override
    public LocalDate endDate() {
        return startDate();
    }
}
