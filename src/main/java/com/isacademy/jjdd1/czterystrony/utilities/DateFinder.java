package com.isacademy.jjdd1.czterystrony.utilities;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;

public class DateFinder {
    public static LocalDate findClosestInDates(List<LocalDate> dates, LocalDate date) {
        return Collections.min(dates, (d1, d2) -> {
            long diff1 = Math.abs(ChronoUnit.DAYS.between(d1, date));
            long diff2 = Math.abs(ChronoUnit.DAYS.between(d2, date));
            return Long.compare(diff1,diff2);
        });
    }
}
