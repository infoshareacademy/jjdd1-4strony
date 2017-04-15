package com.isacademy.jjdd1.czterystrony.utilities;

import com.isacademy.jjdd1.czterystrony.instruments.FinancialInstrument;
import com.isacademy.jjdd1.czterystrony.instruments.Rating;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class LocalExtremaFinder {
    private final int DEFAULT_START_INDEX = 0;
    private List<Rating> ratings;
    private int startIndex;
    private int endIndex;

    public LocalExtremaFinder(FinancialInstrument financialInstrument) {
        this.ratings = financialInstrument.getRatings();
        this.startIndex = DEFAULT_START_INDEX;
        this.endIndex = ratings.size();
    }

    public LocalExtremaFinder(FinancialInstrument financialInstrument, LocalDate startDate) {
        this.ratings = financialInstrument.getRatings();
        this.startIndex = findIndexOfClosestDate(startDate, DEFAULT_START_INDEX);
        this.endIndex = ratings.size();
    }

    public LocalExtremaFinder(FinancialInstrument financialInstrument, LocalDate startDate, LocalDate endDate) {
        this.ratings = financialInstrument.getRatings();
        this.startIndex = findIndexOfClosestDate(startDate, DEFAULT_START_INDEX);
        this.endIndex = findIndexOfClosestDate(endDate, ratings.size());
    }

    private int findIndexOfClosestDate(LocalDate date, int defaultIndex) {
        if (date != null) {
            List<LocalDate> dates = ratings.stream()
                    .map(t -> t.getDate())
                    .sorted()
                    .collect(Collectors.toList());

            LocalDate closestDate = DateFinder.findClosestDateInDates(dates, date);

            return dates.indexOf(closestDate);
        } else {
            return defaultIndex;
        }
    }

    public List<Rating> findExtrema(double minSwingLimitInPct) {
        boolean swingHigh = false;
        boolean swingLow = false;

        int lowIndex = startIndex;
        int highIndex = startIndex;

        List<Rating> extrema = new ArrayList<>();

        for (int i = startIndex; i < endIndex; i++) {
            if (currentValueIsGreaterThenLastHighValue(i, highIndex)) {
                highIndex = i;

                if (!swingLow && percentageDifferenceBetweenHighAndLowIsGreaterThenLimit(highIndex, lowIndex, minSwingLimitInPct)) {
                    extrema.add(ratings.get(lowIndex));
                    swingHigh = false;
                    swingLow = true;
                }

                if (swingLow) lowIndex = highIndex;

            } else if (currentValueIsSmallerThenLastLowValue(i, lowIndex)) {
                lowIndex = i;

                if (!swingHigh && percentageDifferenceBetweenHighAndLowIsGreaterThenLimit(highIndex, lowIndex, minSwingLimitInPct)) {
                    extrema.add(ratings.get(highIndex));
                    swingHigh = true;
                    swingLow = false;
                }

                if (swingHigh) highIndex = lowIndex;
            }
        }
        return extrema;
    }

    private boolean currentValueIsGreaterThenLastHighValue(int currentIndex, int highIndex) {
        return ratings.get(currentIndex).getCloseValue().subtract(ratings.get(highIndex).getCloseValue()).compareTo(BigDecimal.ZERO) == 1;
    }

    private boolean currentValueIsSmallerThenLastLowValue(int currentIndex, int lowIndex) {
        return ratings.get(currentIndex).getCloseValue().subtract(ratings.get(lowIndex).getCloseValue()).compareTo(BigDecimal.ZERO) == -1;
    }

    private boolean percentageDifferenceBetweenHighAndLowIsGreaterThenLimit(int highIndex, int lowIndex, double minSwingLimitInPct) {
        return (ratings.get(highIndex).getCloseValue()
                .subtract(ratings.get(lowIndex).getCloseValue()))
                .divide(ratings.get(lowIndex).getCloseValue(), 2, BigDecimal.ROUND_UP)
                .compareTo(BigDecimal.valueOf(minSwingLimitInPct / 100D)) >= 0;
    }
}
