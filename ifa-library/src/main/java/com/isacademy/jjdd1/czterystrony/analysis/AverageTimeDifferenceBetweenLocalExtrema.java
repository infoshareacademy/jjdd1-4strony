package com.isacademy.jjdd1.czterystrony.analysis;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;


public class AverageTimeDifferenceBetweenLocalExtrema {

    private List<Integer> listOfDifferenceTime = new ArrayList<>();
    private List<LocalDate> listOfDates = new ArrayList<>();

    public long getDaysCount(LocalDate start, LocalDate end) {
        return Math.abs(ChronoUnit.DAYS.between(start, end));
    }

    public List<Integer> calculationDiffMeanTime() {
        for (int i = 0; i < listOfDates.size() - 1; i++) {
            listOfDifferenceTime.add(i, (int) getDaysCount(listOfDates.get(i), listOfDates.get(i + 1)));
        }
        return listOfDifferenceTime;
    }

    public Integer calculateAvgDiffBtnTime(List<Integer> listOfDifferenceTime) {
        if (listOfDifferenceTime == null || listOfDifferenceTime.isEmpty()) {
            return 0;
        }

        int sum = 0;
        for (Integer mark : listOfDifferenceTime) {
            sum += mark;
        }
        return sum / listOfDifferenceTime.size();
    }

    public List<Integer> getListOfDifferenceTime() {
        return listOfDifferenceTime;
    }
}





