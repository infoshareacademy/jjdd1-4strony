package com.isacademy.jjdd1.czterystrony.beanparameters;

import javax.ws.rs.PathParam;
import java.time.LocalDate;
import static java.time.temporal.TemporalAdjusters.*;

public class MonthParam extends YearParam {

    @PathParam("month")
    protected int month;

    @Override
    public LocalDate startDate() {
        return LocalDate.of(year, month, 1);
    }

    @Override
    public LocalDate endDate() {
        return startDate().with(lastDayOfMonth());
    }
}
