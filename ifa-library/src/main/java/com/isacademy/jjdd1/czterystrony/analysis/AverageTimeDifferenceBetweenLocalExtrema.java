package com.isacademy.jjdd1.czterystrony.analysis;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class AverageTimeDifferenceBetweenLocalExtrema {

    public Integer calculate(List<LocalDate> dates) {
        List<Integer> listOfDifferences = getListOfDifferencesInDaysBetweenAdjacentDates(dates);

        if (listOfDifferences == null || listOfDifferences.isEmpty()) {
            return 0;
        }

        Integer sum = listOfDifferences.stream()
                .reduce(0, (a, b) -> a + b);

        return sum / listOfDifferences.size();
    }

    private List<Integer> getListOfDifferencesInDaysBetweenAdjacentDates(List<LocalDate> dates) {
        List<Integer> listOfDifferences = new ArrayList<>();

        for (int i = 1; i < dates.size(); i++) {
            listOfDifferences.add(getDifferenceInDays(dates.get(i), dates.get(i - 1)));
        }
        return listOfDifferences;
    }

    private Integer getDifferenceInDays(LocalDate firstDate, LocalDate secondDate) {
        return Math.abs((int) ChronoUnit.DAYS.between(firstDate, secondDate));
    }
}