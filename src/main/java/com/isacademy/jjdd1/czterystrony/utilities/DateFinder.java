package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class DateFinder {
    public static LocalDate findClosestDateInDates(List<LocalDate> dates, LocalDate date) {
        return Collections.min(dates, (d1, d2) -> {
            long diff1 = Math.abs(ChronoUnit.DAYS.between(d1, date));
            long diff2 = Math.abs(ChronoUnit.DAYS.between(d2, date));
            return Long.compare(diff1, diff2);
        });
    }

    public static int findIndexOfRatingWithClosestDateInRatings(List<Rating> ratings, LocalDate date) {
        List<LocalDate> dates = ratings.stream()
                .map(t -> t.getDate())
                .sorted()
                .collect(Collectors.toList());

        LocalDate closestDate = DateFinder.findClosestDateInDates(dates, date);

        return dates.indexOf(closestDate);
    }
}
